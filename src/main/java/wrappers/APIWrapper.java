package wrappers;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;


public class APIWrapper {
    public static int getStatusCodePost(String url) {
        return given()
                .when()
                .post(url)
                .andReturn().getStatusCode();
    }

    public static JsonPath getJsonBodyEmptyPost(String url) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .post(url)
                .then()//.log().all()
                .extract().body().jsonPath();
    }

    public static JsonPath getJsonBodyJsonPost(String url, JSONObject body) {
        return given()
                .when()
                .body(body)
                .contentType(ContentType.JSON)
                .post(url)
                .then()//.log().all()
                .extract().body().jsonPath();
    }
}
