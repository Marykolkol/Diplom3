import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static config.StellarBurgerConfig.STELLAR_BASE_URI;
import static org.junit.Assert.assertTrue;

public class RegistrationTests {
    private WebDriver driver;
    Random RANDOM = new Random();

    @Before
    @DisplayName("Подготовка данных")
    public void prepareDriver(){
        driver = WebDriverFactory.createWebDriver();
        driver.get(STELLAR_BASE_URI + "register");
    }

    public Map<String, String> setRandomValues() {
        return Map.of ("name", "Test Testov",
                "email", "email_" + RANDOM.nextInt(1000000) + "@pochta.ru",
                "password", "password_" + RANDOM.nextInt(1000));
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successRegister() {
        Map<String, String> data = setRandomValues();

        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = registerPage.fillRegistrationFormAndClick(data.get("name"), data.get("email"), data.get("password"));
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        assertTrue(loginPage.isLoginButtonVisible());

        User user = new User(data.get("email"), data.get("password"), data.get("name"));
        ConstructorPage authorizedPage = loginPage.fillLoginFormAndClick(user);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        assertTrue(authorizedPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Получение ошибки ввода пароля")
    public void notSuccessRegister() {
        Map<String, String> data = setRandomValues();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillRegistrationFormAndClick(data.get("name"), data.get("email"), "12345");
        assertTrue(registerPage.isErrorVisible());
    }

    @After
    @DisplayName("Закрытие браузера")
    public void after() {
        driver.quit();
    }
}
