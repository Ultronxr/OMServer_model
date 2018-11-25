package XMLTest;

import org.dom4j.Document;
import org.dom4j.Element;
import utils.MyXmlParser;

import java.util.Iterator;

public class MyXmlParserTest {
    public static void main(String [] args){
        String stringXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Assign\">\n" +
                "<ext lineid=\"Phone 1\">\n" +
                "    <id>001</id>\n" +
                "    <staffid>0000001</staffid>\n" +
                "    <mobile>15257845702</mobile>\n" +
                "    <group>1</group>\n" +
                "    <voicefile>busy</voicefile>\n" +
                "    <Call_Restriction>2</Call_Restriction>\n" +
                "    <Call_Pickup>no</Call_Pickup>\n" +
                "    <No_Disturb>no</No_Disturb>\n" +
                "    <Fwd_Type>1</Fwd_Type>\n" +
                "    <Fwd_Number>15257845702</Fwd_Number>\n" +
                "    <Fork>15257845702</Fork>\n" +
                "    <WAKEUP>00:00</WAKEUP>\n" +
                "    <record>on</record>\n" +
                "    <autoAnswer>yes </autoAnswer>\n" +
                "    <api>7</api>\n" +
                "</ext>\n" +
                "</Control>";

        Document document = MyXmlParser.stringXmlParser(stringXml);
        Element element = document.getRootElement().element("ext").element("mobile");

        System.out.println(element.getTextTrim());

    }


}
