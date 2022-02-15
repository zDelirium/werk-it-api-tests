package com.xyzcorp;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class werkItLoginTest {
    @Test
    public void testSuccessfulLogin() {
        given()
                .relaxedHTTPSValidation()
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/login/admin/pilot")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
