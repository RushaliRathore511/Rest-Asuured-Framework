package base;

import client.ApiClient;
import models.request.LoginRequest;
import models.request.SignUpRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public  class AuthService extends ApiClient {

    private static final String BASE_PATH = "/api/auth/";

  public Response  login(LoginRequest loginRequest){
      return post(BASE_PATH + "login",loginRequest);
  }

  public Response signUp( SignUpRequest signUpRequest){
        return post(BASE_PATH + "signup",signUpRequest);
    }

    public Response forgotPassword( String emailAddress){
        HashMap<String,String > payload = new HashMap();
        payload.put("email",emailAddress);
        return postWithQueryParam(BASE_PATH + "forgot-password",payload);
    }
}
