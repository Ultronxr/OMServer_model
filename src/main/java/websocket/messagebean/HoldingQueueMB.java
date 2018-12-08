package websocket.messagebean;

import entity.VisitorEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class HoldingQueueMB implements Serializable {
    private ArrayList<VisitorEntity> holdingQueue;

    public HoldingQueueMB(){

    }

    public HoldingQueueMB(ArrayList<VisitorEntity> hq){
        this.holdingQueue = hq;
    }

    public ArrayList<VisitorEntity> getHoldingQueue() {
        return holdingQueue;
    }

    public void setHoldingQueue(ArrayList<VisitorEntity> holdingQueue) {
        this.holdingQueue = holdingQueue;
    }
}
