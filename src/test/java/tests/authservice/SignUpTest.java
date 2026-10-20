package tests.authservice;

import base.AuthService;
import io.restassured.response.Response;
import models.request.SignUpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends AuthService {

    @Test(groups = "auth", description = "A new user can sign up")
    public void signUpWithNewDetailsSucceeds() {
        String suffix = Long.toString(System.currentTimeMillis());
        String mobileNumber = "9" + suffix.substring(suffix.length() - 9);

        SignUpRequest request = SignUpRequest.builder()
                .userName("portfolio_" + suffix)
                .email("portfolio+" + suffix + "@example.com")
                .firstName("Portfolio")
                .lastName("Tester")
                .mobileNumber(mobileNumber)
                .password("TestPass@" + suffix)
                .build();

        Response response = signUp(request);

        Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 300,
                "Signup should succeed for new details");
    }
}
