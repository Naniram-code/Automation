package Testss;

import base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

//hvvbvbvbvbvbvbvbvbvbvbvbvbvbvb
import java.util.HashMap;

public class TC_Integration2  extends BaseTest{
    @Test(priority = 2, groups = "regression")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Booking can be Created")
    public void testCreatBook(ITestContext iTestContext) throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);

        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();
        ValidatableResponse validatableResponse = response.then().log().all();
        assertActions.verifyStatusCode(response);
        jsonPath = JsonPath.from(response.asString());
        assertActions.verifyResponseBody("Nani", "Nani", "nknkn");
        iTestContext.setAttribute("bookingid", jsonPath.getString("bookingid"));//set method
    }

    @Test(priority = 1, groups = {"Unit Test", "regression"})
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get all booking")
    void getAllRequest() {

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        ValidatableResponse validatableResponse = response.then().log().all();

    }

    @Test(priority=5,groups = "regression")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Booking can be Created")
    void filterByID(ITestContext iTestContext) throws JsonProcessingException {
        String bookingId = (String) iTestContext.getAttribute("bookingid");
        System.out.println("Pramod - " + bookingId);
       requestSpecification.basePath(APIConstants.CREATE_BOOKING+"/"+bookingId);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        ValidatableResponse validatableResponse = response.then().log().all();
        assertActions.verifyStatusCode(response);
    }
    @Test(priority=6,groups = "Unit Test")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Token  can be Created")
    void CreateToken() {
        getToken();
        ValidatableResponse validatableResponse = response.then().log().all();
        assertActions.verifyStatusCode(response);
        assertActions.verifyResponseBody(response.header("Content-Type"),
                "application/json; charset=utf-8","abc" );
    }
    @Test(priority = 7,groups = "regression")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("put request and Verify")
    void PutRequest(ITestContext iTestContext) throws JsonProcessingException {
            String token = getToken();
            String bookingId = (String) iTestContext.getAttribute("bookingid");

            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
            response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                    .when().body(payloadManager.updatedPayload()).put();

            validatableResponse = response.then().log().all();
            validatableResponse.body("firstname", Matchers.is("RamKumar"));


        }
    @Test(priority = 9,groups = "regression")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Partial Update FirstName and Last Name then verify ")
    void testPartialUpdate(ITestContext iTestContext) throws JsonProcessingException {
        HashMap data = new HashMap();
        data.put("firstname", "jack");
        data.put("lastname", "MAmaa");
        String token = getToken();
        String bookingId = (String) iTestContext.getAttribute("bookingid");

        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .auth().basic(APIConstants.UserName,APIConstants.Password)
                .when().body(data).patch();
      validatableResponse = response.then().log().all();
       validatableResponse.body("firstname", Matchers.is("jack"));
       validatableResponse.body("lastname", Matchers.is("MAmaa"));

    }
    @Test(priority=10,groups = "regression")
    @Owner("NaniRam")
    @Severity(SeverityLevel.NORMAL)
    @Description("Getting  Booking Id By Name")
    void filterId_By_ValidName() {//booking?firstname=Jim&lastname=Brown
        requestSpecification.contentType(ContentType.JSON)
                .queryParam("firstname","jack")
                .queryParam("lastname","MAmaa")
                .basePath(APIConstants.UPDATE_BOOKING );
        response = RestAssured.given().spec(requestSpecification)
                .auth().basic(APIConstants.UserName,APIConstants.Password)
                .when().get();
        validatableResponse = response.then().log().all();
        assertActions.verifyStatusCode(response);
        //System.out.println("User Name="+APIConstants.UserName);

    }
    }

