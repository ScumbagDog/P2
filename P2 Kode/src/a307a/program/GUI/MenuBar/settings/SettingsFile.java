package a307a.program.GUI.MenuBar.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class SettingsFile {
    private static Document ReadFile(){
        try{
            File windowSettings = new File("options.xml");
            DocumentBuilderFactory settingsFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder settingsBuilder = settingsFactory.newDocumentBuilder();
            return settingsBuilder.parse(windowSettings);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static Element getInformation(Document document){
        NodeList nList = document.getElementsByTagName("window");
        Node nNode = nList.item(0);
        return (Element) nNode;
    }

    public static String AccessSettings(String string){
            Document settingsDoc = SettingsFile.ReadFile();
            Element eElement = SettingsFile.getInformation(settingsDoc);

            return eElement.getAttribute(string);
    }

    public static void EditSettings(String setting, String value){
        Document settingsDoc = SettingsFile.ReadFile();
        Element eElement = SettingsFile.getInformation(settingsDoc);

        eElement.setAttribute(setting, value);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(settingsDoc);
            StreamResult result = new StreamResult(new File("options.xml"));
            transformer.transform(source, result);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
