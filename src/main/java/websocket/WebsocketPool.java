package websocket;

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



    public synchronized static void addWsEndPoint(String extid, WebsocketEndPoint wsed){
        WebsocketPool.wsPool.put(extid, wsed);
        ++size;
    }

    public synchronized static void removeWsEndPoint(String extid){
        WebsocketPool.wsPool.remove(extid);
        --size;
    }


    public synchronized static int getWsPoolSize(){
        return WebsocketPool.size;
    }

    public synchronized static WebsocketEndPoint getWsEndPoint(String extid){
        return WebsocketPool.wsPool.get(extid);
    }

    public synchronized static void sendMessageToOneWsEndPoint(String extid, String msg){
        WebsocketEndPoint wsed =  WebsocketPool.wsPool.get(extid);
        if(wsed != null){
            try {
                wsed.getSession().getBasicRemote().sendText(msg);
                //System.out.println("信息发送成功 : "+extid);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("[x] Exception Trace: src.main.java.websocket--WebsocketPool:sendMessageToOneWsEndPoint" +"\n"+
                        "   异常：无法向指定EndPoint发送信息。");
            }
        }else {
            System.out.println("[x] Error Trace: src.main.java.websocket--WebsocketPool:sendMessageToOneWsEndPoint" +"\n"+
                    "   错误：不存在指定的EndPoint："+extid);
        }
    }


}
