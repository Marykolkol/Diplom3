package client;

import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import io.restassured.response.ValidatableResponse;
import model.User;

import static config.StellarBurgerConfig.STELLAR_BASE_URI;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final Random RANDOM = new Random();

    public Map<String, String> generateUserBody(){
        Map<String, String> body = new HashMap<>();
        body.put("email", "email_" + RANDOM.nextInt(1000000) + "@pochta.ru");
        body.put("password", "password_" + RANDOM.nextInt(1000));
        body.put("name", "Test Testov");
        return body;
    }

    @Step("Создание пользователя")
    public ValidatableResponse postCreateUser(User user){
        return
                given()
                        .log()
                        .all()
                        .baseUri(STELLAR_BASE_URI)
                        .header("Content-type", "application/json")
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log()
                        .all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse postUserLogin(User user) {
        return
                given()
                        .log()
                        .all()
                        .baseUri(STELLAR_BASE_URI)
                        .header("Content-type", "application/json")
                        .body(user)
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log()
                        .all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return
                given()
                        .log()
                        .all()
                        .baseUri(STELLAR_BASE_URI)
                        .header("Authorization", token)
                        .header("Content-type", "application/json")
                        .when()
                        .delete("/api/auth/user")
                        .then()
                        .log()
                        .all();
    }
}
