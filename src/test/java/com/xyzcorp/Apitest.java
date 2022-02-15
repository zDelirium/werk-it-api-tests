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
import static org.hamcrest.Matchers.hasItem;

public class Apitest {


    @Test
    // test access to page
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

    }
    @Test
    // test  create profile
    // test   error in writting   key  username and lastname
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
    // test  create profile
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
                .statusCode(200)
                .log().all();

    }

    @Test
    // put  fonction noy implemented
    public void ReqPut() {
        JSONObject UserObject = new JSONObject()
                .put("username", "mami2")
                .put("email", "mami2@gmail.com")
                .put("firstName", "mamamam")
                .put("lastName", "dommmm")
                .put("password", "1111");


        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .put("https://staging.tiered-planet.net/werk-it-back-end/register/1")
                .then()
                .assertThat()
                .statusCode(404)
                .log().all();
    }

    @Test

    // test  access
    public void testweightsGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get(" https://staging.tiered-planet.net/werk-it-back-end/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)

                .log().all();

    }
    @Test
    // create new  exercise
    public void WeightPost() {
        JSONObject UserObject = new JSONObject()
                .put("name", "push")
                .put("sets", 3)
                .put("reps", 33)
                .put("pounds", 50)
                .put("userId", 9);



        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/werk-it-back-end/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();

    }

}
