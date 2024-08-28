import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4ClassRunner.class)
public class UserRegistrationTest extends BaseTest{
    private final static String USER_NAME = "TestUser";
    private final static String EMAIL = "TestUser" + Math.random() + "@example.com";
    private final static String PASSWORD = "123456";
    private final static String WRONG_PASSWORD = "1234";
    public static String accessToken = "";
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void initializePages() {
        driver.get(baseUrl + "register");
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void createNewRegistration() {
        registrationPage.checkTitle();
        registrationPage.registerUser(USER_NAME, EMAIL, PASSWORD);
        loginPage.checkTitle();
        accessToken = getAccessToken(EMAIL, PASSWORD);
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void checkWrongPassword() {
        registrationPage.checkTitle();
        registrationPage.registerUser(USER_NAME, EMAIL, WRONG_PASSWORD);
        registrationPage.checkErrorMessage();
    }

    @After
    public void clear() {
        deleteUser(accessToken);
        accessToken = "";
    }
}
