package com.epam.automation.util;

import org.apache.commons.collections.BidiMap;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class Components {

    private static BidiMap locatorsMap;

    public static String getElementName(String how) {
        return (String)locatorsMap.getKey(how);
    }
}
