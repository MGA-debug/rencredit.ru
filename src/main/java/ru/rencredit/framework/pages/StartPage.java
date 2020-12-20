package ru.rencredit.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static ru.rencredit.framework.managers.DriverManager.getDriver;

public class StartPage extends BasePage {

    @FindBy(xpath = "//div[@class='service__title-text']")
    List<WebElement> serviceList;

    @Step("Выбираем продукт '{serviceName}'")
    public FormPage selectService(String serviceName) {
        /*Проверяем, что мы находимся на главной странице */
        Assert.assertEquals("Вы не перешли на стартовую страницу",
                "Банк «Ренессанс Кредит»", getDriver().getTitle());

        WebElement name;
        for (WebElement service : serviceList) {
            if (service.getText().equalsIgnoreCase(serviceName)) {
                name = service.findElement(By.xpath("./../a[@class='service__title-action']"));
                waitUtilElementToBeClickable(name);
                name.click();

                /* Ждем заголовок страницы с формой для заполнения */
                waitUtilElementToBeVisible(getDriver().
                        findElement(By.xpath("//h2[@class='calculator-block__title block-title']")));
                return app.getFormPage();
            }
        }
        Assert.fail("Такого продукта нет");
        return app.getFormPage();
    }
}
