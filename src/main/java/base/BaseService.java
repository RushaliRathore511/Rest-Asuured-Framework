package base;

import filter.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {

private final RequestSpecification requestSpecification;

    public static final String BASE_URL  = "http://64.227.160.186:8080";

   //Want to make this run only once that's static
    static {
        RestAssured.filters(new LoggingFilter());
    }

    public BaseService(){
        requestSpecification = RestAssured.given().baseUri(BASE_URL);
    }

    protected Response postRequest(String endPoint , Object payload){
      return requestSpecification.contentType(ContentType.JSON).body(payload).post(endPoint);
    }

    protected Response postRequest(String baseurl , String endPoint , Object payload){
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(baseurl+endPoint);
    }

    protected void setAuthToken(String token ){
         requestSpecification.header("Authorization","Bearer " + token);
    }

    protected Response getRequest(String endPoint){
        return requestSpecification.contentType(ContentType.JSON).get(endPoint);
    }

    protected Response putRequest(String endPoint , Object payload){
        return requestSpecification.contentType(ContentType.JSON).body(payload).put(endPoint);
    }
}
