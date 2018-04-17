import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/a/?format=xml");
			InputStream stream = url.openStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);
			doc.getDocumentElement().normalize();

			NodeList nodeListRates = doc.getElementsByTagName("Rate");

			for (int s = 0; s < nodeListRates.getLength(); s++) {
				Node firstNode = nodeListRates.item(s);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstElement = (Element) firstNode;

					NodeList currencyElementList = firstElement.getElementsByTagName("Currency");
					Element currencyElement = (Element) currencyElementList.item(0);
					NodeList currencyGettingValue = currencyElement.getChildNodes();

					NodeList codeElementList = firstElement.getElementsByTagName("Code");
					Element codeElement = (Element) codeElementList.item(0);
					NodeList codeGettingValue = codeElement.getChildNodes();
					
					NodeList midElementList = firstElement.getElementsByTagName("Mid");
					Element midElement = (Element) midElementList.item(0);
					NodeList midGettingValue = midElement.getChildNodes();

					System.out.println();
					System.out.println("Nazwa waluty: " + ((Node) currencyGettingValue.item(0)).getNodeValue());
					System.out.println("Kod: " + ((Node) codeGettingValue.item(0)).getNodeValue());
					System.out.println("Sredni kurs: " + ((Node) midGettingValue.item(0)).getNodeValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
