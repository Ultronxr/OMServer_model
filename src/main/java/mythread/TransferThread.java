package mythread;

import mythreadexecutor.transfermodule.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TransferThread implements Runnable{

    public TransferThread(){

    }

    public static void start(){
        Thread thread = new Thread(new TransferThread());
        thread.setName("TransferThread");
        thread.start();
    }

    @Override
    public void run(){
        System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " TransferThread 线程启动...");
        Executor executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new TransferExecutor());
    }

}
