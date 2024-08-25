import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pageobject.*;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4ClassRunner.class)
public class LoginTest {
    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private final static String EMAIL = "test123@ex.com";
    private final static String PASSWORD = "123456";
    HeaderPage headerpage;
    LoginPage loginPage;
    ConstructorPage constrpage;
    RegistrationPage regpage;
    ResetPasswordPage resetpasspage;
    AccountPage accountPage;

    @Before
    public void setWebDriver() {
        driver = UserRegistrationTest.createDriver("chrome");
        headerpage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
        constrpage = new ConstructorPage(driver);
        regpage = new RegistrationPage(driver);
        resetpasspage = new ResetPasswordPage(driver);
        accountPage = new AccountPage(driver);
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginViaMainPage() {
        constrpage.clickLoginButton();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constrpage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaAccountPage() {
        headerpage.clickAccountLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constrpage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaRegistrationPage() {
        constrpage.clickLoginButton();
        loginPage.clickRegisterLink();
        regpage.checkTitle();
        regpage.clickLoginLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constrpage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaResetPasswordPage() {
        headerpage.clickAccountLink();
        loginPage.clickResetPasswordLink();
        resetpasspage.checkTitle();
        resetpasspage.clickLoginLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constrpage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logOutViaAccount() {
        constrpage.clickLoginButton();
        loginPage.loginUser(EMAIL, PASSWORD);
        headerpage.clickAccountLink();
        accountPage.clickLogOutButton();
        loginPage.checkTitle();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

