import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class FirstTest extends BaseTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{

                {"Рубли", "300000", "6 месяцев", "50000", "да", "9132,17", "250000", "559132,17"},
                {"Доллары США", "500000", "6 месяцев", "30000", "да", "285,19", "150000", "650285,19"}
        });
    }

    @Parameterized.Parameter(0)
    public String condition;

    @Parameterized.Parameter(1)
    public String sumDeposit;

    @Parameterized.Parameter(2)
    public String term;

    @Parameterized.Parameter(3)
    public String monthlyReplenishment;

    @Parameterized.Parameter(4)
    public String consent;

    @Parameterized.Parameter(5)
    public String accruedInterest;

    @Parameterized.Parameter(6)
    public String replenishment;

    @Parameterized.Parameter(7)
    public String toWithDraw;


    @Test
    public void firsTest() {
        app.getStartPage()
                .selectService("Вклады")
                .selectCondition("Валюта", condition)
                .selectCondition("Сумма вклада", sumDeposit)
                .selectCondition("На срок", term)
                .selectCondition("Ежемесячное пополнение", monthlyReplenishment)
                .selectCondition("Ежемесячная капитализация", consent)
                .checkValues("Начислено %", accruedInterest)
                .checkValues("Пополнение за 6 месяцев", replenishment)
                .checkValues("К снятию через 6 месяцев", toWithDraw);
    }
}