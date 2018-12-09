import com.alibaba.fastjson.JSON;
import entity.VisitorEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.MySerializeUtil;
import utils.MyXmlParser;
import websocket.WebsocketPool;

import java.nio.ByteBuffer;
import java.util.*;

public class Test {

    public static void main(String[] args) {

//        String resultStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
//                "<Event attribute=\"INVITE\">\n" +
//                "   <trunk id=\"02161204000\" />\n" +
//                "   <visitor id=\"31\" from=\"13012345678\" to=\"02161204000\" callid=\"36895\" />\n" +
//                "</Event>";
//        Document document = MyXmlParser.stringXmlParser(resultStr);
//        Element rootElement = document.getRootElement();
//        //System.out.println(rootElement.attributeValue("visitor"));
//        String temp = rootElement.element("visitor ").attributeValue("from");
//        System.out.println(temp);
        String extid = "227";
        WebsocketPool.sendMessageToOneWsEndPoint(extid, "sdafsadfasdsgadf");
        System.out.println(WebsocketPool.getWsPoolSize());
    }

}