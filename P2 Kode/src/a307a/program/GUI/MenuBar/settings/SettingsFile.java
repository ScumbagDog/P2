package a307a.program.GUI.MenuBar.settings;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SettingsFile {
    private Element eElement;
    private Transformer transformer;
    private DOMSource source;
    private StreamResult result;
    private int windowHeight, windowWidth;
    private Boolean windowFullscreen;

    public SettingsFile() {
        Document settingsDoc = SettingsFile.ReadFile();
        NodeList nList = settingsDoc.getElementsByTagName("window");
        Node nNode = nList.item(0);
        eElement = SettingsFile.getInformation(nNode);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        source = new DOMSource(settingsDoc);
        result = new StreamResult(new File("options.xml"));
        loadSettings();
    }

    //This method was made to create elements used to access and edit the settings,
    //which two of the methods in this class needs to do.
    private static Element getInformation(Node nNode) {
        return (Element) nNode;
    }

    //This method was purely made to make the public methods more readable by abstracting
    // some of the code.
    private static Document ReadFile() {
        try {
            File windowSettings = new File("options.xml");
            DocumentBuilderFactory settingsFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder settingsBuilder = settingsFactory.newDocumentBuilder();
            return settingsBuilder.parse(windowSettings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //The code for editing the settings was made into its own method due to it being used
    // multiple times.
    public void EditSettings(String setting, String value) {
        try {
            eElement.setAttribute(setting, value);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //The purpose of this method is to read the settings from the 'start' method in
    // MainGUI.
    public String AccessSettings(String string) {
        return eElement.getAttribute(string);
    }

    private void loadSettings() {
        windowWidth = Integer.parseInt(AccessSettings("width"));
        windowHeight = Integer.parseInt(AccessSettings("height"));
        windowFullscreen = Boolean.parseBoolean(AccessSettings("fullscreen"));
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public Boolean getIsWindowFullscreen() {
        return windowFullscreen;
    }
}
