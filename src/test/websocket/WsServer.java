//package websocket;
//
//import entity.ExtLoginEntity;
//import org.java_websocket.WebSocket;
//import org.java_websocket.handshake.ClientHandshake;
//import org.java_websocket.server.WebSocketServer;
//
//
//import javax.websocket.Session;
//import java.net.InetSocketAddress;
//
//public class WsServer extends WebSocketServer {
//
//    public WsServer(int port) {
//        super(new InetSocketAddress(port));
//    }
//
//    public WsServer(InetSocketAddress address) {
//        super(address);
//    }
//
//    @Override
//    public void onOpen(WebSocket wsConn, ClientHandshake handshake) {
//        // ws连接的时候触发的代码，onOpen中我们不做任何操作
//
//    }
//
//
//    @Override
//    public void onClose(WebSocket wsConn, int code, String reason, boolean remote) {
//        //断开连接时候触发代码
//        extLeave(wsConn);
//        System.out.println(reason);
//    }
//
//
////    @Override
////    public void onMessage(WebSocket wsConn, ExtLoginEntity ext) {
////        System.out.println(ext.getExtid());
////        if(null != message && message.startsWith("online")){
////            String userName = message.replaceFirst("online", message);//用户名
////            extJoin(wsConn, userName);//用户加入
////        }else if(null != message && message.startsWith("offline")){
////            extLeave(wsConn);
////        }
////    }
//
//
//    @Override
//    public void onMessage(String message, Session session){
//
//    }
//
//    @Override
//    public void onError(WebSocket conn, Exception ex) {
//        //错误时候触发的代码
//        System.out.println("on error");
//        ex.printStackTrace();
//    }
//
//
//    /**
//     * 去除掉失效的websocket链接
//     */
//    private void extLeave(WebSocket wsConn){
//        WsPool.removeExt(wsConn);
//    }
//
//
//    /**
//     * 将websocket加入连接池
//     */
//    private void extJoin(WebSocket wsConn, ExtLoginEntity extLoginEntity){
//        WsPool.addWsConn(wsConn, extLoginEntity);
//    }
//}
