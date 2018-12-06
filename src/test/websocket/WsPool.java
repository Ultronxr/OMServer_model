package websocket;

import entity.ExtLoginEntity;
import org.java_websocket.WebSocket;

import java.util.*;

public class WsPool {

    /**
     * websocket对应ExtLoginEntity
     */
    private static final Map<WebSocket, ExtLoginEntity> wsExtMap = new HashMap<>();


    /**
     * 通过websocket连接获取ExtLoginEntity
     */
    public static ExtLoginEntity getWsByExtid(WebSocket ws) {
        return wsExtMap.get(ws);
    }


    /**
     * 通过ExtLoginEntity连接获取websocket连接，这是一个list,此处取第一个
     * 因为有可能多个websocket对应一个ExtLoginEntity（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
     */
    public static WebSocket getWsByUser(ExtLoginEntity extLoginEntity) {
        Set<WebSocket> keySet = wsExtMap.keySet();
        synchronized (keySet) {
            for (WebSocket wsConn : keySet) {
                ExtLoginEntity temp = wsExtMap.get(wsConn);
                if (temp.equals(extLoginEntity)) {
                    return wsConn;
                }
            }
        }
        return null;
    }


    /**
     * 向连接池中添加连接
     */
    public static void addWsConn(WebSocket ws, ExtLoginEntity extLoginEntity) {
        wsExtMap.put(ws, extLoginEntity);
    }


    /**
     * 获取当前所有在线的Ext
     *
     * @return
     */
    public static Collection<ExtLoginEntity> getOnlineExt() {
        List<ExtLoginEntity> extsSet = new ArrayList<ExtLoginEntity>();
        Collection<ExtLoginEntity> extSet = wsExtMap.values();
        for (ExtLoginEntity temp : extSet) {
            extsSet.add(temp);
        }
        return extsSet;
    }


    /**
     * 移除连接池中的某个指定连接
     */
    public static boolean removeExt(WebSocket wsConn) {
        if (wsExtMap.containsKey(wsConn)) {
            wsExtMap.remove(wsConn); // 移除连接
            return true;
        } else {
            return false;
        }
    }


    /**
     * 向特定的连接发送数据
     */
    public static void sendMessageToExt(WebSocket wsConn, String message){
        if(null != wsConn && null != wsExtMap.get(wsConn)){
            wsConn.send(message);
        }
    }


    /**
     * 向所有的连接发送数据
     */
    public static void sendMessageToAll(String message) {
        Set<WebSocket> keySet = wsExtMap.keySet();
        synchronized (keySet) {
            for (WebSocket wsConn : keySet) {
                ExtLoginEntity ext = wsExtMap.get(wsConn);
                if (ext != null) {
                    wsConn.send(message);
                }
            }
        }
    }










}