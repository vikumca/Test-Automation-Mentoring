package com.epam.automation.report;

import com.epam.automation.util.Components;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class LogEventListener implements WebDriverEventListener {
    private By lastFindBy;

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        Logger.info("WebDriver navigating to:'"+url+"'");
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        String locator = webElement.toString().split("-> ")[1];
        String elementName = Components.getElementName(locator.substring(0, locator.length() - 1).split(": ")[1]);
        Logger.info("WebDriver clicking on: '"+ (elementName == null? locator: elementName) +"'");

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable error, WebDriver webDriver) {
        if (error.getClass().equals(NoSuchElementException.class)){
            Logger.error("WebDriver error: Element not found "+lastFindBy);
        } else if(error.getClass().equals(StaleElementReferenceException.class)){
            Logger.error("Stale element exception:");
        } else if(error.getClass().equals(UnhandledAlertException.class)) {
            Logger.error("Alert exception: ");
        } else {
            Logger.error("WebDriver error:" + error);
        }
    }
}
