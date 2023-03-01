package Tests.TestNG.RestFullTestNgGrouping.RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1;

import io.restassured.RestAssured;

public class GetAllBooking {
    public static void main(String[] args) {
        String BASE_URI = "https://restful-booker.herokuapp.com";

            RestAssured.baseURI = BASE_URI;
            RestAssured.given().basePath("/booking").headers("content-Type", "application/json")
                    .when().get()
                    .then().log().all().statusCode(200);
        }
    }

