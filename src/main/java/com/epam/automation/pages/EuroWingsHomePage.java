package com.epam.automation.pages;

import com.epam.automation.config.Constants;
import com.epam.automation.report.Logger;
import com.epam.automation.strategy.TitleValidationStrategy;
import com.epam.automation.util.CommonUtility;
import com.epam.automation.util.Config;
import com.epam.automation.util.Waits;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class EuroWingsHomePage extends Page {

    @FindBy(css = "input.originName")
    private WebElement departureAirportInputTextFieldHomePage;

    @FindBy(xpath = "//input[@name='origin' and @value = 'LHR']")
    private WebElement departureAirportInputFieldHomePage;

    @FindBy(css = "input.destinationName")
    private WebElement destinationAirportInputTextFieldHomePage;

    @FindBy(xpath = "//input[@name='destination' and @value = 'FCO']")
    private WebElement destinationAirportInputFieldHomePage;

    @FindBy(css = "input.outboundDateTextInput")
    private WebElement outGoingFlightInputFieldHomePage;

    @FindBy(css = "input.inboundDateTextInput")
    private WebElement returnFlightInputFieldHomePage;

    @FindBy(css = "div.passengerFieldContainer")
    private WebElement passengerSelectDivHomePage;

    @FindBy(xpath = "//button[@type='submit' and text()='Search for flight' and @data-trigger-click = 'submitCompactSearch']")
    private WebElement searchForFlightButtonHomePage;

    @FindBy(xpath = "//li[@data-tlc = 'LHR' and @data-text = 'London Heathrow']")
    private WebElement departureAirportListHomePage;

    @FindBy(xpath = "//span[contains(., 'Rome Fiumicino')]/descendant::strong[contains(text(), 'Rome')]")
    private WebElement destinationAirportListHomePage;

    public EuroWingsHomePage() {
        PageFactory.initElements(driver, this);
    }

    public EuroWingsHomePage open() {
        String baseURL = Config.getConfigProperty(Constants.BASEURL);
        driver.get(baseURL);
        Logger.info("Navigated to " + baseURL + " link");
        return this;
    }

    @Override
    protected void setPageProperties() {
        this.pageTitle = Constants.EUROWingsHOMEPAGETitle;
        this.pageValidationStrategy = new TitleValidationStrategy();
    }

    public boolean isDepartureAirportInputFieldVisible() { return departureAirportInputTextFieldHomePage.isDisplayed();}

    public boolean isDestinationAirportInputFieldVisible() { return destinationAirportInputTextFieldHomePage.isDisplayed(); }

    public boolean isOutGoingFlightInputFieldVisible(){
        return outGoingFlightInputFieldHomePage.isDisplayed();
    }

    public boolean isReturnFlightInputFieldVisible(){
        return returnFlightInputFieldHomePage.isDisplayed();
    }

    public boolean isPassengerSelectDivVisible(){
        return passengerSelectDivHomePage.isDisplayed();
    }

    public boolean isSearchForFlightButton(){
        return searchForFlightButtonHomePage.isDisplayed();
    }

    public EuroWingsHomePage setDepartureAirport(String strDepartureAirport){
        departureAirportInputTextFieldHomePage.sendKeys(strDepartureAirport);
        Logger.info("Entered "+ strDepartureAirport +" in Departure Airport");
        return new EuroWingsHomePage();
    }

    public EuroWingsHomePage setDestinationAirport(String strDestinationAirport){
        destinationAirportInputTextFieldHomePage.sendKeys(strDestinationAirport);
        Logger.info("Entered "+ strDestinationAirport +" in Destination Airport");
        return new EuroWingsHomePage();
    }

    public EuroWingsHomePage selectDepartureAirport(){
        departureAirportListHomePage.click();
        Logger.info("Clicked London Heathrow from the list of airports");
        return new EuroWingsHomePage();
    }

    public EuroWingsHomePage selectDestinationAirport(){
        destinationAirportListHomePage.click();
        Logger.info("Clicked Rome Fiumicino from the list of airports");
        return new EuroWingsHomePage();
    }

    public EuroWingsHomePage setOutgoingFlightDate(String outgoingFlightDate) {
        outGoingFlightInputFieldHomePage.sendKeys(CommonUtility.getDate("dd.MM.yy",outgoingFlightDate));
        Logger.info("Entered Outgoing Flight date to "+CommonUtility.getDate("dd.MM.yy",outgoingFlightDate));
        return new EuroWingsHomePage();
    }

    public EuroWingsHomePage setReturnFlightDate(String returnFlightDate) {
        returnFlightInputFieldHomePage.sendKeys(CommonUtility.getDate("dd.MM.yy",returnFlightDate));
        Logger.info("Entered return Flight date to "+CommonUtility.getDate("dd.MM.yy",returnFlightDate));
        return new EuroWingsHomePage();
    }

    public YourSearchPage clickSearchForFlight() {
        Waits.waitForElementValueToLoad(searchForFlightButtonHomePage, "ATTRIBUTE", "value");
        searchForFlightButtonHomePage.click();
        Logger.info("Clicked on Search for Flight Button");
        return new YourSearchPage();
    }

    public String getDepartureAirportValue() {
        Waits.explicitWait();
        return departureAirportInputFieldHomePage.getAttribute("value");


    }

    public String getDestinationAirportValue(){
        return destinationAirportInputFieldHomePage.getAttribute("value");
    }

    public String getText(){
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return jQuery(arguments[0]).text();", departureAirportInputFieldHomePage);
    }
}
