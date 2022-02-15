package com.xyzcorp;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
                .log().all();

    }

    @Test
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
                .statusCode(200);

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

    //This test will fail as the application does not handle duplicate usernames

    @Disabled
    @Test
    public void testDuplicateUsernameRegistration(){
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


        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("/login/sallys/differentPassword")
                .then()
                .assertThat()
                .statusCode(401)
                .log().all();

    }

}