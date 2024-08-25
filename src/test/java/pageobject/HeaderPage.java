package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {

    private WebDriver driver;
    private By logoLink = By.xpath("(//*[name()='svg'])[3]");
    private By accountLink = By.xpath("//a[@href='/account']");
    private By constructorLink = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу 'Логин' через ссылку 'Войти'")
    public void clickLogoLink() {
        driver.findElement(logoLink).click();
    }

    @Step("Переход в личный кабинет через ссылку 'Личный кабинет'")
    public void clickAccountLink() {
        driver.findElement(accountLink).click();
    }

    @Step("Переход к конструктору через ссылку 'Конструктор'")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

}
