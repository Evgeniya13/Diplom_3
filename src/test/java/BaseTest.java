import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigFileReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static String baseUrl;
    public static String driverPath;
    public static String driverName;
    public static long implicitlyWait;

    public WebDriver driver;
    private ConfigFileReader configFileReader;

    @Before
    public void setWebDriver() {
        configFileReader= new ConfigFileReader();
        baseUrl = configFileReader.getApplicationUrl();
        driverPath = configFileReader.getDriverPath();
        driverName = configFileReader.getDriverName();
        implicitlyWait = configFileReader.getImplicitlyWait();

        driver = createDriver(driverName, driverPath);
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    private WebDriver createDriver(String browserName, String driverPath) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome") || browserName.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", driverPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // Создаем экземпляр FirefoxDriver
            driver = new FirefoxDriver();
        } else {
            System.out.println("Неподдерживаемый браузер: " + browserName);
        }
        return driver;
    }

    public static String createUser(String email, String password, String name) {
        ValidatableResponse response = RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(new User(email, password, name))
                .when()
                .post(baseUrl + "api/auth/register").then();
        response.assertThat().statusCode(200);
        return response.extract().path("accessToken").toString().split(" ")[1];
    }

    public static void deleteUser(String accessToken) {
        if (!accessToken.isEmpty()) {
            RestAssured.given().auth().oauth2(accessToken)
                    .baseUri(baseUrl + "api/auth/user")
                    .contentType(ContentType.JSON)
                    .when()
                    .delete().then().assertThat().statusCode(202);
        }
    }

    public static String getAccessToken(String email, String password) {
        ValidatableResponse response = RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(new UserLoginData(email, password))
                .when()
                .post(baseUrl + "api/auth/login").then();
        response.assertThat().statusCode(200);
        return response.extract().path("accessToken").toString().split(" ")[1];
    }
}
