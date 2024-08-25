package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    private WebDriver driver;
    private By title = By.xpath("//h2[contains(text(),'Вход')]");
    private By emailField = By.xpath("//input[@name='name']");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By registerLink = By.xpath("//a[@href='/register']");
    private By resetPasswordLink = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle() {
        Assert.assertEquals(driver.findElement(title).getText(), "Вход");
    }

    public void fillEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Логин пользователя")
    public void loginUser(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    @Step("Переход на страницу 'Восстановить пароль'")
    public void clickResetPasswordLink() {
        driver.findElement(resetPasswordLink).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }
}
