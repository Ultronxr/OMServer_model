package global;

import entity.VisitorEntity;

import java.util.ArrayList;

public class __GlobalHoldingQueue {
    private static ArrayList<VisitorEntity> globalHoldingQueue;

    public static ArrayList<VisitorEntity> getGlobalHoldingQueue() {
        return globalHoldingQueue;
    }

    public static void setGlobalHoldingQueue(ArrayList<VisitorEntity> globalHoldingQueue) {
        __GlobalHoldingQueue.globalHoldingQueue = globalHoldingQueue;
    }
}
