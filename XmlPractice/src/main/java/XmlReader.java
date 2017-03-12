import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlReader {

	public static void main(String args[]) {
		InputStream res = null;
		SAXReader reader = new SAXReader();

		//更新xml node
		try {
			res = getInputStream("/addOffer.xml");
			Document document = reader.read(res);
			// 取得XML Root Node
			Element root = document.getRootElement();
			setElementByName("productID", root, "aavv");
			System.out.println(document.asXML());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//取得list資料
		try {
			res = getInputStream("/getNotification.xml");
			Document document = reader.read(res);
			// 取得XML Root Node
			Element root = document.getRootElement();
			Element element = getElementsByName("notification", root, null).get(0);
			List<Element> msgs = getElementsByName("notifyMessage", element, null);
			for (Element ele : msgs) {
				System.out.println(ele.getText());
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//取得單一Element
	public static Element getElementByName(String name, Element parent) {

		Element element = null;
		for (Iterator i = parent.elementIterator(); i.hasNext();) {
			Element current = (Element) i.next();
			if (current.getName().equalsIgnoreCase(name)) {
				element = current;
			}
		}
		
		return element;
	}
	
	//取得elementList
	public static List<Element> getElementsByName(String name, Element parent, List<Element> elementList) {
		if (elementList == null)
			elementList = new ArrayList<Element>();

		for (Iterator i = parent.elementIterator(); i.hasNext();) {
			Element current = (Element) i.next();
			if (current.getName().equalsIgnoreCase(name)) {
				elementList.add(current);
			}

			getElementsByName(name, current, elementList);
		}
		return elementList;
	}
	
	//更新element text
	public static void setElementByName(String name, Element parent, String text){
		for (Iterator i = parent.elementIterator(); i.hasNext();) {
			Element current = (Element) i.next();
			if (current.getName().equalsIgnoreCase(name)) {
				current.setText(text);
				break;
			}
			
			setElementByName(name, current, text);
		}		
	}

	private static InputStream getInputStream(String file) {
		return XmlReader.class.getClass().getResourceAsStream(file);
	}
}
