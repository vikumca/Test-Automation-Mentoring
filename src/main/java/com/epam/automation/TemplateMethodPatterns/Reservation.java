package com.epam.automation.TemplateMethodPatterns;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public abstract class Reservation {

    abstract public void setLocation();
    abstract public void setFromDate();
    abstract public void setTillDate();
    abstract public void search();
    abstract public void sortByPrice();
    abstract public void chooseFirst();
    abstract public void pay();
    abstract public void printConfirmationNumber();

    public final void reserve(){
        setLocation();
        setFromDate();
        setTillDate();
        search();
        sortByPrice();
        chooseFirst();
        pay();
        printConfirmationNumber();
    }
}
