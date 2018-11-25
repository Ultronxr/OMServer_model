package XMLTest;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DOM4J {

    public static void main(String[] args) {
        try{
            File file = new File("D:\\所有文件夹\\STUDY\\1计算机语言\\PROJECTS\\IntelliJ_IDEA_workspace\\OMServer_model\\src\\test\\XMLTest\\test.xml");
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(file);
            Element firstElement = document.getRootElement(); //根节点
            System.out.println(firstElement.getName());
            System.out.println(firstElement.attributeValue("attribute"));

            for (Iterator i = firstElement.elementIterator(); i.hasNext(); ) {
                Element secondElement = (Element) i.next();
                for (Iterator j = secondElement.elementIterator(); j.hasNext(); ) {
                    Element thirdElement = (Element) j.next();
                    System.out.println(thirdElement.getName() + ":" + thirdElement.getText());
                }
            }

        }
        catch(DocumentException e1){
            e1.printStackTrace();
        }




    }

}
