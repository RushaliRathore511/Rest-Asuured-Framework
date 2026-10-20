package client;

import config.ConfigManager;
import credentials.CredentialsProvider;
import credentials.TokenInfo;
import io.restassured.http.ContentType;
import models.request.LoginRequest;
import models.response.LoginResponse;
import io.restassured.response.Response;

import java.util.Map;

import static base.UserMgmtService.BASE_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class AuthClient {

    //1st way
    public LoginResponse login(LoginRequest request) {
        Response response =
                post(BASE_PATH + "login", request);
        return response.as(LoginResponse.class);

    }

    public TokenInfo generateToken() {

        String clientId = CredentialsProvider.getClientId();
        String clientSecret = CredentialsProvider.getClientSecret();


        //2nd way
        LoginResponse loginResponse = given().
                baseUri(ConfigManager.getAuthUrl()).
                contentType(ContentType.JSON).
                header("X-mirrod-d", "werwre")
                .body(Map.of("clientId", clientId, "clientSecret", clientSecret)).
                post().as(LoginResponse.class);

        return new TokenInfo(loginResponse.getExpiresAt(),loginResponse.getAccessToken());
    }


}
