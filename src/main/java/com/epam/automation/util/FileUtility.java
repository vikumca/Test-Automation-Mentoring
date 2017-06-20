package com.epam.automation.util;

import java.io.File;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class FileUtility {

    private FileUtility(){}

    public static String getProjectRootPath() {
        return System.getProperty("user.dir");
    }

    // Get File Separated Path
    public static String getFileSeparatedPath(String slashedPath) {
        String fullPath = getProjectRootPath() + File.separator + slashedPath;
        return fullPath.replace("/", File.separator);
    }
}
