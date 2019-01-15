package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @Description: XML解析工具类，使用DOM4J作为底层工具
 *
 */
public class XmlParser {

    public XmlParser(){

    }


    /**
     * @Description: 针对string字符串的xml解析器，返回解析后的Document对象
     *
     */
    public static Document stringXmlParser(String stringXml){
        Document document = null;
        try{
            document = DocumentHelper.parseText(stringXml);
        }
        catch (DocumentException e1){
            e1.printStackTrace();
        }

        return document;
    }



    /**
     * @Description: 针对文件的xml解析器，返回解析后的Document对象
     *
     */
    public static Document fileXmlParser(String filePath){
        File file = new File(filePath);
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try{
            document = saxReader.read(file);

        }catch (DocumentException e1){
            e1.printStackTrace();
        }

        return document;
    }


}
