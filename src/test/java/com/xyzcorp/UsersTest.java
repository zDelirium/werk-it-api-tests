package com.xyzcorp;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class UsersTest {

    //go to the website
    //access backend 
    //get user be sure you have a psotive response
    //display info in console

    @Test
    public void testGetUserLogin() {
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/login/sallys/point234")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
    }

    
    
}
