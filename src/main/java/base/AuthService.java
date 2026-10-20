package base;

import client.ApiClient;
import io.restassured.response.Response;
import models.request.LoginRequest;
import models.request.SignUpRequest;

import java.util.Map;

public class AuthService extends ApiClient {

    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest loginRequest) {
        return post(BASE_PATH + "login", loginRequest);
    }

    public Response signUp(SignUpRequest signUpRequest) {
        return post(BASE_PATH + "signup", signUpRequest);
    }

    public Response forgotPassword(String emailAddress) {
        return postWithQueryParam(BASE_PATH + "forgot-password", Map.of("email", emailAddress));
    }
}
