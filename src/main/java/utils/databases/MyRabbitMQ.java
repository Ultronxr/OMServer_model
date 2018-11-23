package utils.databases;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.ResourceBundle;


/**
 * @Name: RabbitMQ的静态方法类
 * @Description: 创建连接、关闭连接等
 *
 */
public class MyRabbitMQ {

    private static final String facHost;
    private static final int facPort;
    private static final String facUsername;
    private static final String facPassword;
    private static final String facVirtualHost;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("rabbitmq_config");

    static{
        facHost = resourceBundle.getString("amqp.host");
        facPort = Integer.valueOf(resourceBundle.getString("amqp.port"));
        facUsername = resourceBundle.getString("amqp.username");
        facPassword = resourceBundle.getString("amqp.password");
        facVirtualHost = resourceBundle.getString("amqp.virtualHost");
    }

    public static ConnectionFactory getConnFactory(){
        ConnectionFactory connFactory = null;
        try{
            connFactory = new ConnectionFactory();
            connFactory.setHost(facHost);
            connFactory.setPort(facPort);
            connFactory.setUsername(facUsername);
            connFactory.setPassword(facPassword);
            connFactory.setVirtualHost(facVirtualHost);
        }catch(Exception e){
            e.printStackTrace();
        }

        return connFactory;
    }

    public static Connection getConnection(ConnectionFactory connectionFactory){
        Connection conn = null;
        try{
            conn = connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }

    public static Channel getChannel(Connection connection){
        Channel chan = null;
        try{
            chan = connection.createChannel();
        }catch (Exception e){
            e.printStackTrace();
        }

        return chan;
    }

    public static void closeChannelAndConnection(Channel channel, Connection connection){
        try{
            if(null != channel) channel.close();
            if(null != connection) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
