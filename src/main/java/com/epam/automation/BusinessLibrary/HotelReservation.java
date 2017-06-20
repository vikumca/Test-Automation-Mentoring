package com.epam.automation.BusinessLibrary;

import com.epam.automation.TemplateMethodPatterns.Reservation;
import com.epam.automation.pages.HotelPage;
import com.epam.automation.util.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class HotelReservation extends Reservation {

    private final HotelPage hotel;
    private final String city;
    private final String checkin;
    private final String checkout;

    public HotelReservation(WebDriver driver, String city, String checkin, String checkout) {
        this.hotel = PageFactory.initElements(driver, HotelPage.class);
        this.city = city;
        this.checkin = checkin;
        this.checkout = checkout;

        this.goTo(driver);
    }

    private void goTo(WebDriver driver) {
        driver.get("https://www.hotwire.com/shop/?gccid=");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }


    @Override
    public void setLocation() {
        hotel.setCity(city);
    }

    @Override
    public void setFromDate() {
        hotel.setCheckInDate(checkin);
    }

    @Override
    public void setTillDate() {
        hotel.setCheckOutDate(checkout);
    }

    @Override
    public void search() {
        hotel.search();
    }

    @Override
    public void sortByPrice() {
        // TODO Auto-generated method stub
    }

    @Override
    public void chooseFirst() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pay() {
        // TODO Auto-generated method stub

    }

    @Override
    public void printConfirmationNumber() {
        // TODO Auto-generated method stub

    }


}
