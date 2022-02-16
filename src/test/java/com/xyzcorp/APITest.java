package com.xyzcorp;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class APITest {


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
                .body("password",equalTo("4444"))
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
                .body("email",equalTo("uuui@gmail.com"))
                .body("firstName",nullValue())
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
                .body("username",equalTo("mami"))
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

    //  test access to list weight exercices for user
    public void testweightsGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get(" https://staging.tiered-planet.net/werk-it-back-end/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].name",equalTo("bench press"))
                .log().all();

    }

    @Test
    // create new  exercise weight
    public void WeightPost() {
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
                .post("https://staging.tiered-planet.net/werk-it-back-end/weights/user/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("push"))
                .log().all();

    }

    @Test
    // test access to list aerobics exercices for user
    public void testAerorobicsGet() {

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/19")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].seconds",equalTo(783))
                .log().all();
    }
    @Test
    // create new  exercise aerobics
    public void aerobicsPost() {
        JSONObject UserObject = new JSONObject()
                .put("name", "dance")
                .put("seconds", 1000);



        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UserObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/werk-it-back-end/aerobics/user/19")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("dance"))
                .log().all();

    }

}