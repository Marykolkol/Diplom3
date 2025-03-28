import client.UserClient;
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
import pages.PersonalPage;

import java.util.Map;

import static config.StellarBurgerConfig.STELLAR_BASE_URI;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTests {
    private WebDriver driver;
    User user;
    private final UserClient userClient = new UserClient();
    private UserResponse userResponse = new UserResponse();

    @Before
    @DisplayName("Подготовка данных")
    public void prepareDriver(){
        driver = WebDriverFactory.createWebDriver();
        driver.get(STELLAR_BASE_URI);
        Map<String, String> userData = userClient.generateUserBody();
        user = new User(userData.get("email"), userData.get("password"), userData.get("name"));
        ValidatableResponse response = userClient.postCreateUser(user);
        userResponse = response.extract().body().as(UserResponse.class);
    }

    @Test
    @DisplayName("Переход на страницу конструктора")
    public void clickConstructor() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        ConstructorPage pageAfterMovement = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user)
                .clickPersonalAccountButton()
                .clickConstructorButton();
        assertTrue(pageAfterMovement.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Переход на страницу конструктора при нажатии на логотип")
    public void clickLogoConstructor() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        ConstructorPage pageAfterMovement = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user)
                .clickPersonalAccountButton()
                .clickLogo();
        assertTrue(pageAfterMovement.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logout() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        LoginPage loginPage = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user)
                .clickPersonalAccountButton()
                .clickExitButton();
        assertTrue(loginPage.isLoginButtonVisible());
    }

    @After
    @DisplayName("Завершение")
    public void after() {
        if (userResponse.getAccessToken() != null) {
            userClient.deleteUser(userResponse.getAccessToken());
        }
        driver.quit();
    }
}
