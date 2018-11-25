package mainmethod;

import mythread.CmdTransferThread;
import mythread.OMEventListenerThread;

public class MainMethod {

    /**
     * @Description: 测试用的main入口方法
     *
     */
    public static void main(String[] args){

        //CmdTransferThread.start();
        OMEventListenerThread.start();

    }

    /**
     * @Description: MyListener监听器调用的方法
     *
     */
    public static void StartMyListener(){

        CmdTransferThread.start();
        OMEventListenerThread.start();

    }

}
