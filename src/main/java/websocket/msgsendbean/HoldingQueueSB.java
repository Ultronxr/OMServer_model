package websocket.msgsendbean;

import entity.VisitorEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class HoldingQueueSB implements Serializable {
    private ArrayList<VisitorEntity> holdingQueue;

    public HoldingQueueSB(){

    }

    public HoldingQueueSB(ArrayList<VisitorEntity> hq){
        this.holdingQueue = hq;
    }

    public ArrayList<VisitorEntity> getHoldingQueue() {
        return holdingQueue;
    }

    public void setHoldingQueue(ArrayList<VisitorEntity> holdingQueue) {
        this.holdingQueue = holdingQueue;
    }
}
