package pageobject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ConstructorPage {
    private WebDriver driver;
    private WebElement tab;
    private By title = By.xpath("//class[text()='Соберите бургер']");
    private By bunsTab = By.xpath("//div[span[text()='Булки']]");
    private By saucesTab = By.xpath("//div[span[text()='Соусы']]");
    private By toppingsTab = By.xpath("//div[span[text()='Начинки']]");
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By createOrderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");

    public ConstructorPage(WebDriver driver, WebElement tab) {
        this.driver = driver;
        this.tab = tab;
    }

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle() {
        Assert.assertEquals(driver.findElement(title).getText(), "Соберите бургер");
    }

    @Step("Перейти к булкам")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public boolean checkBunsTabIsSelected() {
        tab = driver.findElement(bunsTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Перейти к соусам")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public boolean checkSaucesTabIsSelected() {
        tab = driver.findElement(saucesTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Перейти к начинкам")
    public void clickToppingsTab() {
        driver.findElement(toppingsTab).click();
    }

    public boolean checkToppingsTabIsSelected() {
        tab = driver.findElement(toppingsTab);
        return tab.getAttribute("class").contains("current");
    }

    @Step("Войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Проверить, что кнопка Оформить заказ отображается")
    public void checkCreateOrderButton() {
        driver.findElement(createOrderButton).isDisplayed();
    }
}
