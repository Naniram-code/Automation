package RestAssuredProject.AssignmentREstAssured.ASSIGNMENT2;

import RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1.Booking;
import RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1.BookingDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateDeleteAND_Validate {
    //Global Variable
    String BookingID;
    String BASE_URI = "https://restful-booker.herokuapp.com";
    @Test(priority = 1)

    void PostRequest(){
            Booking booking = new Booking();
            booking.setFirstname("Nani Ram");
            booking.setLastname("Bhujel");
            booking.setTotalprice(2343);
            booking.setDepositpaid(true);

            BookingDate bookingDate=new BookingDate();
            bookingDate.setCheckin("2023-02-15");
            bookingDate.setCheckout("2023-02-25");

            booking.setBookingdates(bookingDate);
            booking.setAdditionalneeds("lunch and dinner");

            Gson gson=new Gson();
            String jsonObj=gson.toJson(booking);

        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;
        requestSpecification.baseUri(BASE_URI)
                .basePath("/booking").headers("content-Type", "application/json")
                .auth().basic("admin", "password123")
                .body(jsonObj);

        Response response=requestSpecification.post();
        validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.log().all();

        JsonPath extractor=response.jsonPath();//JsonPath assertion
        BookingID=extractor.get("bookingid").toString();
        System.out.println(BookingID);
        }
        @Test(priority = 2)
    void DeleteRequest() {
        String basePath = "booking/%s";//Modular operation basic java
        RestAssured.baseURI = BASE_URI;
        RestAssured.given()
                .basePath(String.format(basePath, BookingID)).contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .auth().basic("admin", "password123")
                .when().delete()
                .then()
                .body(equalTo("Created"))
                .statusCode(201)
                .log().all();
    }
    @Test(priority=3)
    void VarifyDeleteBookingByID() {
        String basePath = "booking/%s";
        RestAssured.baseURI = BASE_URI;
        RestAssured.given()
                .basePath(String.format(basePath, BookingID))
                .headers("content-Type", "application/json")
                .when().get()
                .then()
                .statusCode(404)
                .body(equalTo("Not Found"))
                .header("Content-Type", equalTo("text/plain; charset=utf-8"))
                .log().all();

    }
}

