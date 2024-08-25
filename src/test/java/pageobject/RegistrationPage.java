package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

// URL for reg = https://stellarburgers.nomoreparties.site/register
public class RegistrationPage {

    private WebDriver driver;
    private By title = By.xpath("//h2[contains(text(),'Регистрация')]");
    private By nameField = By.xpath("//div[label[text()='Имя']]/input");
    private By emailField = By.xpath("//div[label[text()='Email']]/input");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By errorMessage = By.xpath("//p[@class='input__error text_type_main-default']");
    private By loginLink = By.xpath("//a[@href='/login']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle() {
        Assert.assertEquals(driver.findElement(title).getText(), "Регистрация");
    }

    public void checkErrorMessage() {
        Assert.assertEquals(driver.findElement(errorMessage).getText(), "Некорректный пароль");
    }

    public void fillName(String userName) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(userName);
    }

    public void fillEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Регистрация пользователя")
    public void registerUser(String userName, String email, String password) {
        fillName(userName);
        fillEmail(email);
        fillPassword(password);
        clickRegisterButton();
    }

    @Step("Переход на страницу 'Логин' через ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
