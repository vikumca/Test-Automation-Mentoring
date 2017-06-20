package com.epam.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class CarPage {
    @FindBy(name = "startlocation")
    private WebElement pickupCity;

    @FindBy(name = "endlocation")
    private WebElement dropOffCity;

    @FindBy(name = "pickupdate")
    private WebElement pickupDate;

    @FindBy(name = "dropoffdate")
    private WebElement dropOffDate;

    @FindBy(css = "input[type=submit]")
    private WebElement searchButton;

    public void setPickupCity(String location) {
        this.pickupCity.sendKeys(location);
    }

    public void setDropOffCity(String location) {
        this.dropOffCity.sendKeys(location);
    }

    public void setPickupDate(String date) {
        this.pickupDate.sendKeys(date);
    }

    public void setDropOffDate(String date) {
        this.dropOffDate.sendKeys(date);
    }

    public void search() {
        this.searchButton.click();
    }
}
