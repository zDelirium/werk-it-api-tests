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
public class Apitest {


    @Test
    public void testloginGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/login/aliio/4444")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();
        //System.out.println();
    }
    @Test
    public void failtestRegisterPost() {
        JSONObject UserObject = new JSONObject()
                .put("username", "m")
                .put("email", "uuui@gmail.com")
                .put("firstname", "dddghh")
                .put("lastname", "dooi")
                .put("password", "1111");



        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/werk-it-back-end/register")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();

    }
    @Test
    public void corectionfailtestRegisterPost() {
        JSONObject UserObject = new JSONObject()
                .put("username", "mami")
                .put("email", "mami@gmail.com")
                .put("firstName", "mamamam")
                .put("lastName", "dommmm")
                .put("password", "9988");



        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/werk-it-back-end/register")
                .then()
                .assertThat()
                .statusCode(200);
                //.log().all();

    }
}
