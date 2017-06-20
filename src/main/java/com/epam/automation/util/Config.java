package com.epam.automation.util;

import com.epam.automation.config.Constants;
import com.epam.automation.exception.FrameworkExceptions;
import com.epam.automation.report.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class Config {

    private static Map<String, String> configValues = null;

    private Config()  {
    }

    static {
        configValues = new HashMap<>();
        try {
            readConfigContent();
        } catch (Exception e) {
            Logger.error("Error reading config file");
        }
    }

    /**
     * Gets the config content.
     *
     * @return the config content
     * @throws Exception the exception
     */
    private static void readConfigContent()  {
        Logger.trace("Executing the function to read the Config.xml file content mentioned as:" + Constants.CONFIGFILEPATH);
        Logger.info("Reading the Configuration file");
        File file = new File(Constants.CONFIGFILEPATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList configLines = document.getElementsByTagName("Config");
            for (int counter = 0; counter < configLines.getLength(); counter++) {
                Node configLine = configLines.item(counter);
                if (configLine.hasAttributes()) {
                    NamedNodeMap nameValuePair = configLine.getAttributes();
                    Logger.trace("Fetching for the name and value attribute values from the Coniguration file");
                    configValues.put(nameValuePair.getNamedItem("name").getNodeValue().toUpperCase(), nameValuePair.getNamedItem("value").getNodeValue());
                }
            }
            Logger.info("Completed the reading of the Configuration file");
        } catch (Exception e) {
            Logger.error("Exception occured while reading the contents from Configuration file. The excetpion messge is:" + e);
            //throw new FrameworkExceptions("An exception occured while getting the content from config file: " + e.getMessage());
        }
    }

    /**
     * Gets the config property.
     *
     * @param configName the config name
     * @return the config property
     * @throws Exception the exception
     */
    public static String getConfigProperty(String configName)  {
        Logger.trace("Try to fetch the content form Configuration file with key" + configName);
        String configValue = configValues.get(configName.toUpperCase());
        Logger.trace("Fetching the value from the Configuration file with key '" + configName + "' and the result is '" + configValue + "'");
        if (null == configValue) {
            Logger.error("The specified '" + configName + "'configuration property is not available");
        }
        return configValue;
    }
}
