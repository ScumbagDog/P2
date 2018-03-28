package a307a.program.GUI.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SettingsFile {
    public static String AccessSettings(String string){
        try{
            File windowSettings = new File("options.xml");
            DocumentBuilderFactory settingsFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder settingsBuilder = settingsFactory.newDocumentBuilder();
            Document settingsDoc = settingsBuilder.parse(windowSettings);
            settingsDoc.getDocumentElement().normalize();

            NodeList nList = settingsDoc.getElementsByTagName("window");

            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;

            return eElement.getAttribute(string);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
