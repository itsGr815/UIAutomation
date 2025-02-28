package com.dFarm.qa.dFarm.testsuite.api;

import com.dFarm.qa.dFarm.constants.APIEndPoints;
import com.dFarm.qa.dFarm.util.execution.AllDataHolder;
import com.dFarm.qa.dFarm.util.execution.FlexFrameWorkRunTimeException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ApiTests implements APIEndPoints {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ADMIN_DEV; // Replace with your Spring Boot API base URL
    }

    @Test
    public void testCreateUser() {

        // Create a JSON request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("login_id", "ganesh.g@dfarm.in");
        requestBody.put("password", "Password@123");

        // Perform a POST request to create a new user
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post(ADMIN_LOGIN);

        String responseBody = response.body().asString();
        System.out.println("Response body: " + responseBody);
        // Validate the response
       Assert.assertEquals(response.getStatusCode(), 200); // Assuming the API returns 201 for a successful creation
       Assert.assertEquals(response.jsonPath().get("data.users[0].employee_id"), "VDF0008");
//        Assert.assertEquals(response.jsonPath().get("email"), "johndoe@example.com");
    }



    @Test(testName = "TC0AdminLoginTest")
    public void DemoTest()throws FlexFrameWorkRunTimeException {
        Map<String, String> serviceHeadersData = new HashedMap();
        serviceHeadersData.put("user_name", "wholeorder");
       // AllDataHolder.getUtil().report(1, "Endpoint :", ADMIN_DEV+ADMIN_LOGIN, "");
        try {
            HttpResponse response =  AllDataHolder.getWsCaller().getRequest(serviceHeadersData, ADMIN_DEV+ADMIN_LOGIN);

            System.out.println("Ganesh" + response.toString());


        }catch (Exception e){
            throw new FlexFrameWorkRunTimeException(e);
        }
    }
}