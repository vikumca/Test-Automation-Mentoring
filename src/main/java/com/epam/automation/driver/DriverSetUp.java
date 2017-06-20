package com.epam.automation.driver;

import com.epam.automation.config.Constants;
import com.epam.automation.report.Logger;
import com.epam.automation.util.Config;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class DriverSetUp {

    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 20;
    private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 15;
    private static final ThreadLocal<WebDriver> webDriverThreadLocal;

    static {
        webDriverThreadLocal = new ThreadLocal<>();
    }

    private DriverSetUp() {}

    /**
     * To initialize the Driver
     * @param   browser
     *          - Browser (e.g. Chrome, Firefox IE etc)
     * @return
     */
    public static void initializeDriver(String browser) {
        WebDriver driver = null;
        if (("firefox").equalsIgnoreCase(browser)) {
            driver = initiateFireFoxDriver();
            Logger.info("Started Firefox Browser successfully.");

        } else if (("chrome").equalsIgnoreCase(browser)) {
            driver = initiateChromeDriver();
            Logger.info("Started Chrome Browser successfully.");

        }
        setDriver(driver);

    }

    private static WebDriver initiateFireFoxDriver() {
        WebDriver driver = null;
        try {
            driver = new FirefoxDriver();
            Logger.info("Started FireFox Driver");
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (Exception e) {
            Logger.error("Failed to initiate FireFox Driver");
        }
        return driver;
    }

    private static WebDriver initiateChromeDriver() {
        WebDriver driver = null;
        try {
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
        } catch (Exception e) {
            Logger.error("Failed to initiate Chrome Driver"+e);
        }
        return driver;
    }

    private static void setDriver(WebDriver driver) {
        webDriverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = null;
        driver = webDriverThreadLocal.get();
        if (driver == null)
            throw new IllegalStateException("Driver not set...");
        return driver;
    }
}
