package com.epam.automation.pages;

import com.epam.automation.BusinessLibrary.HotelReservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class HotelPage {

    @FindBy(xpath = "//input[@name='destination']")
    private WebElement city;

    @FindBy(xpath = ".//*[@id='startDate']")
    private WebElement checkin;

    @FindBy(xpath = ".//*[@id='endDate']")
    private WebElement checkout;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    public void setCity(String location) {
        this.city.sendKeys(location);
    }

    public void setCheckInDate(String date) {
        this.checkin.sendKeys(date);
    }

    public void setCheckOutDate(String date) {
        this.checkout.sendKeys(date);
    }

    public void search() {
        this.searchButton.click();
    }
}
