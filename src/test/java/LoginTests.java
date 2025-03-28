import client.UserClient;
import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static config.StellarBurgerConfig.STELLAR_BASE_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTests extends WebDriverFactory {
    private WebDriver driver;
    User user;
    private final UserClient userClient = new UserClient();
    private UserResponse userResponse = new UserResponse();

    String page;
    LoginPage loginPage;

    public LoginTests(String page) {
        this.page = page;
    }

    @Before
    @DisplayName("Подготовка данных")
    public void createNewUser(){
        driver = WebDriverFactory.createWebDriver();
        Map<String, String> userData = userClient.generateUserBody();
        user = new User(userData.get("email"), userData.get("password"), userData.get("name"));
        ValidatableResponse response = userClient.postCreateUser(user);
        userResponse = response.extract().body().as(UserResponse.class);
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"ConstructorPage"},
                {"PersonalPage"},
                {"ForgotPasswordPage"},
                {"RegisterPage"},
        };
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void login(){
        switch (page) {
            case "ConstructorPage":
                driver.get(STELLAR_BASE_URI);
                ConstructorPage constructorPage = new ConstructorPage(driver);
                loginPage = constructorPage.clickLoginButton();
                break;
            case "PersonalPage":
                driver.get(STELLAR_BASE_URI + "login");
                loginPage = new LoginPage(driver);
                break;
            case "ForgotPasswordPage":
                driver.get(STELLAR_BASE_URI + "forgot-password");
                ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
                loginPage = forgotPasswordPage.clickLoginButton();
                break;
            case "RegisterPage":
                driver.get(STELLAR_BASE_URI + "register");
                RegisterPage registerPage = new RegisterPage(driver);
                loginPage = registerPage.clickLoginButton();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        assertEquals(STELLAR_BASE_URI + "login", driver.getCurrentUrl());
        ConstructorPage authorizedPage = loginPage.fillLoginFormAndClick(user);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
        assertTrue(authorizedPage.isOrderButtonVisible());
    }

    @After
    @DisplayName("Завершение")
    public void clean() {
        if (userResponse.getAccessToken() != null) {
            userClient.deleteUser(userResponse.getAccessToken());
        }
            driver.quit();
    }
}
