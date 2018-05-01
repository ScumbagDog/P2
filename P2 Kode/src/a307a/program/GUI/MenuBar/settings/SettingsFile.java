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
    Document settingsDoc;
    Element eElement;
    TransformerFactory transformerFactory;
    Transformer transformer;
    DOMSource source;
    StreamResult result;
    NodeList nList;
    Node nNode;

    public SettingsFile() {
        settingsDoc = SettingsFile.ReadFile();
        nList = settingsDoc.getElementsByTagName("window");
        nNode = nList.item(0);
        eElement = SettingsFile.getInformation(nNode);
        transformerFactory = TransformerFactory.newInstance();
        try{transformer = transformerFactory.newTransformer();}catch(Exception e){e.printStackTrace();}
        source = new DOMSource(settingsDoc);
        result = new StreamResult(new File("options.xml"));
    }

    //The code for editing the settings was made into its own method due to it being used multiple times.
    public void EditSettings(String setting, String value){
        try {
            eElement.setAttribute(setting, value);
            transformer.transform(source, result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //The purpose of this method is to read the settings from the 'start' method in MainGUI.
    public String AccessSettings(String string){
        return eElement.getAttribute(string);
    }

    //This method was made to create elements used to access and edit the settings,
    //which two of the methods in this class needs to do.
    private static Element getInformation(Node nNode){
        return (Element) nNode;
    }

    //This method was purely made to make the public methods more readable by abstracting some of the code.
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






}
