package websocket;

import entity.ExtLoginEntity;
import global.GlobalWebExtTokens;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{token}")
public class TestWebsocketServer {

    private static int onlineCount = 0;

    private String token;
    private Map<String, ExtLoginEntity> onlineExtTokenMap = new HashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws IOException {
        boolean flag = GlobalWebExtTokens.getGlobalWebExtLoginTokensMap().containsKey(token);
        if(flag){
            this.token = token;
            this.onlineExtTokenMap.put(this.token, GlobalWebExtTokens.getGlobalWebExtLoginTokensMap().get(this.token));
            this.session = session;
            addOnlineCount();
            System.out.println("新连接："+token);
            session.getBasicRemote().sendText("欢迎登录："+this.onlineExtTokenMap.get(this.token).getExtid()+" 号分机");
        }
        else{
            System.out.println("拒绝访问！！！错误的token："+token);
            session.close();
        }
    }


    @OnClose
    public void onClose(){
        System.out.println("连接关闭："+this.token);
        minusOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到用户信息："+this.token + "：" + message);
        session.getBasicRemote().sendText("已收到信息："+message);
    }

    @OnError
    public void onError(Session session, Throwable err){
        System.out.println("连接错误："+this.token);
        err.printStackTrace();
    }


    public synchronized void addOnlineCount(){
        TestWebsocketServer.onlineCount++;
    }

    public synchronized void minusOnlineCount(){
        TestWebsocketServer.onlineCount--;
    }
}
