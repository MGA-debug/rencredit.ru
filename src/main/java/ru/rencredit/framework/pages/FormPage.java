package ru.rencredit.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static ru.rencredit.framework.managers.DriverManager.getDriver;


public class FormPage extends BasePage {

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']/..")
    List<WebElement> currency;

    @FindBy(xpath = "//div[@class='calculator__slide-section']")
    List<WebElement> inputCondition;

    @FindBy(xpath = "//div[@class='calculator__content']//span[@class='calculator__check-text']")
    List<WebElement> checkBoxList;

    @FindBy(xpath = "//tr[@class='calculator__dep-result-table-row']")
    List<WebElement> depositInterest;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    WebElement toWithdraw;

    @Step("Выбираем '{name}' со значением {condition}")
    public FormPage selectCondition(String name, String condition) {
        WebElement conditionElement;
        WebElement valueElement;
        m:
        switch (name) {
            case "Валюта":
                for (WebElement element : currency) {
                    if (element.getText().equalsIgnoreCase(condition)) {
                        waitUtilElementToBeClickable(element);
                        element.click();
                        break m;
                    }
                }
                Assert.fail("Вкладов в такой валюты нет");
                break;

            case "Сумма вклада":
            case "Ежемесячное пополнение":
                for (WebElement element : inputCondition) {
                    conditionElement = element.findElement(By.xpath("./div/label"));
                    if (conditionElement.getText().equalsIgnoreCase(name)) {
                        valueElement = element.findElement(By.xpath(".//input"));
                        valueElement.sendKeys(condition);
                        break m;
                    }
                }
                break;

            case "На срок":
                for (WebElement element : inputCondition) {
                    conditionElement = element.findElement(By.xpath("./div/label"));
                    if (conditionElement.getText().equalsIgnoreCase(name)) {
                        Select select = new Select(element.findElement(By.xpath(".//select")));
                        select.selectByVisibleText(condition);
                        break m;
                    }
                }
                Assert.fail("Нельзя открыть вклад на такой срок");

            case "Ежемесячная капитализация":
            case "Частичное снятие":
            case "В отделении банка":
            case "В интернет банке":
                for (WebElement element : checkBoxList) {
                    if (element.getText().equalsIgnoreCase(name)) {
                        conditionElement = element.findElement(By.xpath("./../..//input/.."));
                        if (conditionElement.getAttribute("class").contains("checked") && (condition.equals("нет"))) {
                            conditionElement.click();
                        } else if (!conditionElement
                                .getAttribute("class").contains("checked") && (condition.equals("да"))) {
                            waitUtilElementToBeClickable(conditionElement);
                            conditionElement.click();
                        }
                        break m;
                    }
                }
                Assert.fail("Выберите да/нет");
            default:
                Assert.fail("Нет такого пункта");
                break;
        }
        return this;
    }

    @Step("Проверяем поле '{name}' значением {value}")
    public FormPage checkValues(String name, String value) {
        WebElement valueElement;
        String valueXPath;

        switch (name) {
            case "Начислено %":
            case "Пополнение за 6 месяцев":
                for (WebElement element : depositInterest) {
                    if (element.getText().contains(name)) {
                        valueXPath = "/td[@class='calculator__dep-result-table-cell " +
                                "calculator__dep-result-table-cell_val']";
                        valueElement = element.findElement(By.xpath("." + valueXPath));
                        /*Ждем пока текст элемента поменяется*/
                        waitChangeText(valueElement, value);
                        Assert.assertEquals("Ошибка в значении поля", value,
                                convertValue(valueElement));
                    }
                }
                break;
            case "К снятию через 6 месяцев":
                valueElement = getDriver().findElement(By.xpath("//div[@class='calculator__dep-result-value']"));
                waitChangeText(valueElement, value);
                Assert.assertEquals("Ошибка в 'сумма к снятию'", value, convertValue(toWithdraw));
        }
        return this;
    }
}

