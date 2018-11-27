package global;

import entity.VisitorEntity;

public class GlobalAnsweringVisitor {

    private static VisitorEntity visitorEntity;

    public static VisitorEntity getVisitorEntity() {
        return visitorEntity;
    }

    public static void setVisitorEntity(VisitorEntity visitorEntity) {
        GlobalAnsweringVisitor.visitorEntity = visitorEntity;
    }
}
