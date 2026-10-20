package client;

import base.AuthService;
import config.ConfigManager;
import credentials.CredentialsProvider;
import credentials.TokenInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private final AuthService authService = new AuthService();

    public LoginResponse login(LoginRequest request) {
        Response response = authService.login(request);
        response.then().statusCode(200);
        return response.as(LoginResponse.class);
    }

    public TokenInfo generateToken() {
        String clientId = CredentialsProvider.getClientId();
        String clientSecret = CredentialsProvider.getClientSecret();

        if (clientId == null || clientId.isBlank() ||
                clientSecret == null || clientSecret.isBlank()) {
            throw new IllegalStateException(
                    "Set CLIENT_ID and CLIENT_SECRET environment variables before requesting a client token");
        }

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-mirrod-d", "werwre")
                .body(Map.of("clientId", clientId, "clientSecret", clientSecret))
                .post(ConfigManager.getAuthUrl());

        response.then().statusCode(200);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        return new TokenInfo(loginResponse.getAccessToken(), loginResponse.getExpiresAt());
    }
}
