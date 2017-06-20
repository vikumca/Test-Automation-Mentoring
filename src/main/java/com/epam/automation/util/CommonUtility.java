package com.epam.automation.util;

import com.epam.automation.report.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class CommonUtility {

    public static String getDate(String dateFormat, String strValue)  {
        try {
            int intDays;
            Calendar objCal = Calendar.getInstance();

            SimpleDateFormat objSdf = new SimpleDateFormat(dateFormat);
            objSdf.setTimeZone(TimeZone.getTimeZone("IST"));

            objCal.setTime(objSdf.parse(getSystemDate(dateFormat)));

            if (strValue.trim().toUpperCase().equalsIgnoreCase("CDATE_TODAY")) {
                strValue = objSdf.format(objCal.getTime());
            } else if (strValue.trim().toUpperCase().contains("CDATE_TODAY_")) {
                String[] arrValues = strValue.toUpperCase().split("DAY_");
                intDays = Integer.parseInt(arrValues[1]);
                objCal.add(Calendar.DATE, -intDays);
                strValue = objSdf.format(objCal.getTime());
            } else if (strValue.trim().toUpperCase().contains("CDATE_TODAY#")) {
                String[] arrValues = strValue.toUpperCase().split("DAY#");
                intDays = Integer.parseInt(arrValues[1]);
                objCal.add(Calendar.DATE, intDays);
                strValue = objSdf.format(objCal.getTime());
            }

        }catch (Exception e) {
            Logger.error("Failed in getDate() method of CommonUtil class ");
        }
        return strValue;
    }

    public static String getSystemDate(String dateFormat) {
        String strValue = "";
        try {
            Calendar objCal = Calendar.getInstance();
            objCal.setTime(new Date());
            SimpleDateFormat objSdf = new SimpleDateFormat(dateFormat);
            objSdf.setTimeZone(TimeZone.getTimeZone("IST"));
            strValue = objSdf.format(objCal.getTime());
        } catch(Exception e) {
            throw new RuntimeException("Exception occurred"+e);
        }
        return strValue;
    }
}
