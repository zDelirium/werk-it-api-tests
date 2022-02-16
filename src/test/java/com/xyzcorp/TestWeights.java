package com.xyzcorp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;


public class TestWeights {

    HTTPRequestFunctions httpRequestFunctions;

    @BeforeEach
    void setup() {
        httpRequestFunctions = new HTTPRequestFunctions();
    }

    @Test
    public void testWeightExistance() {
        HashMap<String, Object> expectedValuesMap = new HashMap<>();
        expectedValuesMap.put("[0].id", 2);
        expectedValuesMap.put("[0].name", "bench press");
        expectedValuesMap.put("[0].sets", 2);
        expectedValuesMap.put("[0].reps", 20);
        expectedValuesMap.put("[0].pounds", 40);
        expectedValuesMap.put("[0].userId", 1);

        String sutURL = String.format("https://staging.tiered-planet.net/werk-it-back-end/weights/user/%d",
                expectedValuesMap.get("[0].userId"));

                expectedValuesMap.forEach((field, value) -> httpRequestFunctions.assertGetEndpointFieldValue(field, value, sutURL));
    }

}
