package com.api.tests.authservice;

import com.api.base.AUthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends AUthService {
    AUthService aUthService = new AUthService();

    @Test(description = "Verifying forgot password")
    public void forgotPassword(){
        Response response = aUthService.forgotPassword("rushalirathore511@gmail.com");
        System.out.println(response.asPrettyString());
    }
}
