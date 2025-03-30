package pages;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    //поле ввода почты
    @FindBy(xpath = ".//label[text()='Email']/parent::div/input")
    private WebElement emailInput;

    //поле ввода пароля
    @FindBy(xpath = ".//label[text()='Пароль']/parent::div/input")
    private WebElement passwordInput;

    //кнопка "Войти"
    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement loginButton;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение формы и нажатие кнопки 'Войти'")
    public ConstructorPage fillLoginFormAndClick(User user) {
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        loginButton.click();
        return new ConstructorPage(driver);
    }

    @Step("Проверка наличия кнопки 'Войти'")
    public boolean isLoginButtonVisible() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(loginButton));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
