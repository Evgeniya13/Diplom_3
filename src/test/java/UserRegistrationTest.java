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

public class UserRegistrationTest {
    private final static String URL = "https://stellarburgers.nomoreparties.site/register";
    private WebDriver driver;
    private final static String USER_NAME = "TestUser";
    private final static String EMAIL = "TestUser" + Math.random() + "@example.com";
    private final static String PASSWORD = "123456";
    private final static String WRONG_PASSWORD = "1234";
    LoginPage loginpage;
    RegistrationPage regpage;

    @Before
    public void setWebDriver() {
        driver = createDriver("chrome");
        loginpage = new LoginPage(driver);
        regpage = new RegistrationPage(driver);
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void createNewRegistration() {
        regpage.checkTitle();
        regpage.registerUser(USER_NAME, EMAIL, PASSWORD);
        loginpage.checkTitle();
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void checkWrongPassword() {
        regpage.checkTitle();
        regpage.registerUser(USER_NAME, EMAIL, WRONG_PASSWORD);
        regpage.checkErrorMessage();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    public static WebDriver createDriver(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");

            // Создаем экземпляр ChromeDriver
            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Создаем экземпляр FirefoxDriver
            driver = new FirefoxDriver();
        } else {
            System.out.println("Неподдерживаемый браузер: " + browser);
        }
        return driver;
    }
}
