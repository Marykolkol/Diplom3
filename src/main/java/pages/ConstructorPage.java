package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ConstructorPage {
    private final WebDriver driver;

    //кнопка "Личный кабинет"
    private final By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text() = 'Личный Кабинет']/parent::a");

    //заголовок раздела "Булки"
    private final By bunsTitle = By.xpath(".//span[text()='Булки']");

    //активный заголовок раздела "Булки"
    private final By bunsTitleActive = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");

    //заголовок раздела "Соусы"
    private final By saucesTitle = By.xpath(".//span[text()='Соусы']");

    //активный заголовок раздела "Соусы"
    private final By saucesTitleActive = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //заголовок раздела "Начинки"
    private final By fillerTitle = By.xpath(".//span[text()='Начинки']");

    //активный заголовок раздела "Начинки"
    private final By fillerTitleActive = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");


    //кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    //кнопка "Оформить заказ"
    private final By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");

    public ConstructorPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PersonalPage clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new PersonalPage(driver);
    }

    public LoginPage clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public void clickBunsTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTitle));
        driver.findElement(bunsTitle).click();
    }

    public boolean isBunsActive() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(bunsTitleActive));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void clickSaucesTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesTitle));
        driver.findElement(saucesTitle).click();
    }

    public boolean isSaucesActive() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(saucesTitleActive));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void clickFillerTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillerTitle));
        driver.findElement(fillerTitle).click();
    }

    public boolean isFillersActive() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(fillerTitleActive));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isOrderButtonVisible() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(orderButton));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
