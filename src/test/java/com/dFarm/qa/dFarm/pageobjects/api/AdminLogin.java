package com.dFarm.qa.dFarm.pageobjects.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AdminLogin {

    public void verifyLogin(String endPoint, String redirect, JSONObject jsonObject){

        ValidatableResponse response = given().baseUri(endPoint)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post(redirect)
                .then()
                .statusCode(200)
                .body("data.users[0].employee_id", equalTo("VDF0008"))
                .body("message", equalTo("success"));;

        response.body("data.user[0].create_date", equalTo("2023-03-06T04:26:31.000Z"));
    }
}
