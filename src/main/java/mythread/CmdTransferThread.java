package mythread;

import mythreadexecutor.cmdtransfermodule.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CmdTransferThread implements Runnable{

    public CmdTransferThread(){

    }

    public static void start(){
        Thread thread = new Thread(new CmdTransferThread());
        thread.setName("CmdTransferThread");
        thread.start();
    }

    @Override
    public void run(){
        System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " CmdTransferThread 线程启动...");
        Executor executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new CmdTransferExecutor());
    }

}
