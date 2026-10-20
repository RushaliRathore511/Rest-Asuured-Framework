package tests.authservice;

import base.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends AuthService {
    AuthService aUthService = new AuthService();

    @Test(description = "Verifying forgot password")
    public void forgotPassword(){
        Response response = aUthService.forgotPassword("rushalirathore511@gmail.com");
        System.out.println(response.asPrettyString());
    }
}
