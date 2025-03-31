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
import pages.PersonalPage;
import java.util.Map;

import static config.StellarBurgerConfig.STELLAR_BASE_URI;
import static org.junit.Assert.assertTrue;

public class ConstructorTests {
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
    @DisplayName("Переход в личный кабинет")
    public void clickPersonalAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        PersonalPage personalPage = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user)
                .clickPersonalAccountButton();
        assertTrue(personalPage.isSignVisible());
    }

    @Test
    @DisplayName("Проверка нажатия на раздел 'Начинки'")
    public void checkSwitchFillersParts() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        ConstructorPage authorizedPage = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user);
        authorizedPage.clickFillerTitle();

        assertTrue(authorizedPage.isFillersActive());
    }

    @Test
    @DisplayName("Проверка нажатия на раздел 'Соусы'")
    public void checkSwitchSaucesParts() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        ConstructorPage authorizedPage = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user);
        authorizedPage.clickSaucesTitle();
        assertTrue(authorizedPage.isSaucesActive());
    }

    @Test
    @DisplayName("Проверка нажатия на раздел 'Булки'")
    public void checkSwitchBunsParts() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        ConstructorPage authorizedPage = constructorPage
                .clickLoginButton()
                .fillLoginFormAndClick(user);
        authorizedPage.clickFillerTitle();
        authorizedPage.clickBunsTitle();
        assertTrue(authorizedPage.isBunsActive());

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
