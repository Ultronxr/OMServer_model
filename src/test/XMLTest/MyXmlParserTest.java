package XMLTest;

import org.dom4j.Document;
import org.dom4j.Element;
import utils.MyXmlParser;

import java.util.Iterator;

public class MyXmlParserTest {
    public static void main(String [] args){
        String stringXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Event attribute=\"RING \">\n" +
                "  <ext id=\"200\" />\n" +
                "  <ext id=\"208\" />\n" +
                "</Event>";

        Document document = MyXmlParser.stringXmlParser(stringXml);
        //System.out.println(document.getRootElement().getName());
        //System.out.println(document.getRootElement().attributeValue("attribute"));


        //Element element = document.getRootElement().element("ext");
        //System.out.println(element.attributeValue("id"));

        for (Iterator i = document.getRootElement().elementIterator(); i.hasNext(); ){
            Element secondElement = (Element) i.next();
            if(secondElement.getName().equals("ext") || secondElement.getName().contains("ext")){
                System.out.println(secondElement.attributeValue("id"));
            }
        }

    }


}
