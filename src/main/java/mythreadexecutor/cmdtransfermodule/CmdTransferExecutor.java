package mythreadexecutor.cmdtransfermodule;

import com.rabbitmq.client.*;
import entity.__QueryEntity;
import utils.databases.MyRabbitMQ;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 这是一个线程中的execute方法实例，用来在rabbitmq里注册一个consumer消费者，去取消息队列中的信息
 */
public class CmdTransferExecutor implements Runnable{

    public CmdTransferExecutor(){

    }

    public void execute(){
        //System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +" CmdTransferExecutor 方法被调用。");

        System.out.println("[*] "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 正在监听消息队列是否存在拨号请求...");

        ConnectionFactory connFactory = MyRabbitMQ.getConnFactory();
        Connection connection = MyRabbitMQ.getConnection(connFactory);
        final Channel channel = MyRabbitMQ.getChannel(connection);

        String queueName = "omserverCallQueryQueue";
        String exchangeName = "omserverExchanger"; //路由名称
        String exchangeType = "direct"; //路由规则
        String routingKey = "omserverKey";

        try{
            //创建路由：路由名称，路由规则，是否持久化
            channel.exchangeDeclare(exchangeName, exchangeType, true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);
            channel.basicQos(1);

            boolean autoAck = false;

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body, "UTF-8");
                    System.out.println("[*] " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Step_03 从消息队列中取出拨号请求：" + message);

                    int index_attribute = message.indexOf("attribute");
                    int index_ext_id_from = message.indexOf("ext_id_from");
                    int index_ext_id_to = message.indexOf("ext_id_to");

                    String str_attribute = message.substring(index_attribute+10, index_ext_id_from-1);
                    String str_ext_id_from = message.substring(index_ext_id_from+12, index_ext_id_to-1);
                    String str_ext_id_to = message.substring(index_ext_id_to+10);

                    System.out.println("获取的请求内容："+ str_attribute + " " + str_ext_id_from + " " + str_ext_id_to);
                    HttpTransferer.HttpTransferer(new __QueryEntity(str_attribute, str_ext_id_from, str_ext_id_to));
                    //手动确认消息消费完成
                    channel.basicAck(envelope.getDeliveryTag(), false);

                }
            };

            channel.basicConsume(queueName, autoAck, consumer);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        /**
         * @Warnning: 这里不应该加finally中的关闭语句，否则finally中的语句会执行，
         *              导致消息队列在线程结束前就关闭了，无法正常使用consumer进行消费！！
         */
        /*
        finally {
            MyRabbitMQ.closeChannelAndConnection(channel, connection);
        }
        */

    }

    @Override
    public void run(){
        execute();
    }
}
