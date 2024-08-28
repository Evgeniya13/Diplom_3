import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pageobject.*;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4ClassRunner.class)
public class ConstructorNavigationTest extends BaseTest{
    ConstructorPage constructorPage;

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void navigateToBuns() {
        constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();
        Assert.assertTrue(constructorPage.checkBunsTabIsSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void navigateToSauces() {
        constructorPage = new ConstructorPage(driver);
        constructorPage.clickSaucesTab();
        Assert.assertTrue(constructorPage.checkSaucesTabIsSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void navigateToToppings() {
        constructorPage = new ConstructorPage(driver);
        constructorPage.clickToppingsTab();
        Assert.assertTrue(constructorPage.checkToppingsTabIsSelected());
    }

}
