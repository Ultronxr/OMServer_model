package websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;
import entity.ExtLoginEntity;
import entity.VisitorEntity;
import global.GlobalWebExtTokens;
import utils.GetCurrentTime;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

/**
 * websocketEndPoint：一个单独且完整的ws连接
 * 这整个websocketEndPoint都被放在连接池里维护
 */
@ServerEndpoint(value = "/websocket/{token}")
public class WebsocketEndPoint {

    private Session session; //当前会话(ws connect)的session
    private String token; //当前会话的身份标识token
    private ExtLoginEntity extLoginEntity = new ExtLoginEntity(); //当前会话的身份实体类

    private ArrayList<VisitorEntity> waitingQueue = new ArrayList<>(); //当前会话的等待队列
    private ArrayList<VisitorEntity> holdingQueue = new ArrayList<>(); //当前会话的保持队列
    private VisitorEntity activeCall = new VisitorEntity(); //正在通话的实体类


    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws IOException {

        boolean flag = GlobalWebExtTokens.getLoginTokensMap().containsKey(token);
        if(flag){
            this.session = session;
            this.token = token;
            this.extLoginEntity = GlobalWebExtTokens.getLoginTokensMap().get(this.token);
            GlobalWebExtTokens.getOnlineTokensMap().put(this.token, this.extLoginEntity);

            System.out.println("[*] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onOpen()");
            System.out.println("    新连接token："+this.token);
            System.out.println("    当前在线数："+GlobalWebExtTokens.getOnlineTokensMap().size());

            WebsocketPool.addWsEndPoint(this.extLoginEntity.getExtid(), this);
            System.out.println("[*] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onOpen()");
            System.out.println("    当前登录的是："+this.extLoginEntity.getExtid());
            System.out.println("    当前连接池大小："+WebsocketPool.getWsPoolSize());

        }
        else{
            System.out.println("[x] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onOpen()");
            System.out.println("    拒绝访问！！！错误的token："+token);
            session.close();
        }
    }

    @OnClose
    public void onClose() throws IOException {
        this.session.close();
        GlobalWebExtTokens.getOnlineTokensMap().remove(this.token);

        System.out.println("[*] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onClose()");
        System.out.println("    连接关闭："+this.token);

        WebsocketPool.removeWsEndPoint(this.extLoginEntity.getExtid());
        System.out.println("    连接池大小："+WebsocketPool.getWsPoolSize());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        System.out.println("[*] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onMessage()");
        System.out.println("    收到用户信息："+this.token + "：" + message);

        JSONObject jobj1 = new JSONObject();
        jobj1.put("response","1");
        session.getBasicRemote().sendText(jobj1.toJSONString());

        JSONObject jobj2 = (JSONObject) JSON.parse(message);

        if(jobj2.getString("attr").equals("ConnectExtToExt")){
            String extFrom = jobj2.getJSONObject("data").getString("extFrom");
            String extTo = jobj2.getJSONObject("data").getString("extTo");

            JSONObject jobj3 = new JSONObject(), jobj4 = new JSONObject();
            jobj3.put("response","2");
            jobj4.put("extFrom",extFrom);
            jobj4.put("extTo",extTo);

            if(WebsocketPool.isExtWsOnline(extTo)){ //判断所拨打的分机是否在线，这是在线的情况
                jobj4.put("status","ExtOnline");
                jobj4.put("process","Connecting");
                jobj3.put("data",jobj4);
                session.getBasicRemote().sendText(jobj3.toJSONString());

                WebsocketPool.getOmTransferDao().ConnectExtToExt(null, Integer.parseInt(extFrom), Integer.parseInt(extTo));
            }
            else{ //分机不在线
                jobj4.put("status","ExtOffline");
                jobj4.put("process","");
                jobj3.put("data",jobj4);
                session.getBasicRemote().sendText(jobj3.toJSONString());
            }
        }


    }

    @OnError
    public void onError(Session session, Throwable e) throws IOException {
        this.session.close();
        System.out.println("[x] "+GetCurrentTime.formatedTime()+" websocket.WebsocketEndPoint-onError()");
        System.out.println("    websocket连接错误："+this.token);
    }





















    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ExtLoginEntity getExtLoginEntity() {
        return extLoginEntity;
    }

    public void setExtLoginEntity(ExtLoginEntity extLoginEntity) {
        this.extLoginEntity = extLoginEntity;
    }

    public ArrayList<VisitorEntity> getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(ArrayList<VisitorEntity> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    public ArrayList<VisitorEntity> getHoldingQueue() {
        return holdingQueue;
    }

    public void setHoldingQueue(ArrayList<VisitorEntity> holdingQueue) {
        this.holdingQueue = holdingQueue;
    }

    public VisitorEntity getActiveCall() {
        return activeCall;
    }

    public void setActiveCall(VisitorEntity activeCall) {
        this.activeCall = activeCall;
    }
}
