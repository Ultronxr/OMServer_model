package global;

import entity.VisitorEntity;

public class __GlobalAnsweringVisitor {

    private static VisitorEntity visitorEntity;

    public static VisitorEntity getVisitorEntity() {
        return visitorEntity;
    }

    public static void setVisitorEntity(VisitorEntity visitorEntity) {
        __GlobalAnsweringVisitor.visitorEntity = visitorEntity;
    }
}
