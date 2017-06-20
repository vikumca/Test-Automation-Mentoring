package com.epam.automation.pages;

import com.epam.automation.config.Constants;
import com.epam.automation.strategy.TitleValidationStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class YourSearchPage extends Page {

    @FindBy(xpath = "//input[@name='origin'  and @value ='LHR']")
    private WebElement departureAirportYourSearchPage;

    @FindBy(xpath = "//input[@name='destination'  and @value ='LHR']")
    private WebElement destinationAirportYourSearchPage;

    @FindBy(css = "input.outboundDateTextInput")
    private WebElement outGoingFlightInputFieldYourSearchPage;

    @FindBy(css = "input.inboundDateTextInput")
    private WebElement returnFlightInputFieldYourSearchPage;

    @FindBy(css = "div.passengerFieldContainer")
    private WebElement passengerSelectDivYourSearchPage;

    @FindBy(xpath = "//button[@type='submit' and text()='Change search']")
    private WebElement changeSearchButtonYourSearchPage;

    @FindBy(xpath = "//input[@name='origin' and @value = 'LHR']")
    private WebElement departureAirportInputFieldYourSearchPage;

    @FindBy(xpath = "//input[@name='destination' and @value = 'FCO']")
    private WebElement destinationAirportInputFieldYourSearchPage;

    public YourSearchPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isPassengerSelectVisibleOnYourSearchPage(){
        return passengerSelectDivYourSearchPage.isDisplayed();
    }

    public boolean isChangeSearchButtonVisible(){
        return changeSearchButtonYourSearchPage.isDisplayed();
    }

    public String getOutgoingFlightDate(){
        return outGoingFlightInputFieldYourSearchPage.getAttribute("value");
    }

    public String getReturnFlightDate(){
        return returnFlightInputFieldYourSearchPage.getAttribute("value");
    }

    public String getDepartureAirportValue(){
        return departureAirportInputFieldYourSearchPage.getAttribute("value");
    }

    public String getDestinationAirportValue(){
        return destinationAirportInputFieldYourSearchPage.getAttribute("value");
    }

    @Override
    protected void setPageProperties() {
        //todo
    }
}
