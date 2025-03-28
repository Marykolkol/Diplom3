package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    private WebDriver driver;

    //кнопка "Войти"
    @FindBy(xpath = ".//a[@class='Auth_link__1fOlj' and text()='Войти']")
    private WebElement loginButton;

    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }
}
