package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    private final WebDriver driver;

    //кнопка "Войти"
    @FindBy(xpath = ".//a[@class='Auth_link__1fOlj' and text()='Войти']")
    private WebElement loginButton;

    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки 'Войти'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }
}
