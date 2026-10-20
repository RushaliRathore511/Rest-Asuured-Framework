package client;

import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class ApiClient extends RequestSpecFactory{

    protected Response get(String endpoint) {
        return given().spec(RequestSpecFactory.getDefaultSpec()).get(endpoint);
    }

    protected Response post( String endpoint , Object body ){
        return given().spec(RequestSpecFactory.getDefaultSpec()).body(body).post(endpoint);
    }

    protected Response put ( String endpoint , Object body){
        return given().spec(RequestSpecFactory.getDefaultSpec()).body(body).put(endpoint);
    }

    protected Response delete(String endpoint){
        return given().spec(RequestSpecFactory.getDefaultSpec()).delete(endpoint);
    }

    protected Response postWithQueryParam(String endpoint , HashMap<String, String> params){
        return given().spec(RequestSpecFactory.getDefaultSpec()).param(String.valueOf(params)).post(endpoint);
    }
}
