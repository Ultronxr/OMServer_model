package global;

import entity.VisitorEntity;

import java.util.ArrayList;

public class GlobalWaitingQueue {
    private static ArrayList<VisitorEntity> globalWaitingQueue = new ArrayList<>();

    public static ArrayList<VisitorEntity> getGlobalWaitingQueue() {
        return globalWaitingQueue;
    }

    public static void setGlobalWaitingQueue(ArrayList<VisitorEntity> globalWaitingQueue) {
        GlobalWaitingQueue.globalWaitingQueue = globalWaitingQueue;
    }
}
