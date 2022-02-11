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
     * Verify that an existing login endpoint returns status code 200
     */
    @Test
    public void testValidLoginEndpoint() {
        String userName  = "admin", password = "pilot";
        String url = String.format("https://staging.tiered-planet.net/werk-it-back-end/login/%s/%s", userName, password);
        int expectedStatusCode = 200;

        basicEndpointFunctions.verifyGetEndpointStatusCode(url, expectedStatusCode);
    }

    /**
     * Verify that an non-existing login endpoint returns status code 401
     */
    @Test
    public void testInvalidLoginEndpoint() {
        String userName  = "admin", password = "badclock";
        String url = String.format("https://staging.tiered-planet.net/werk-it-back-end/login/%s/%s", userName, password);
        int expectedStatusCode = 401;

        basicEndpointFunctions.verifyGetEndpointStatusCode(url, expectedStatusCode);
    }

}
