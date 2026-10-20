package client;

import auth.AuthManager;
import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static final String BASE_URL =
            "http://64.227.160.186:8080";

    RequestSpecification requestSpecification;

    public static RequestSpecification getDefaultSpec(){
       return new  RequestSpecBuilder().setBaseUri(ConfigManager.getBaseUrl()).setContentType(ContentType.JSON).build();
    }

    public static RequestSpecification getAuthenticatedSpec(){
        return new  RequestSpecBuilder().setBaseUri(ConfigManager.getBaseUrl()).setContentType(ContentType.JSON).
                addHeader("Authorization", "Bearer " + AuthManager.getToken()).build();
    }


}
