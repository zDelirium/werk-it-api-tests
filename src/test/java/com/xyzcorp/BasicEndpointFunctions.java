package com.xyzcorp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;

/**
 * This class holds some basic functions for endpoints
 */
public class BasicEndpointFunctions {

    /**
     * Checks if the status code of an endpoint is the expected status code
     * @param sutURL the endpoint to test
     * @param expectedStatusCode status code expected to be obtained
     */
    void verifyGetEndpointStatusCode(String sutURL, int expectedStatusCode) {
        given()
            .relaxedHTTPSValidation()
            .when()
            .get(sutURL)
            .then()
            .assertThat()
            .statusCode(expectedStatusCode);
    }

    /**
     * Asserts that a field in JSON object from tested endpoint contains the expected value
     * @param JSONField the field for which its value needs to be asserted
     * @param expectedValue the value that the passed JSON field is expected to have
     * @param sutURL the endpoint to test
     */
    void assertEndpointFieldValue(String JSONField, String expectedValue, String sutURL) {
        given()
        .relaxedHTTPSValidation()
        .accept(ContentType.JSON)
        .when()
        .get(sutURL)
        .then()
        .assertThat()
        .body(JSONField, equalTo(expectedValue));
    }

}
