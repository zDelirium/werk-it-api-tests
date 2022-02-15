package com.xyzcorp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;

import io.restassured.http.ContentType;

/**
 * This class holds some basic functions for HTTP requests assertions, notably GET and POST
 */
public class HTTPRequestFunctions {

    /**
     * Checks if the status code of an endpoint is the expected status code for a GET request
     * @param sutURL endpoint for which a GET request is done
     * @param expectedStatusCode status code expected to be obtained from GET request
     */
    void assertGetEndpointStatusCode(String sutURL, int expectedStatusCode) {
        given()
            .relaxedHTTPSValidation()
            .when()
            .get(sutURL)
            .then()
            .assertThat()
            .statusCode(expectedStatusCode);
    }

    /**
     * Asserts that a field in JSON object from tested endpoint contains the expected value for a GET request
     * @param JSONField field for which its value needs to be asserted
     * @param expectedValue value that the passed JSON field is expected to have
     * @param sutURL endpoint for which the GET request is done
     */
    void assertGetEndpointFieldValue(String JSONField, Object expectedValue, String sutURL) {
        given()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .when()
            .get(sutURL)
            .then()
            .assertThat()
            .body(JSONField, equalTo(expectedValue));
    }

    /**
     * Asserts that a POST request to an endpoint returns the expected status code
     * @param sutURL endpoint in which the POST request is done
     * @param expectedStatusCode HTTP status code that is expected by the POST request
     * @param newValuesMap map of fields and values to be added in POST request
     */
    void assertPostEndpointStatusCode(String sutURL, int expectedStatusCode, HashMap<String, Object> newValuesMap) {
        JSONObject newJSONObject = new JSONObject();
    
        newValuesMap.forEach((name, value) -> newJSONObject.put(name, value));
    
        given()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(newJSONObject.toString())
            .when()
            .post(sutURL)
            .then()
            .assertThat()
            .statusCode(expectedStatusCode);
    }

}
