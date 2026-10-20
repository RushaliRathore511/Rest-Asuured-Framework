package tests.authservice;

import base.AuthService;
import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestCredentials;

public class LoginApiTest extends AuthService {

    @Test(groups = "auth", description = "Valid credentials should return an access token")
    public void loginWithValidCredentialsReturnsToken() {
        Response response = login(TestCredentials.validLogin());

        Assert.assertEquals(response.getStatusCode(), 200, "Login should succeed");
        LoginResponse loginResponse = response.as(LoginResponse.class);
        Assert.assertNotNull(loginResponse.getToken(), "Login response should contain a token");
        Assert.assertFalse(loginResponse.getToken().isBlank(), "Token should not be empty");
    }

    @Test(groups = "auth", description = "Invalid credentials should not authenticate")
    public void loginWithInvalidCredentialsIsRejected() {
        LoginRequest request = LoginRequest.builder()
                .userName("portfolio_invalid_user")
                .password("definitely-not-valid")
                .build();

        Response response = login(request);

        Assert.assertTrue(response.getStatusCode() >= 400 && response.getStatusCode() < 500,
                "Invalid credentials should return a client error, not succeed or fail on the server");
    }

    @Test(groups = "auth", description = "Empty credentials should not authenticate")
    public void loginWithEmptyCredentialsIsRejected() {
        LoginRequest request = LoginRequest.builder()
                .userName("")
                .password("")
                .build();

        Response response = login(request);

        Assert.assertTrue(response.getStatusCode() >= 400 && response.getStatusCode() < 500,
                "Empty credentials should return a client error, not succeed or fail on the server");
    }
}
