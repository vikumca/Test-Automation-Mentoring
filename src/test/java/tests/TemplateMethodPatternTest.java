package tests;

import com.epam.automation.BusinessLibrary.CarReservation;
import com.epam.automation.BusinessLibrary.HotelReservation;
import com.epam.automation.TemplateMethodPatterns.Reservation;
import com.epam.automation.config.Constants;
import com.epam.automation.report.Logger;
import com.epam.automation.util.Config;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vikas_Sharma on 6/20/2017.
 */
public class TemplateMethodPatternTest {

    private WebDriver driver;

    @Test(dataProvider = "reservations")
    public void reservationTest(Reservation r) {
        r.reserve();
    }

    @DataProvider
    public Object[][] reservations() {
        return new Object[][] {

                new Object[] {
                        new HotelReservation(driver, "Houston", "06/21/2017", "06/28/2017")
                },
//                new Object[] {
//                        new CarReservation(driver, "Houston", "Austin", "05/02/2017", "05/05/2017")
//                }
        };
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", Config.getConfigProperty(Constants.CHROME_DRIVER_PATH));
        ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
        String commandSwitches = "WebDriverCommandSwitch";
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        if (!commandSwitches.isEmpty() || commandSwitches.contains("--")) {
            Logger.info("User had specified [" + commandSwitches + "] command switch for Chrome Browser");
            ChromeOptions options = new ChromeOptions();
            String[] commandList = commandSwitches.split(",");
            for (String command : commandList) {
                options.addArguments(command);
                options.addArguments("--test-type");
                options.addArguments("chrome.switches",	"--disable-extensions");
                options.addArguments("--start-maximized");
            }
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new org.openqa.selenium.chrome.ChromeDriver(chromeService, capabilities);
            Logger.info("Started Google Chrome Driver with command switches successfully");

        } else {
            Logger.debug("Starting the Chrome Driver");
            driver = new org.openqa.selenium.chrome.ChromeDriver(chromeService);
            Logger.info("Started Google Chrome Browser successfully");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest() {
        //driver.quit();
    }
}
