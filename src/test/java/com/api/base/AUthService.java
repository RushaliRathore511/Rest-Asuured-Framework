package com.api.base;

import com.api.models.request.LoginRequest;
import com.api.models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

public  class AUthService extends BaseService{
    private static final String BASE_PATH = "/api/auth/";

    public Response login( LoginRequest loginRequest){
        return postRequest(BASE_PATH + "login",loginRequest);
    }

    public Response signUp( SignUpRequest signUpRequest){
        return postRequest(BASE_PATH + "signup",signUpRequest);
    }

    public Response forgotPassword(  String emailAddress){
        HashMap<String,String > payload = new HashMap();
        payload.put("email",emailAddress);
        return postRequest(BASE_PATH + "forgot-password",emailAddress);
    }
}
