package com.xyzcorp;

import static io.restassured.RestAssured.given;

/**
 * This class holds some basic functions for endpoints
 */
public class BasicEndpointFunctions {

    /**
     * Checks if the status code of an endpoint is the expected status code
     * @param url the endpoint
     * @param expectedStatusCode status code expected to be obtained
     */
    void verifyGetEndpointStatusCode(String url, int expectedStatusCode) {
        given()
            .relaxedHTTPSValidation()
            .when()
            .get(url)
            .then()
            .assertThat()
            .statusCode(expectedStatusCode);
    }

}
