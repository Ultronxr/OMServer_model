package dao.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dao.__MQOperateDao;
import entity.__QueryEntity;
import utils.databases.MyRabbitMQ;

public class __MQOperateDaoImpl implements __MQOperateDao {

    @Override
    public boolean transferToMQ(__QueryEntity queryEntity){
        ConnectionFactory connFactory = MyRabbitMQ.getConnFactory();
        Connection connection = MyRabbitMQ.getConnection(connFactory);
        Channel channel = MyRabbitMQ.getChannel(connection);

        String exchangeName = "omserverExchanger"; //路由名称
        String exchangeType = "direct"; //路由规则
        String routingKey = "omserverKey";

        try{
            //创建路由：路由名称，路由规则，是否持久化
            channel.exchangeDeclare(exchangeName, exchangeType, true);

            //System.out.println("[*] "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 正在把拨号请求加入消息队列："+queryEntity.toString());
            //发送消息：路由名称，routingKey，其他信息，消息字节数组
            channel.basicPublish(exchangeName, routingKey, null, queryEntity.toString().getBytes("UTF-8"));

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            MyRabbitMQ.closeChannelAndConnection(channel, connection);
        }

        return true;
    }

}
