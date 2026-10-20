package tests.authservice;

import base.AuthService;
import listeners.TestListener;
import models.request.LoginRequest;
import models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginApiTest extends AuthService {

    @Test(description = "Login API functionality")
    public void loginTest(){
        LoginRequest request = LoginRequest.builder().userName("testuser").password("testpass").build();
        Response response =login(request); //response is json object
        System.out.println(response.prettyPrint());
       LoginResponse loginResponse = response.as(LoginResponse.class); //converting json object to Java object //deserialization
        Assert.assertNotNull(loginResponse.getToken());
        System.out.println(loginResponse.getId());
        System.out.println(loginResponse.getType());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getUserRoles());
    }
}
