package mythreadexecutor;

import java.io.IOException;
import java.io.InputStream;
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
            //System.out.println(str);




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
