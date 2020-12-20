package ru.rencredit.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.rencredit.framework.managers.InitializeManager.props;
import static ru.rencredit.framework.util.PropertyConstant.CHROMEDRIVER_PATH;

public class DriverManager {

    private static WebDriver driver;
    public static TestPropManager prop = TestPropManager.getTestPropManager();


    private DriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null)
            initDriver();
        return driver;
    }

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty(CHROMEDRIVER_PATH));
        driver = new ChromeDriver();
    }

    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
