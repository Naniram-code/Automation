package RestAssuredProject.AssignmentREstAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

    public class JSONSchemaValidation {


        public static void main(String[] args) throws JsonProcessingException {

            String BASE_URI = "https://restful-booker.herokuapp.com";


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
            validatableResponse.statusCode(200)
                    .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"))
                    .log().all().toString();

        }
    }




