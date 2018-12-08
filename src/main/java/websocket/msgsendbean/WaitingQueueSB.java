package websocket.msgsendbean;

import entity.VisitorEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class WaitingQueueSB implements Serializable {

    private ArrayList<VisitorEntity> waitingQueue;

    public WaitingQueueSB(){

    }

    public WaitingQueueSB(ArrayList<VisitorEntity> wq){
        this.waitingQueue = wq;
    }

    public ArrayList<VisitorEntity> getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(ArrayList<VisitorEntity> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }
}
