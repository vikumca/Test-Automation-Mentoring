package com.epam.automation.pages;

import com.epam.automation.driver.DriverSetUp;
import com.epam.automation.strategy.PageValidationStrategy;
import com.epam.automation.strategy.TitleValidationStrategy;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public abstract class Page {

    protected WebDriver driver;
    protected String pageTitle;
    protected PageValidationStrategy pageValidationStrategy;

    Page() {
        newInstance();
        pageValidationStrategy = new TitleValidationStrategy();
        setPageProperties();
    }

    private void newInstance() {
        driver = DriverSetUp.getDriver();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public boolean isCurrentPage() {
        return pageValidationStrategy.isCurrentPage(this);
    }

    protected abstract void setPageProperties();

}
