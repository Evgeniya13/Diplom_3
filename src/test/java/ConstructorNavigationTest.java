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
public class ConstructorNavigationTest {
    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    ConstructorPage constrpage;

    @Before
    public void setWebDriver() {
        driver = UserRegistrationTest.createDriver("chrome");
        constrpage = new ConstructorPage(driver);
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void navigateToBuns() {
        constrpage.clickSaucesTab();
        constrpage.clickBunsTab();
        Assert.assertTrue(constrpage.checkBunsTabIsSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void navigateToSauces() {
        constrpage.clickSaucesTab();
        Assert.assertTrue(constrpage.checkSaucesTabIsSelected());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void navigateToToppings() {
        constrpage.clickToppingsTab();
        Assert.assertTrue(constrpage.checkToppingsTabIsSelected());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
