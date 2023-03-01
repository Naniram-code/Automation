package Tests.TestNG.RestFullTestNgGrouping.RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1;

import payloads.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateToken {
    public static void main(String[] args) {
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        String BASE_URI = "https://restful-booker.herokuapp.com";
        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;
        requestSpecification.baseUri(BASE_URI)
                 .basePath("/auth").headers("content-Type", "application/json")
                .body(auth);//request Details(1)


        Response response=requestSpecification.post();//post method(2)

        validatableResponse = response.then(); //validation (3)
                validatableResponse.statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("token",Matchers.notNullValue())
                        .log().all();

                String ss=response.jsonPath().get("token");
                System.out.println("Token="+ss);


    }}