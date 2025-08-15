package com.thetestingacdemy.Tests.Restfulbooker.CRUD;

import com.thetestingacdemy.Base.BaseTest;
import com.thetestingacdemy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {
    @Test
    @Description("TC#3 - Verify Health")
    public void testGETHealthCheck(){
        requestSpecification.basePath(APIConstants.PING_URL);
        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        assertActions.verifyTrue(response.asString().contains("Created"));
        validatableResponse.statusCode(201);
    }

}
