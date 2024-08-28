package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AccountPage {
    private WebDriver driver;
    private By logOutButton = By.xpath("//button[text()='Выход']");
    private By title = By.xpath("//a[contains(text(),'Профиль')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle() {
        Assert.assertEquals(driver.findElement(title).getText(), "Профиль");
    }

    @Step("Выход из аккаунта")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
}
