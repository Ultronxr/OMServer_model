package websocket;

import com.alibaba.fastjson.JSON;
import entity.ExtLoginEntity;
import entity.VisitorEntity;
import global.GlobalWebExtTokens;
import utils.MySerializeUtil;
import websocket.messagebean.WaitingQueueMB;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@ServerEndpoint(value = "/websocket/{token}")
public class WebsocketServer {

    private String token;
    private Session session;
    private ArrayList<VisitorEntity> holdingQueue = new ArrayList<>();
    private ArrayList<VisitorEntity> waitingQueue = new ArrayList<>();
    private int testCount = 0;

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws IOException {
        boolean flag = GlobalWebExtTokens.getLoginTokensMap().containsKey(token);
        if(flag){
            this.token = token;
            GlobalWebExtTokens.getOnlineTokensMap().put(
                    this.token, GlobalWebExtTokens.getLoginTokensMap().get(this.token));
            this.session = session;
            System.out.println("新连接："+token);
            System.out.println("当前在线数："+GlobalWebExtTokens.getOnlineTokensMap().size());
            session.getBasicRemote().sendText("欢迎登录："
                    + GlobalWebExtTokens.getOnlineTokensMap().get(this.token).getExtid() +" 号分机");

            session.getBasicRemote().sendText(this.session.getId());

            this.testCount++;
            this.session.getBasicRemote().sendText(String.valueOf(testCount));

//            VisitorEntity ve = new VisitorEntity();
//            ve.setExtid(999);
//            this.waitingQueue.add(ve);
//            session.getBasicRemote().sendText(JSON.toJSONString(new WaitingQueueMB(waitingQueue)));
        }
        else{
            System.out.println("拒绝访问！！！错误的token："+token);
            session.close();
        }
    }


    @OnClose
    public void onClose() throws IOException {
        this.session.close();
        GlobalWebExtTokens.getOnlineTokensMap().remove(this.token);
        System.out.println("连接关闭："+this.token);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到用户信息："+this.token + "：" + message);
        session.getBasicRemote().sendText("已收到信息："+message);
    }

    @OnError
    public void onError(Session session, Throwable e) throws IOException {
        this.session.close();
        System.out.println("连接错误："+this.token);
        //e.printStackTrace();
    }

}
