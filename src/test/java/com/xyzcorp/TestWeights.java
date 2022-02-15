package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;

import io.restassured.http.ContentType;

public class TestWeights {

    BasicEndpointFunctions basicEndpointFunctions;

    @BeforeEach
    void setup() {
        basicEndpointFunctions = new BasicEndpointFunctions();
    }

    // GET tests

    /**
     * Assert that the first element in aerobics/user/1 has all the expected
     * elements.
     * Since no DELETE or PUT operations has been implemented for Aerobics, element
     * [0] should not change
     */
    @Test
    public void testWeightExistance() {
        int expectedUserId = 1;
        int expectedpounds = 40, expectedReps = 20, expectedSets = 2, expectedWeightId = 2;
        String expectedName = "bench press";

        String setsField = "[0].sets", nameField = "[0].name",
                userIdField = "[0].userId", weightIdField = "[0].id", repsField = "[0].reps",
                poundsField = "[0].pounds";

        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/weights/user/%d",
                expectedUserId);

        basicEndpointFunctions.assertEndpointFieldValue(weightIdField, expectedWeightId, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(userIdField, expectedUserId, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(nameField, expectedName, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(setsField, expectedSets, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(repsField, expectedReps, sutURL);
        basicEndpointFunctions.assertEndpointFieldValue(poundsField, expectedpounds, sutURL);

    }

}
