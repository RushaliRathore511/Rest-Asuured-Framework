package tests.authservice;

import base.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends AuthService {

    @Test(groups = "auth", description = "Forgot-password request is accepted")
    public void forgotPasswordRequestIsAccepted() {
        String email = "portfolio+" + System.currentTimeMillis() + "@example.com";

        Response response = forgotPassword(email);

        Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 300,
                "Forgot-password request should be accepted");
    }
}
