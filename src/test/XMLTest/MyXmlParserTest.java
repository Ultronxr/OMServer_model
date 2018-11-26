package XMLTest;

import org.dom4j.Document;
import org.dom4j.Element;
import utils.MyXmlParser;

import java.util.Iterator;

public class MyXmlParserTest {
    public static void main(String [] args){
        String stringXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Event attribute=\"IDLE\">\n" +
                "  <ext id=\"230\" />\n" +
                "</Event>\n" +
                "<Auth>\n" +
                "   <nonce>55BB5523</nonce>\n" +
                "   <timestamp>1543150543</timestamp>\n" +
                "   <signature>5807b047e203d03d7d9ee290791dc8db</signature>\n" +
                "</Auth>";

        Document document = MyXmlParser.stringXmlParser(stringXml);
        //System.out.println(document.getRootElement().getName());
        //System.out.println(document.getRootElement().attributeValue("attribute"));
        //Element element = document.getRootElement().element("ext").element("mobile");

        //System.out.println(element.getTextTrim());

    }


}
