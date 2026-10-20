package base;

import client.RequestSpecFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected Response postRequest(String endPoint, Object payload) {
        return given().spec(RequestSpecFactory.getDefaultSpec())
                .body(payload)
                .post(endPoint);
    }

    protected Response postRequest(String baseUrl, String endPoint, Object payload) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endPoint);
    }

    protected Response getRequest(String endPoint) {
        return given().spec(RequestSpecFactory.getDefaultSpec()).get(endPoint);
    }

    protected Response getRequest(String endPoint, String token) {
        return given().spec(RequestSpecFactory.getAuthenticatedSpec(token)).get(endPoint);
    }

    protected Response putRequest(String endPoint, Object payload) {
        return given().spec(RequestSpecFactory.getDefaultSpec())
                .body(payload)
                .put(endPoint);
    }

    protected Response putRequest(String endPoint, Object payload, String token) {
        return given().spec(RequestSpecFactory.getAuthenticatedSpec(token))
                .body(payload)
                .put(endPoint);
    }
}
