package mythreadexecutor;

import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.MyXmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: OMEventListener线程中的execute方法实例，用来监听OM设备的事件汇报
 *              OM系统时间汇报的详细介绍查看 http://app.newrocktech.com/apiGuide/chapter5/monitor/content.html
 */
public class OMEventListenerExecutor implements Runnable{

    private Socket clientSocket;

    public OMEventListenerExecutor(){

    }

    public OMEventListenerExecutor(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void execute(){
        System.out.println("[*] "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 正在监听OM设备的事件汇报...");

        InputStream inputStream = null;

        try{
            inputStream = clientSocket.getInputStream();

            byte[] bytes = new byte[2048];
            int len = inputStream.read(bytes);
            String str = "";
            if(len >= 0)
                str = new String(bytes, 0, len);

            int xmlStartIndex = str.indexOf("<");
            int xmlEndIndex = str.lastIndexOf(">");
            String resultStr = str.substring(xmlStartIndex, xmlEndIndex+1);
            System.out.println(resultStr);

//
//            Document document = MyXmlParser.stringXmlParser(resultStr);
//            Element rootElement = document.getRootElement();
//            if(rootElement.getName().equals("Event")){
//                String attr = rootElement.attributeValue("attribute");
//                if(attr.equals("BOOTUP")){
//                    System.out.println("[*] "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" OM Event:BOOTUP 收到OM事件：OM系统启动");
//                }
//                else if(attr.equals("INVITE")){
//                    System.out.println("[*] "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" OM Event:INVITE 收到OM事件：中继来电");
//                    OMTransferDao omTransferDao = new OMTransferDaoImpl();
//                    //omTransferDao.QueueExt()
//
//
//                }
//            }
//




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
