package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
 

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
        HashMap<String, Object> expectedValuesMap = new HashMap<>();
        expectedValuesMap.put("[0].seconds", 3000);
        expectedValuesMap.put("[0].name", null);
        expectedValuesMap.put("[0].userId", 1);
        expectedValuesMap.put("[0].id", 265);

        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/%d", expectedValuesMap.get("[0].userId"));

        expectedValuesMap.forEach((field, value) -> basicEndpointFunctions.assertEndpointFieldValue(field, value, sutURL));
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

        HashMap<String, Object> newValues = new HashMap<>();
        newValues.put("seconds", 1800);
        newValues.put("name", "Dance");
        
        basicEndpointFunctions.assertPostEndpointStatusCode(sutURL, expectedStatusCode, newValues);
    }

    @Test 
    public void testInvalidSecondsPost() {

    }

    @Test
    public void testInvalidNamePost() {

    }

}
