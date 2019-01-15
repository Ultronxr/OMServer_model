package websocket;

import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;
import utils.GetCurrentTime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket连接池
 * 用来维护全体ws连接：新增连接、删除连接、向某个连接发送信息等
 */
public class WebsocketPool {

    private static Map<String, WebsocketEndPoint> wsPool = new HashMap<>();
    private static int size = 0;
    private static OMTransferDao omTransferDao = new OMTransferDaoImpl();

    //添加一个连接进入连接池
    public synchronized static void addWsEndPoint(String extid, WebsocketEndPoint wsed){
        WebsocketPool.wsPool.put(extid, wsed);
        ++size;
    }

    //从连接池中删除一个连接
    public synchronized static void removeWsEndPoint(String extid){
        WebsocketPool.wsPool.remove(extid);
        --size;
    }

    //获取当前连接池大小（大小的意思是连接的数量）
    public synchronized static int getWsPoolSize(){
        return WebsocketPool.size;
    }

    //获取一个指定分机号的ws连接
    public synchronized static WebsocketEndPoint getWsEndPoint(String extid){
        return WebsocketPool.wsPool.get(extid);
    }

    //判断一个分机的连接是否存在
    public synchronized static boolean isExtWsOnline(String extid){
        if(WebsocketPool.wsPool.get(extid)==null) return false;
        else return true;
    }

    //发送信息给指定分机
    public synchronized static void sendMessageToOneWsEndPoint(String extid, String msg){
        WebsocketEndPoint wsed =  WebsocketPool.wsPool.get(extid);
        if(wsed != null){
            try {
                wsed.getSession().getBasicRemote().sendText(msg);
                //System.out.println("信息发送成功 : "+extid);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("[x] "+ GetCurrentTime.formatedTime()+" websocket.WebsocketPool-sendMessageToOneWsEndPoint()");
                System.out.println("    Exception: 无法向指定EndPoint发送信息。");
            }
        }else {
            System.out.println("[x] "+ GetCurrentTime.formatedTime()+" websocket.WebsocketPool-sendMessageToOneWsEndPoint()");
            System.out.println("    Exception: 不存在指定的EndPoint："+extid);
        }
    }

    public synchronized static OMTransferDao getOmTransferDao(){return WebsocketPool.omTransferDao;}


}
