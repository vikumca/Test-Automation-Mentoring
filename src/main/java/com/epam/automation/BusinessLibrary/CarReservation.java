package com.epam.automation.BusinessLibrary;

import com.epam.automation.TemplateMethodPatterns.Reservation;
import com.epam.automation.pages.CarPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class CarReservation extends Reservation {

    private final CarPage car;
    private final String pickup;
    private final String dropOff;
    private final String pickupDate;
    private final String dropOffDate;

    public CarReservation(WebDriver driver, String pickup, String dropoff, String pickupdate, String dropoffdate) {

        this.car = PageFactory.initElements(driver, CarPage.class);
        this.pickup = pickup;
        this.dropOff = dropoff;
        this.pickupDate = pickupdate;
        this.dropOffDate = dropoffdate;

        this.goTo(driver);
    }

    private void goTo(WebDriver driver) {
        driver.get("https://www.hotwire.com");
        driver.findElement(By.cssSelector("a[href=\"#CARS\"]")).click();
    }

    @Override
    public void setLocation() {
        car.setPickupCity(pickup);
        car.setDropOffCity(dropOff);
    }

    @Override
    public void setFromDate() {
        car.setPickupDate(pickupDate);
    }

    @Override
    public void setTillDate() {
        car.setDropOffDate(dropOffDate);
    }

    @Override
    public void search() {
        car.search();
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
