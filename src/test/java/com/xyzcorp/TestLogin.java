package com.xyzcorp;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class holds the tests for login endpoints
 */
public class TestLogin {

    private HTTPRequestFunctions httpRequestFunctions;
    private static String baseURL;

    @BeforeAll
    static void setupAll() {
        baseURL = "https://staging.tiered-planet.net/werk-it-back-end/login";
    }

    @BeforeEach
    void setup() {
        httpRequestFunctions = new HTTPRequestFunctions();
    }

    /**
     * Verify that an existing login endpoint returns status code 200, and that the
     * JSON string contains the proper username and password
     */
    @Test
    public void testValidLoginEndpoint() {
        HashMap<String, String> loginValuesMap = new HashMap<>();
        loginValuesMap.put("username", "dhinojosa");
        loginValuesMap.put("password", "swimming");

        String sutURL = String.format("%s/%s/%s", baseURL, loginValuesMap.get("username"),
                loginValuesMap.get("password"));
        int expectedStatusCode = 200;

        httpRequestFunctions.assertGetEndpointStatusCode(sutURL, expectedStatusCode);

        loginValuesMap.forEach((field, expectedValue) -> httpRequestFunctions.assertGetEndpointFieldValue(field,
                expectedValue, sutURL));
    }

    /**
     * Verify that an non-existing login endpoint returns status code 401
     */
    @Test
    public void testInvalidLoginEndpoint() {
        String userName = "admin", password = "badclock";
        String sutURL = String.format("%s/%s/%s", baseURL, userName, password);
        int expectedStatusCode = 401;

        httpRequestFunctions.assertGetEndpointStatusCode(sutURL, expectedStatusCode);
    }

}