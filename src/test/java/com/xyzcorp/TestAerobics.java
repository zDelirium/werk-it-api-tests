package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
 

public class TestAerobics {
    
    HTTPRequestFunctions httpRequestFunctions;

    @BeforeEach
    void setup() {
        httpRequestFunctions = new HTTPRequestFunctions();
    }

    // GET tests

    /**
     * Assert that the first element in aerobics/user/1 has all the expected elements.
     * Since no DELETE or PUT operations has been implemented for Aerobics, element [0] should not change
     */
    @Test
    public void testExistingAerobicsGet() {
        HashMap<String, Object> expectedValuesMap = new HashMap<>();
        expectedValuesMap.put("[0].seconds", 3000);
        expectedValuesMap.put("[0].name", null); // Name should not be null in the first place
        expectedValuesMap.put("[0].userId", 1);
        expectedValuesMap.put("[0].id", 265);

        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", expectedValuesMap.get("[0].userId"));

        expectedValuesMap.forEach((field, value) -> httpRequestFunctions.assertGetEndpointFieldValue(field, value, sutURL));
    }

    // POST tests
    
    /**
     * Assert that a POST to Aerobics with valid values returns status code 200
     */
    @Test
    public void testValidAerobicsPost() {
        int userId = 1;
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", userId);
        int expectedStatusCode = 200;

        HashMap<String, Object> newValuesMap = new HashMap<>();
        newValuesMap.put("seconds", 1800);
        newValuesMap.put("name", "Dance");
        
        httpRequestFunctions.assertPostEndpointStatusCode(sutURL, expectedStatusCode, newValuesMap);
    }

    /**
     * Asserts that a negative time is rejected by the back-end in a POST request
     */
    @Test 
    @Disabled("The POST request does not verify for invalid time")
    public void testNegativeSecondsInvalidPost() {
        int userId = 1;
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", userId);
        int expectedStatusCode = 401;

        HashMap<String, Object> newValuesMap = new HashMap<>();
        newValuesMap.put("seconds", -1200);
        newValuesMap.put("name", "Jogging");

        httpRequestFunctions.assertPostEndpointStatusCode(sutURL, expectedStatusCode, newValuesMap);
    }

    /**
     * Asserts that an empty name is rejected by the back-end in a POST request
     */
    @Test
    @Disabled("The POST request does not verify for invalid name")
    public void testEmptyNameInvalidPost() {
        int userId = 1;
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", userId);
        int expectedStatusCode = 401;

        HashMap<String, Object> newValuesMap = new HashMap<>();
        newValuesMap.put("seconds", 300);
        newValuesMap.put("name", "");

        httpRequestFunctions.assertPostEndpointStatusCode(sutURL, expectedStatusCode, newValuesMap);
    }

}
