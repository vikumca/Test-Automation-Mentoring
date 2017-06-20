package com.epam.automation.config;

import com.epam.automation.util.FileUtility;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class Constants {
    private Constants(){};

    public static final String CONFIGFILEPATH = FileUtility.getFileSeparatedPath("src/main/resources/Config.xml");
    public static final String BASEURL = "AppURL";
    public static final String ELEMENT_WAITTIME = "ElementWaitTime";
    public static final String BROWSER = "BrowserType";
    public static final String CHROME_DRIVER_PATH = "ChromeDriverPath";
    public static final String EUROWingsHOMEPAGETitle = "Eurowings - book cheap flights";


}
