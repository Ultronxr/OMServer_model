package mythreadexecutor;

import dao.OMConfigureDao;
import dao.OMTransferDao;
import dao.impl.OMConfigureDaoImpl;
import dao.impl.OMTransferDaoImpl;
import entity.VisitorEntity;
import global.__GlobalWaitingQueue;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.GetCurrentTime;
import utils.XmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * @Description: OMEventListener线程中的execute方法实例，用来监听OM设备的事件汇报
 *              OM系统时间汇报的详细介绍查看 http://app.newrocktech.com/apiGuide/chapter5/monitor/content.html
 */
public class OMEventListenerExecutor implements Runnable{

    private Socket clientSocket;

    public OMEventListenerExecutor(){}

    public OMEventListenerExecutor(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void execute(){
        System.out.println("[*] "+GetCurrentTime.formatedTime()+"mythreadexecutor.OMEventListenerExecutor-execute()");
        System.out.println("    正在监听OM设备的事件汇报...");

        InputStream inputStream = null;

        try{

            OMConfigureDao omConfigureDao = new OMConfigureDaoImpl();
            OMTransferDao omTransferDao = new OMTransferDaoImpl();

            inputStream = clientSocket.getInputStream();

            byte[] bytes = new byte[2048];
            int len = inputStream.read(bytes);
            String str = "";
            if(len >= 0)
                str = new String(bytes, 0, len);
            //System.out.println(str);

            int xmlStartIndex = str.indexOf("<");
            int xmlEndIndex = str.lastIndexOf(">");
            String resultStr = str.substring(xmlStartIndex, xmlEndIndex+1);
            //System.out.println(resultStr);


            Document document = XmlParser.stringXmlParser(resultStr);
            Element rootElement = document.getRootElement();

            //监听事件Event
            if(rootElement.getName().equals("Event")){

                //测试OM20设备能否访问到阿里云的OMServer，收到OM20设备的报文则写点东西进数据库
//                Connection connection = MyMysql.getConnection();
//                String sql = "insert into temp values(\"11\")";
//                PreparedStatement ps=null;
//                try{
//                    ps=connection.prepareStatement(sql);
//                    ps.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }

                String attr = rootElement.attributeValue("attribute");

                //监听到系统启动事件
                if(attr.contains("BOOTUP")){
                    System.out.println("[*] "+GetCurrentTime.formatedTime()+"mythreadexecutor.OMEventListenerExecutor-execute()");
                    System.out.println("    OM Event:BOOTUP 收到OM事件：OM系统启动");
                    omConfigureDao.setExtGroup();
                }
                //来电前控制事件
                else if(attr.contains("INVITE")){
                    System.out.println("[*] "+GetCurrentTime.formatedTime()+"mythreadexecutor.OMEventListenerExecutor-execute()");
                    System.out.println("    OM Event:INVITE 收到OM事件：外线来电-来电前控制");
                    String visitorid = rootElement.element("visitor").attributeValue("id");

                    //直接把所有来电请求转接到分机组队列group1(坐席)中
                    omTransferDao.QueueExtGroup(Integer.valueOf(visitorid), 1);

                }
                //分机响铃事件
                else if(attr.contains("RING")){
                    System.out.println("[*] "+GetCurrentTime.formatedTime()+"mythreadexecutor.OMEventListenerExecutor-execute()");
                    System.out.println("    OM Event: RING 收到分机事件：分机响铃");

                    int cnt = 0;
                    String[] strArray = new String[2];
                    for (Iterator i = rootElement.elementIterator(); i.hasNext(); ){
                        Element secondElement = (Element) i.next();
                        if(secondElement.getName().equals("ext") || secondElement.getName().contains("ext")){
                            //System.out.println(secondElement.attributeValue("id"));
                            strArray[cnt++] = secondElement.attributeValue("id");
                        }
                    }
                    //分机呼分机的情况
                    if(cnt == 2){
                        //不用处理
                        //VisitorEntity visitorEntity =  new VisitorEntity();
                        //visitorEntity.setFrom(strArray[0]);
                        //visitorEntity.setExtid(Integer.valueOf(strArray[1]));
                        //__GlobalWaitingQueue.getGlobalWaitingQueue().add(visitorEntity);

                        //System.out.println("***********"+ __GlobalWaitingQueue.getGlobalWaitingQueue().size());


                        //WebsocketPool.sendMessageToOneWsEndPoint("227", "dasfsadgsag");
                    }
                    //来电呼分机的情况
                    else if(cnt == 1){
                        VisitorEntity visitorEntity =  new VisitorEntity();
                        rootElement.element("ext");
                        rootElement.element("visitor");
                    }

                }
                //分机应答事件
                else if(attr.contains("ANSWER")){
                    System.out.println("[*] "+GetCurrentTime.formatedTime()+"mythreadexecutor.OMEventListenerExecutor-execute()");
                    System.out.println("    OM Event:ANSWER 收到OM事件：分机应答");

                    int cnt = 0;
                    String[] strArray = new String[2];
                    for (Iterator i = rootElement.elementIterator(); i.hasNext(); ){
                        Element secondElement = (Element) i.next();
                        if(secondElement.getName().equals("ext") || secondElement.getName().contains("ext")){
                            //System.out.println(secondElement.attributeValue("id"));
                            strArray[cnt++] = secondElement.attributeValue("id");
                        }
                    }
                    if(cnt == 2){ //分机拨分机的情况
//                        for(int i = 0; i < __GlobalWaitingQueue.getGlobalWaitingQueue().size(); i++){
//                            VisitorEntity visitorEntity = __GlobalWaitingQueue.getGlobalWaitingQueue().get(i);
//                            if(visitorEntity.getFrom().equals(strArray[0])
//                                    && visitorEntity.getExtid()==Integer.valueOf(strArray[1])){
//                                //__GlobalWaitingQueue.getGlobalWaitingQueue().remove(visitorEntity);
//
//
//
//                                System.out.println("***********"+ __GlobalWaitingQueue.getGlobalWaitingQueue().size());
//                            }
//                        }



                    }

                }

            }

        }
        catch (IOException e1){
            e1.printStackTrace();
        }

    }

    @Override
    public void run(){
        execute();
    }
}
