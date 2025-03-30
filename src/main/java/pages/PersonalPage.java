package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalPage {
    private final WebDriver driver;

    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    //кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text() = 'Конструктор']/parent::a");

    //логотип Бургера
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    //название поля "Профиль"
    private final By profileSign = By.xpath(".//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9']");

    public PersonalPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки 'Выход'")
    public LoginPage clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажатие кнопки 'Конструктоп'")
    public ConstructorPage clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
        driver.findElement(constructorButton).click();
        return new ConstructorPage(driver);
    }

    @Step("Нажатие логотипа")
    public ConstructorPage clickLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logo));
        driver.findElement(logo).click();
        return new ConstructorPage(driver);
    }

    @Step("Проверка наличия заголовка 'Профиль'")
    public boolean isSignVisible() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(profileSign));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
