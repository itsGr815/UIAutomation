package com.dFarm.qa.dFarm.testsuite.api;

import com.dFarm.qa.dFarm.constants.APIEndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginDemo implements APIEndPoints {

    @Test(description = "Login Demo")
    public void verifyAdminLogin(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_id", "ganesh.g@dfarm.in");
        jsonObject.put("password", "Password@123");

        ValidatableResponse response = given().baseUri(ADMIN_DEV)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post(ADMIN_LOGIN)
                .then()
                .statusCode(200)
                .body("data.users[0].employee_id", equalTo("VDF0008"))
                .body("message", equalTo("success"));;

        String responseBody = response.extract().body().asString();
        System.out.println("Response body: " + responseBody);
         // response.body("data.user[0].create_date", equalTo("2023-03-06T04:26:31.000Z"));
    }

  /*  @Test(description = "Login Demo")
    public void addAuthorizedUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_id", "ganesh.g@dfarm.in");
        jsonObject.put("password", "Password@123");
        jsonObject.put("Authorization", "");

        ValidatableResponse response = given().baseUri(ADMIN_DEV)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post("/api/login/check")
                .then()
                .statusCode(200)
                .body("data.users[0].employee_id", equalTo("VDF0008"))
                .body("message", equalTo("success"));;

        // response.body("data.user[0].create_date", equalTo("2023-03-06T04:26:31.000Z"));
    }*/


//    @Test
//    public void WeatherMessageBody()
//    {
//        RestAssured.baseURI = ADMIN_DEV;
//        RequestSpecification httpRequest = RestAssured.given();
//        Response response = httpRequest.get("user[0]");
//
//        // Retrieve the body of the Response
//        ResponseBody body = response.getBody();
//
//        // By using the ResponseBody.asString() method, we can convert the  body
//        // into the string representation.
//        System.out.println("Response Body is: " + body.asString());
//    }

}
