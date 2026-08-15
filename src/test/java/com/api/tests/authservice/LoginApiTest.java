package com.api.tests.authservice;

import com.api.base.AUthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)
public class LoginApiTest extends AUthService {

    AUthService aUthService = new AUthService();

    @Test(description = "Login API functionality")
    public void loginTest(){
        LoginRequest request = LoginRequest.builder().userName("testuser").password("testpass").build();
        Response response = aUthService.login(request); //response is json object
        System.out.println(response.prettyPrint());
       LoginResponse loginResponse = response.as(LoginResponse.class); //converting json object to Java object //deserialization
        Assert.assertNotNull(loginResponse.getToken());
        System.out.println(loginResponse.getId());
        System.out.println(loginResponse.getType());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getUserRoles());
    }
}
