package classnote.udemy.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;

    public class Classcode {
        //Global variable
        String BASE_URI = "https://restful-booker.herokuapp.com";
        @Test(priority = 1)
        void testPost() {

            Map<String,Object> data = new HashMap<>();

            data.put("firstname", "NaniRam");
            data.put("lastname", "Bhujel");
            data.put("totalprice", 1000);
            data.put("depositpaid", true);

            Map<String,Object> bookD = new HashMap<>();
            bookD.put("checkin", "2018-01-01");
            bookD.put("checkout", "2019-01-01");
            data.put("bookingdates", bookD);
            data.put("additionalneeds", "Breakfast");


            RestAssured.baseURI = BASE_URI;
            RestAssured.given().basePath("/booking").headers("content-Type", "application/json")
                    .cookie("token", "fd771e317011e1f")
                    //.auth().basic("admin", "password123")
                    .body(data)
                    .when().post()

                    .then()
                    .statusCode(200)

                    .body("booking.firstname", equalTo("NaniRam"))
                    .body("booking.lastname", equalTo("Bhujel"))
                    .body("booking.totalprice", equalTo(1000))
                    .body("booking.depositpaid", equalTo(true))
                    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                    .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                    .body("booking.additionalneeds", equalTo("Breakfast"))
                    .log().all();
        }


    }




