package com.epam.automation.util;

import com.epam.automation.config.Constants;
import com.epam.automation.driver.DriverSetUp;
import com.epam.automation.exception.FrameworkExceptions;
import com.epam.automation.report.Logger;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.epam.automation.util.JSExecutor.getJavaScriptExec;


/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class Waits {

    public static final long defaultTimeout = 5000;
    private static int waitTime = 0;
    private static WebDriver driver = DriverSetUp.getDriver();

    private Waits(){}

    // Fluent Wait
    public static WebElement fluentWait(final WebElement element, long duration) throws FrameworkExceptions {
        try {
            return new FluentWait<>(driver).withTimeout(duration, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .until(new Function<WebDriver, WebElement>() {
                        @Override
                        public WebElement apply(WebDriver input) {
                            boolean isPresent = element.isDisplayed() && element.isEnabled();
                            if (isPresent) {
                                return element;
                            } else {
                                return null;
                            }

                        }
                    });
        } catch (Exception e) {
            throw new FrameworkExceptions("Element: "+element+" not found"+e);
        }
    }

    // Wait for the Page to load
    public static boolean waitForPageLoadJS() {
        try{
            new WebDriverWait(driver, getWaitTime())
                    .until(new Predicate<WebDriver>() {
                               @Override
                               public boolean apply(WebDriver webDriver) {
                                   return ("complete").equals(getJavaScriptExec().executeScript("return document.readyState"));
                               }
                           }

                    );
            return ("complete").equals(getJavaScriptExec().executeScript("return document.readyState"));
        }
        catch (Exception e)
        {
            Logger.error("Page did not load");
            return false;
        }
    }

    // Get Wait Time
    public static int getWaitTime()  {
        if(waitTime == 0)
            waitTime = Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAITTIME));
        return waitTime;
    }

    public static boolean waitForElementValueToLoad(WebElement element, String strType, String strAttribute)
    {
        boolean blnStatus = false;
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.pollingEvery(1,  TimeUnit.SECONDS);
            wait.withTimeout(Integer.parseInt(Config.getConfigProperty(Constants.ELEMENT_WAITTIME)), TimeUnit.SECONDS);
            Function<WebDriver, Boolean> function = new Function<WebDriver,Boolean>(){
                public Boolean apply(WebDriver driver)
                {
                    switch (strType){
                        case "ATTRIBUTE":
                            if(!element.getAttribute(strAttribute).isEmpty())
                                return true;
                            break;
                        case "ISDISPLAYED":
                            if(element.isDisplayed())
                                return true;
                            break;

                        default:
                            Logger.info("In Default");
                    }
                    return false;
                }
            };
            blnStatus = wait.until(function);
        }
        catch(Exception e){
            Logger.info("Exception occurred in waitForElementValueToLoad method");
        }
        return blnStatus;
    }

    public static void explicitWait()
    {
        try{
            Thread.sleep(defaultTimeout);
        }
        catch (Exception e)
        {
            Logger.error("Failed in explicitWait method"+e);
        }
    }

}
