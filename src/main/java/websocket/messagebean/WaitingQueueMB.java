package websocket.messagebean;

import entity.VisitorEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class WaitingQueueMB implements Serializable {

    private ArrayList<VisitorEntity> waitingQueue;

    public WaitingQueueMB(){

    }

    public WaitingQueueMB(ArrayList<VisitorEntity> wq){
        this.waitingQueue = wq;
    }

    public ArrayList<VisitorEntity> getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(ArrayList<VisitorEntity> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }
}
