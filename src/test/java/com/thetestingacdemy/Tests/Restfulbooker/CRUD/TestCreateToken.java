package com.thetestingacdemy.Tests.Restfulbooker.CRUD;

import com.thetestingacdemy.Base.BaseTest;
import com.thetestingacdemy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Aishwarya")
    @Description("TC#1 - Create Token & Verify")
    public void testTokenPOST(){

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.setAuthPayload()).post();

        //Extraction (JSON String response to Java Object)
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);

        //validation of the request
        assertActions.verifyStringKeyNotNull(token);
    }

    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Aishwarya")
    @Description("TC#2 - Create Invalid Token & Verify")
    public void testTokenPOST_Negative(){

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body("{}").post();

        //Extraction (JSON String response to Java Object)
        String invalid_reason = payloadManager.getInvalidResponse(response.asString());
        System.out.println(invalid_reason);

        //validation of the request
        assertActions.verifyStringKey(invalid_reason,"Bad credentials");
    }
}
