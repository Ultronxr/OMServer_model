package global;

import entity.VisitorEntity;

import java.util.ArrayList;

public class GlobalHoldingQueue {
    private static ArrayList<VisitorEntity> globalHoldingQueue;

    public static ArrayList<VisitorEntity> getGlobalHoldingQueue() {
        return globalHoldingQueue;
    }

    public static void setGlobalHoldingQueue(ArrayList<VisitorEntity> globalHoldingQueue) {
        GlobalHoldingQueue.globalHoldingQueue = globalHoldingQueue;
    }
}
