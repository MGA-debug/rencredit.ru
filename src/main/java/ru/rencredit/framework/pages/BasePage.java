package ru.rencredit.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.rencredit.framework.managers.PageManager;

import static ru.rencredit.framework.managers.DriverManager.getDriver;

public class BasePage {
    protected PageManager app = PageManager.getPageManager();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected static String convertValue(WebElement element) {
        return element.getText().replaceAll("[^0-9,]", "");
    }

    protected void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitChangeText(WebElement element, String text) {
        int timeOut = 5;
        while (timeOut > 0) {
            if (!convertValue(element).equals(text)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else break;
            timeOut--;
        }
    }
}
