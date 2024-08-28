package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;
    private By title = By.xpath("//h2[contains(text(),'Восстановление пароля')]");
    private By loginLink = By.xpath("//a[@href='/login']");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle() {
        Assert.assertEquals(driver.findElement(title).getText(), "Восстановление пароля");
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
