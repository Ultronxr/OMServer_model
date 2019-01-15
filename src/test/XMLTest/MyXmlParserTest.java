package XMLTest;

import org.dom4j.Document;
import org.dom4j.Element;
import utils.XmlParser;

public class MyXmlParserTest {
    public static void main(String [] args){
//        String stringXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
//                "<Event attribute=\"RING \">\n" +
//                "  <ext id=\"200\" />\n" +
//                "  <ext id=\"208\" />\n" +
//                "</Event>";
//
//        Document document = XmlParser.stringXmlParser(stringXml);
        //System.out.println(document.getRootElement().getName());
        //System.out.println(document.getRootElement().attributeValue("attribute"));


        //Element element = document.getRootElement().element("ext");
        //System.out.println(element.attributeValue("id"));

//        for (Iterator i = document.getRootElement().elementIterator(); i.hasNext(); ){
//            Element secondElement = (Element) i.next();
//            if(secondElement.getName().equals("ext") || secondElement.getName().contains("ext")){
//                System.out.println(secondElement.attributeValue("id"));
//            }
//        }

        String str = "GET /ring.htm HTTP/1.1\n" +
                "Host: 192.168.0.150\n" +
                "Cache-Control: no-cache\n" +
                "Content-Length: 114\n" +
                "\n" +
                "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Event attribute=\"RING\">\n" +
                "  <ext id=\"227\" />\n" +
                "  <outer to=\"228\" />\n" +
                "</Event>";
        int xmlStartIndex = str.indexOf("<");
        int xmlEndIndex = str.lastIndexOf(">");
        String resultStr = str.substring(xmlStartIndex, xmlEndIndex+1);
        System.out.println(resultStr);

        Document document = XmlParser.stringXmlParser(resultStr);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
    }

}
