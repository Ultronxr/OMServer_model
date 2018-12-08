package websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;
import entity.ExtLoginEntity;
import entity.VisitorEntity;
import global.GlobalWebExtTokens;
import websocket.msgreceivebean.ConnectExtToExtRB;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/websocket/{token}")
public class WebsocketServer {

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

            System.out.println("新连接："+this.token);
            System.out.println("当前在线数："+GlobalWebExtTokens.getOnlineTokensMap().size());

            this.session.getBasicRemote().sendText(
                    "分机拨分机请发送命令：ConnectExtToExt:{\"attribute\":\"Connect\", \"extid1\":\"227\", \"extid2\":\"228\"}");

            String extLoginEntity_str = JSON.toJSONString(extLoginEntity);
            this.session.getBasicRemote().sendText(extLoginEntity_str);

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

//        if(message.startsWith("ConnectExtToExt:")){
//            System.out.println(message.substring(16));
//            JSONObject jsonObject = JSON.parseObject(message.substring(16));
//            String attribute = (String)jsonObject.get("attribute"),
//                   extid1 = (String)jsonObject.get("extid1"),
//                   extid2 = (String)jsonObject.get("extid2");
//            System.out.println(attribute);
//            System.out.println(extid1);
//            System.out.println(extid2);
//            OMTransferDao omTransferDao = new OMTransferDaoImpl();
//            omTransferDao.ConnectExtToExt(null, Integer.valueOf(extid1), Integer.valueOf(extid2));
//        }

    }

    @OnError
    public void onError(Session session, Throwable e) throws IOException {
        this.session.close();
        System.out.println("连接错误："+this.token);
        //e.printStackTrace();
    }

}
