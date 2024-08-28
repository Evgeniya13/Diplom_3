import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import pageobject.*;

@RunWith(JUnit4ClassRunner.class)
public class NavigateToPageTest extends BaseTest{
    HeaderPage headerPage;
    @Before
    public void initializeHeaderPage() {
        headerPage = new HeaderPage(driver);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void navigateToAccountPage() {
        headerPage.clickAccountLink();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "login");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void navigateFromAccountToConstructorPage() {
        headerPage.clickAccountLink();
        headerPage.clickConstructorLink();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void navigateFromAccountToConstructorPageByLogo() {
        headerPage.clickAccountLink();
        headerPage.clickLogoLink();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }
}
