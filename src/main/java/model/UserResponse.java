package model;
import java.util.Map;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private boolean success;
    private Map<String, String> user;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
