package com.xyzcorp;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class APITest {


    @BeforeAll
    public static void setup() {
        baseURI = "https://staging.tiered-planet.net/werk-it-back-end";
    }

    @Test
    public void testLoginGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("/login/aliio/4444")
                .then()
                .assertThat()
                .statusCode(200)
                .body("password", equalTo("4444"))
                .log().all();
    }

    @Test
    public void testInvalidLoginGet() {
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("/login/ThisUser/DoesNotExist")
                .then()
                .assertThat()
                .statusCode(401)
                .log().all();
    }

    @Test
    // test  create profile
    // test   error in writting   key  username and lastname
    public void failTestRegisterPost() {

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
                .post("/register")
                .then()
                .assertThat()
                .statusCode(200)
                .body("email", equalTo("uuui@gmail.com"))
                .body("firstName", nullValue())
                .log().all();

    }

    @Test
    // test  create profile
    public void correctionFailTestRegisterPost() {
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
                .post("/register")
                .then()
                .assertThat()
                .statusCode(200)
                .body("username", equalTo("mami"));


    }

    @Test
    @Disabled
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
                .put("/register/1")
                .then()
                .assertThat()
                .statusCode(404)
                .log().all();
    }

    @Test
    public void testNoOverwritingUserInfo() {
        JSONObject UserObject = new JSONObject()
                .put("username", "sallys")
                .put("email", "sally@aol.com")
                .put("firstName", "NotSally")
                .put("lastName", "NotSimpson")
                .put("password", "point234");

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("/register");

        given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .param("postId", "2")
                .when()
                .get("/login/sallys/point234")
                .then()
                .assertThat().statusCode(200)
                .body("firstName", equalTo("Sally"))
                .body("lastName", equalTo("Simpson"));

    }


    @Test

    //  test access to list weight exercices for user
    public void testWeightsGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].name", equalTo("bench press"))
                .log().all();

    }

    @Test
    // create new  exercise weight
    public void testWeightPost() {
        JSONObject UserObject = new JSONObject()
                .put("name", "push")
                .put("sets", 3)
                .put("reps", 33)
                .put("pounds", 150);
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("push"))
                .log().all();

    }


    //This test will fail as the application does not handle duplicate usernames

    @Disabled
    @Test
    public void testDuplicateUsernameRegistration() {
        JSONObject UserObject = new JSONObject()
                .put("username", "sallys")
                .put("email", "sally@aol.com")
                .put("firstName", "Sally")
                .put("lastName", "Simpson")
                .put("password", "differentPassword");


        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("/register");
    }


    @Test
    // test access to list aerobics exercices for user
    public void testAerobicsGet() {


        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("/aerobics/user/19")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].seconds", equalTo(783))
                .log().all();
    }

    @Test
    // create new  exercise aerobics
    public void testAerobicsPost() {
        JSONObject UserObject = new JSONObject()
                .put("name", "dance")
                .put("seconds", 1000);


        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("/aerobics/user/19")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("dance"))
                .log().all();

    }

}