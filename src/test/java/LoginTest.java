import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import pageobject.*;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4ClassRunner.class)
public class LoginTest extends BaseTest{
    private final static String USER_NAME = "TestUser";
    private final static String EMAIL = "TestUser" + Math.random() + "@example.com";
    private final static String PASSWORD = "123456";
    private static String accessToken = "";
    HeaderPage headerPage;
    LoginPage loginPage;
    ConstructorPage constructorPage;
    RegistrationPage registrationPage;
    ResetPasswordPage resetPasswordPage;
    AccountPage accountPage;


    @Before
    public void testPreparation() {
        accessToken = createUser(EMAIL, PASSWORD, USER_NAME);
        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);
        registrationPage = new RegistrationPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginViaMainPage() {
        constructorPage.clickLoginButton();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constructorPage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaAccountPage() {
        headerPage.clickAccountLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constructorPage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaRegistrationPage() {
        constructorPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registrationPage.checkTitle();
        registrationPage.clickLoginLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constructorPage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaResetPasswordPage() {
        headerPage.clickAccountLink();
        loginPage.clickResetPasswordLink();
        resetPasswordPage.checkTitle();
        resetPasswordPage.clickLoginLink();
        loginPage.checkTitle();
        loginPage.loginUser(EMAIL, PASSWORD);
        constructorPage.checkCreateOrderButton();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logOutViaAccount() {
        constructorPage.clickLoginButton();
        loginPage.loginUser(EMAIL, PASSWORD);
        headerPage.clickAccountLink();
        accountPage.clickLogOutButton();
        loginPage.checkTitle();
    }

    @After
    public void clear() {
        deleteUser(accessToken);
        accessToken = "";
    }
}

