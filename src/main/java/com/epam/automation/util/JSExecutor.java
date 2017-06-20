package com.epam.automation.util;

import com.epam.automation.driver.DriverSetUp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class JSExecutor {

    private JSExecutor(){}

    public static JavascriptExecutor getJavaScriptExec() {
        return (JavascriptExecutor) DriverSetUp.getDriver();
    }



}
