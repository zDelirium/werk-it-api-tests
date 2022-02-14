package com.xyzcorp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.assertion.DetailedCookieAssertion;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Login {


    @Test
    public void testloginget() {

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

    @Test
    public void testloginpost() {
        JSONObject bananaObject = new JSONObject()
                .put("name", "Ananas")
                .put("description", "a yyyyyyyyyyyyytreat");



        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bananaObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/werk-it-back-end/login/username/password")
                .then()
                .assertThat()
                .statusCode(201);

    }


    @Test
    public void testloginput() {
        JSONObject bananaObject = new JSONObject()
                .put("name", "Ananas")
                .put("description", "a yyyyyyyyyyyyytreat");

        System.out.println(bananaObject);

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bananaObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .statusCode(201);
    }






    }


