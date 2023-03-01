package Tests.TestNG.RestFullTestNgGrouping.RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class DeleteRequestByID {
    public static void main(String[] args) {
    String BASE_URI = "https://restful-booker.herokuapp.com";

         RestAssured.baseURI = BASE_URI;
         RestAssured.given()
                    .basePath("/booking/794").contentType(ContentType.JSON)
                    .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                    .auth().basic("admin", "password123")
                    .when().delete()
                    .then()
                    .body(equalTo("Created"))
                     .statusCode(201)
                    .log().all();
        }
    }
