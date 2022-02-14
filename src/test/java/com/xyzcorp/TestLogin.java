package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class holds the tests for login endpoints
 */
public class TestLogin {

    BasicEndpointFunctions basicEndpointFunctions;

    @BeforeEach
    void setup() {
        basicEndpointFunctions = new BasicEndpointFunctions();
    }
    
    /**
     * Verify that an existing login endpoint returns status code 200, and that the JSON string contains the proper username and password
     */
    @Test
    public void testValidLoginEndpoint() {
        String userNameField = "username", passwordField = "password";
        String userName  = "sallys", password = "point234";
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/login/%s/%s", userName, password);
        int expectedStatusCode = 200;

        // Assert that the endpoint is valid
        basicEndpointFunctions.assertEndpointStatusCode(sutURL, expectedStatusCode);
        
        // Assert that the username field has the proper username
        basicEndpointFunctions.assertEndpointFieldValue(userNameField, userName, sutURL);

        basicEndpointFunctions.assertEndpointFieldValue(passwordField, password, sutURL);

    }

    /**
     * Verify that an non-existing login endpoint returns status code 401
     */
    @Test
    public void testInvalidLoginEndpoint() {
        String userName  = "admin", password = "badclock";
        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/login/%s/%s", userName, password);
        int expectedStatusCode = 401;

        basicEndpointFunctions.assertEndpointStatusCode(sutURL, expectedStatusCode);
    }

}
