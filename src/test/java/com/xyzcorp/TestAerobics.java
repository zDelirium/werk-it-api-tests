package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;

import io.restassured.http.ContentType;
 

public class TestAerobics {
    
    BasicEndpointFunctions basicEndpointFunctions;

    @BeforeEach
    void setup() {
        basicEndpointFunctions = new BasicEndpointFunctions();
    }

    // GET tests

    /**
     * Assert that the first element in aerobics/user/1 has all the expected elements.
     * Since no DELETE or PUT operations has been implemented for Aerobics, element [0] should not change
     */
    @Test
    public void testExistingAerobicsGet() {
        int expectedUserId = 1;
        int expectedSeconds = 3000, expectedId = 265;
        String expectedName = null;

        String secondsField = "[0].seconds", nameField = "[0].name", userIdField = "[0].userId", idField = "[0].id";

        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", expectedUserId);

        basicEndpointFunctions.assertEndpointFieldValue(secondsField, expectedSeconds, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(nameField, expectedName, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(userIdField, expectedUserId, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(idField, expectedId, sutURL);

    }

    // POST tests
    /**
     * Assert that a POST to Aerobics with valid values is functional
     */
    @Test
    public void testValidAerobicsPost() {
        int userId = 1;
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", userId);
        int expectedStatusCode = 200;

        HashMap<String, Object> newValues = new HashMap<>();
        newValues.put("seconds", 1800);
        newValues.put("name", "Jogging");
        
        // TODO refactor everything below in BasicEndpointFunctions.java
        JSONObject newAreobicObject = new JSONObject();

        newValues.forEach((name, value) -> newAreobicObject.put(name, value));

        given()
            .relaxedHTTPSValidation()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(newAreobicObject.toString())
            .when()
            .post(sutURL)
            .then()
            .assertThat()
            .statusCode(expectedStatusCode);
    }

    @Test 
    public void testInvalidSecondsPost() {

    }

    @Test
    public void testInvalidNamePost() {

    }

}
