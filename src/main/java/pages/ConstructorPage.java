package pages;
import io.qameta.allure.Step;
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
    private final By bunsTitleActive = By.xpath(".//div[contains(@class='current')]/span[text()='Булки']");

    //заголовок раздела "Соусы"
    private final By saucesTitle = By.xpath(".//span[text()='Соусы']");

    //активный заголовок раздела "Соусы"
    private final By saucesTitleActive = By.xpath(".//div[contains(@class='current')]/span[text()='Соусы']");

    //заголовок раздела "Начинки"
    private final By fillerTitle = By.xpath(".//span[text()='Начинки']");

    //активный заголовок раздела "Начинки"
    private final By fillerTitleActive = By.xpath(".//div[contains(@class='current')]/span[text()='Начинки']");

//.//button[contains(@class,'header')]
    //кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    //кнопка "Оформить заказ"
    private final By orderButton = By.xpath(".//button[text() = 'Оформить заказ']");

    public ConstructorPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки 'Личный кабинет'")
    public PersonalPage clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new PersonalPage(driver);
    }

    @Step("Нажатие кнопки 'Войти'")
    public LoginPage clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Выбор раздела 'Булки'")
    public void clickBunsTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTitle));
        driver.findElement(bunsTitle).click();
    }

    @Step("Проверка активного раздела 'Булки'")
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

    @Step("Выбор раздела 'Соусы'")
    public void clickSaucesTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesTitle));
        driver.findElement(saucesTitle).click();
    }

    @Step("Проверка активного раздела 'Соусы'")
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

    @Step("Выбор раздела 'Начинки'")
    public void clickFillerTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillerTitle));
        driver.findElement(fillerTitle).click();
    }

    @Step("Проверка активного раздела 'Начинки'")
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

    @Step("Проверка наличия кнопки 'Оформить заказ'")
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
