package RestAssuredProject.AssignmentREstAssured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class PutRequestByID {
    public static void main(String[] args) throws JsonProcessingException {

            String BASE_URI = "https://restful-booker.herokuapp.com";


            Booking booking = new Booking();
            booking.setFirstname("RamKumar");
            booking.setLastname("Modhi");
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
                    .basePath("/booking/122").cookie("token", "fd771e317011e1f")
                    .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                    .cookie("token", "fd771e317011e1f")
                    .contentType(ContentType.JSON)
                    .accept("application/json")
                    .body(jsonObj);

            Response response=requestSpecification.put();
            validatableResponse = response.then();
            validatableResponse.log().all().toString();
            Assert.assertEquals(response.jsonPath().get("firstname").toString(),"RamKumar");
            Assert.assertEquals(response.jsonPath().get("lastname").toString(),"Modhi");
            Assert.assertEquals(response.jsonPath().get("totalprice").toString(),"2343");
            Assert.assertEquals(response.jsonPath().get("depositpaid").toString(),"true");
            Assert.assertEquals(response.jsonPath().get("bookingdates.checkin").toString(),"2023-02-15");
            Assert.assertEquals(response.jsonPath().get("bookingdates.checkout").toString(),"2023-02-25");
            Assert.assertEquals(response.jsonPath().get("additionalneeds").toString(),"lunch and dinner");
            Assert.assertEquals(response.getStatusCode(),200);
            Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        }
    }


