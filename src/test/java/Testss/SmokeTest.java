package Testss;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SmokeTest  extends BaseTest {
    @Test(invocationCount = 5)//Repeat multiple time Test method
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Booking can be Created")
    public void testCreatBook() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);

        response = RestAssured.given().spec(requestSpecification)
        .when().body(payloadManager.createPayload()).post();

        ValidatableResponse validatableResponse = response.then().log().all();
        assertActions.verifyStatusCode(response);
        assertActions.verifyResponseBody("Nani","Nani","ok");

    }
    @AfterMethod
    public void tearDown() {
        // Clean up resources
        // ...
}}

