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

public class NavigateToPageTest {
    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private WebDriver driver;
    HeaderPage headerpage;

    @Before
    public void setWebDriver() {
        driver = UserRegistrationTest.createDriver("chrome");
        headerpage = new HeaderPage(driver);
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void navigateToAccountPage() {
        headerpage.clickAccountLink();
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void navigateFromAccountToConstructorPage() {
        headerpage.clickAccountLink();
        headerpage.clickConstructorLink();
        Assert.assertEquals(driver.getCurrentUrl(), URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void navigateFromAccountToConstructorPageByLogo() {
        headerpage.clickAccountLink();
        headerpage.clickLogoLink();
        Assert.assertEquals(driver.getCurrentUrl(), URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
