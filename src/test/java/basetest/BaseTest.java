package basetest;

import com.epam.automation.config.Constants;
import com.epam.automation.driver.DriverSetUp;
import com.epam.automation.report.Logger;
import com.epam.automation.util.Assertions;
import com.epam.automation.util.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class BaseTest {

    protected WebDriver driver;
    protected Assertions assertions;

    @BeforeClass(alwaysRun = true)
    public void init() {
        try {
            assertions = new Assertions();
            Logger.info("================================ TEST STARTS NOW  =========================================");
        } catch (Exception e) {
            System.out.println("Exceptions:  " + e);
        }
    }

    @BeforeClass
    public void startBrowser() {
        DriverSetUp.initializeDriver(Config.getConfigProperty(Constants.BROWSER));
        driver = DriverSetUp.getDriver();

    }

    @AfterClass
    public void stopBrowser(){
        if ( driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
