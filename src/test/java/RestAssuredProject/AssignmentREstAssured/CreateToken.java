package RestAssuredProject.AssignmentREstAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

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
                .auth().basic("admin", "password123")
                .body(auth)
                .when().post()
                .then()
                .log().all().statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .assertThat()
                .body("token", Matchers.notNullValue());
        Response response=requestSpecification.post();

        validatableResponse = response.then();
        String ss= validatableResponse.extract().path("token");
        System.out.println("Token="+ss);


    }}