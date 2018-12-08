import com.alibaba.fastjson.JSON;
import entity.VisitorEntity;
import utils.MySerializeUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

//        ByteBuffer bf = ByteBuffer.wrap(MySerializeUtil.serialize(new TempClass()));
//        byte[] bytes = new byte[bf.capacity()];
//        bf.get(bytes, 0, bytes.length);
//        System.out.println(MySerializeUtil.unserialize(bytes).toString());

        VisitorEntity ve = new VisitorEntity();
        ve.setExtid(999);
        ArrayList<VisitorEntity> waitingQueue = new ArrayList<>();
        waitingQueue.add(ve);

        System.out.println(JSON.toJSONString(waitingQueue));
    }

}