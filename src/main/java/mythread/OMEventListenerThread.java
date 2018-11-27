package mythread;

import mythreadexecutor.OMEventListenerExecutor;
import utils.MyOMConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/**
 * @Description: OMEventListener线程，调用OMEventListenerExecutor实例方法，用来监听OM设备的事件汇报
 *              OM系统的监听配置详细查看 http://app.newrocktech.com/apiGuide/chapter5/monitor/config.html
 *              OM系统事件汇报的详细介绍查看 http://app.newrocktech.com/apiGuide/chapter5/monitor/content.html
 */
public class OMEventListenerThread implements Runnable{

    private int omListenPort; //OM设备汇报事件的端口号

    public OMEventListenerThread(){
        this.omListenPort = MyOMConfig.getListenPort();
    }

    public static void start(){
        Thread thread = new Thread(new OMEventListenerThread());
        thread.setName("OMEventListenerThread");
        thread.start();
    }

    @Override
    public void run(){
        System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " OMEventListenerThread 线程启动...");

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Executor executorService = null;
        boolean flag = true;

        try{
            serverSocket = new ServerSocket(this.omListenPort);
            executorService = Executors.newFixedThreadPool(1);

            while(flag){
                clientSocket = serverSocket.accept();

                System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                        + " OMEventListenerThread 线程获取与OM设备的socket连接："+clientSocket.getInetAddress()+":"+clientSocket.getPort());
                executorService.execute(new OMEventListenerExecutor(clientSocket));

                Thread.sleep(300);
            }

            if(clientSocket != null) clientSocket.close();
            if(serverSocket != null) serverSocket.close();

        }
        catch (IOException e1){
            e1.printStackTrace();
        }
        catch (InterruptedException e2){
            e2.printStackTrace();
        }

    }
}
