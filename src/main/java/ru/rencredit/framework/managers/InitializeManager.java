package ru.rencredit.framework.managers;

import java.util.concurrent.TimeUnit;


import static ru.rencredit.framework.managers.DriverManager.getDriver;
import static ru.rencredit.framework.managers.DriverManager.quitDriver;
import static ru.rencredit.framework.util.PropertyConstant.IMPLICITLY_WAIT;
import static ru.rencredit.framework.util.PropertyConstant.PAGE_LOAD_TIMEOUT;

public class InitializeManager {

    public static TestPropManager props = TestPropManager.getTestPropManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
    }
}
