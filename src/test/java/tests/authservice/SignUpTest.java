package tests.authservice;

import base.AuthService;
import models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends AuthService {

    AuthService authService = new AuthService();

    @Test(description = "Verify Sign Up verification")
    public void signUpTest(){

        SignUpRequest signUpRequest = SignUpRequest.builder().email("test@gmail.com").mobileNumber("9340647992").
                firstName("Rushali").
                lastName("Rathore").password("Rushu@#93406").userName("Rushali511").
                build();

       Response response =  authService.signUp(signUpRequest);

      System.out.println(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
