package Utils;

import com.chanjet.Locator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by yuxin
 */
public class XmlUtils {

    public static HashMap<String, Locator> readXmlDocument(String xmlPath, String pageName) throws DocumentException {
        HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
        locatorMap.clear();

        File file = new File(xmlPath);
        if (!file.exists()) {

            return locatorMap = null;
        }
        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取文件转换成document
        Document document = reader.read(file);
        // 获取根节点元素对象
        Element root = document.getRootElement();
        // 遍历
        for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
            Element page = (Element) i.next();

            if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {

                for (Iterator<?> l = page.elementIterator(); l.hasNext();) {

                    String type = null;
                    String timeOut = "3";
                    String value = null;
                    String locatorName = null;
                    Element locator = (Element) l.next();

                    for (Iterator<?> j = locator.attributeIterator(); j.hasNext();) {

                        Attribute attribute = (Attribute) j.next();
                        //System.out.println(" 12233" +attribute.getName());
                        if (attribute.getName().equals("type")) {
                            type = attribute.getValue();

                        } else if (attribute.getName().equals("timeOut")) {
                            timeOut = attribute.getValue();
                        } else {
                            value = attribute.getValue();
                        }
                    }

                    Locator temp = new Locator(value, Integer.parseInt(timeOut), getByType(type));

                    locatorName = locator.getText();
                    locatorMap.put(locatorName, temp);
                }
                continue;
            }
        }

        return locatorMap;

    }

    public static Locator.ByType getByType(String type) {
        Locator.ByType bytype = Locator.ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            bytype = Locator.ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            bytype = Locator.ByType.id;
        } else if (type.equalsIgnoreCase("name")) {
            bytype = Locator.ByType.name;
        } else if (type.equalsIgnoreCase("classname")) {
            bytype = Locator.ByType.className;
        } else if(type.equalsIgnoreCase("linkText")){
            bytype = Locator.ByType.linkText;
        } else if (type.equalsIgnoreCase("partLinkText")){
            bytype = Locator.ByType.partLinkText;
        } else if(type.equalsIgnoreCase("selector")){
            bytype=Locator.ByType.selector;
        }
        return bytype;

    }
}