package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class RegisterPage {
    private final WebDriver driver;

    //поле ввода имени
    @FindBy(xpath = ".//label[text()='Имя']/parent::div/input")
    private WebElement nameInput;

    //поле ввода почты
    @FindBy(xpath = ".//label[text()='Email']/parent::div/input")
    private WebElement emailInput;

    //поле ввода пароля
    @FindBy(xpath = ".//label[text()='Пароль']/parent::div/input")
    private WebElement passwordInput;

    //кнопка "Зарегистрироваться"
    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement registrationButton;

    //кнопка "Войти" - если уже зарегистрированы
    @FindBy(xpath = ".//a[@class='Auth_link__1fOlj' and text()='Войти']")
    private WebElement loginButton;

    //ошибка "Некорректный пароль"
    private final By error = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage fillRegistrationFormAndClick(String name, String email, String password) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        registrationButton.click();
        return new LoginPage(driver);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public boolean isErrorVisible() {
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(error));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
