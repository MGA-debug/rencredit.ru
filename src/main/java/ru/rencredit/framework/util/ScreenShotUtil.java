package ru.rencredit.framework.util;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.util.ResultsUtils;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static ru.rencredit.framework.managers.DriverManager.getDriver;

public class ScreenShotUtil extends AllureJunit4 {

    public void testFailure(Failure failure) {
        addScreenShot();
        super.testFailure(failure);
    }

    @Attachment(value = "screenshot", type = "image/png")
    static byte[] addScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
