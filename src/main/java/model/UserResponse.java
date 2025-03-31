package model;
import java.util.Map;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private boolean success;
    private Map<String, String> user;
    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Map<String, String> getUser() {
        return user;
    }
}
