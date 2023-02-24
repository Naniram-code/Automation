package RestFullTestNgGrouping;

import RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1.Auth;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import static org.hamcrest.Matchers.equalTo;
public class Restful_bookerTest {
    //Global variable
    String BASE_URI = "https://restful-booker.herokuapp.com";

    @Test(priority = 1,groups = {"Unit Test","regression"})
    void getAllRequest() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("/booking").headers("content-Type", "application/json")
                .when().get()
                .then().log().all().statusCode(200);
    }

    @Test(priority = 2,groups = "regression")
    void PingRequest() {
        RestAssured.baseURI = BASE_URI;
        //RequestSpecification r = RestAssured.given();//This line of code creates an instance of the
        // RequestSpecification class using the given() method from the RestAssured class.
        //String ss= r.body().toString();
        RestAssured.given().basePath("/ping")
                //.auth().basic("admin", "password123")
                .when().get()
                .then()
                .log().all()
                .body(equalTo("Created"));
    }

    @Test(priority = 3,groups="Unit Test")
    void testPost() {

        HashMap data = new HashMap();
        data.put("firstname", "NaniRam");
        data.put("lastname", "Bhujel");
        data.put("totalprice", 1000);
        data.put("depositpaid", true);

        HashMap bookD = new HashMap();
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

    @Test(priority=4,groups = {"Unit Test","regression"})
    void testPostusingJsonLibrary() {
        JSONObject jdata = new JSONObject();
        jdata.put("firstname", "NNaniRam");
        jdata.put("lastname", "BBhujel");
        jdata.put("totalprice", 1000);
        jdata.put("depositpaid", true);
        JSONObject Dbook = new JSONObject();
        Dbook.put("checkin", "2023-01-01");
        Dbook.put("checkout", "2023-01-10");
        jdata.put("bookingdates", Dbook);
        jdata.put("additionalneeds", "Lunch and Dinner");

        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("/booking").headers("content-Type", "application/json")
                .cookie("token", "fd771e317011e1f")
                //.auth().basic("admin", "password123")
                .body(jdata.toString())
                .when().post()

                .then()
                .statusCode(200)

                .body("booking.firstname", equalTo("NNaniRam"))
                .body("booking.lastname", equalTo("BBhujel"))
                .body("booking.totalprice", equalTo(1000))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2023-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2023-01-10"))
                .body("booking.additionalneeds", equalTo("Lunch and Dinner"))
                .log().all();
    }


    @Test(priority=5,groups = "regression")
    void filterByID() {
        RestAssured.baseURI = BASE_URI;
        String BookingId="3";
        RestAssured.given()
                .pathParam("mypath","booking")// mypath(variable) =user define variable and booking=path parameters (mypath=booking)
                //.pathParam("bookingId","3")//bookingid(variable)=3(value) ---( option 1)
                .pathParam("bookingId",BookingId)//bookingId(variable)=BookingId=3(Value)---(option 2)
                .basePath("{mypath}/{bookingId}").//we used path parameters as variable here
                headers("content-Type", "application/json")
                .when().get()
                .then()
                .log().all().statusCode(200)
                //.body("firstname", equalTo("Bob"))
                .header("Content-Type", equalTo("application/json; charset=utf-8"));

    }

    @Test(priority=6,groups = "Unit Test")
    void CreateToken() {
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("auth").headers("content-Type", "application/json")
                .auth().basic("Admin", "password123")
                .body(auth)
                .when().post()
                .then()
                .log().all().statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"));
    }

    @Test(priority = 7,groups = "regression")
    void PutRequest() {

        JSONObject jdata = new JSONObject();
        jdata.put("firstname", "jack");
        jdata.put("lastname", "MA");
        jdata.put("totalprice", 1100);
        jdata.put("depositpaid", true);
        JSONObject Dbook = new JSONObject();
        Dbook.put("checkin", "2021-01-01");
        Dbook.put("checkout", "2021-02-01");
        jdata.put("bookingdates", Dbook);
        jdata.put("additionalneeds", "Lunch");

        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("/booking/165").cookie("token", "fd771e317011e1f")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .cookie("token", "fd771e317011e1f")
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(jdata.toString())
                .when().put()
                .then()
                .statusCode(200)
                .body("firstname", equalTo("jack"))
                .body("lastname", equalTo("MA"))
                .body("totalprice", equalTo(1100))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2021-01-01"))
                .body("bookingdates.checkout", equalTo("2021-02-01"))
                .body("additionalneeds", equalTo("Lunch"))
                .log().all();
    }

    @Test(priority = 8,groups = "regression")
    void testDelete() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("/booking/346").contentType(ContentType.JSON)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .auth().basic("admin", "password123")
                .when().delete()
                .then()
                .statusCode(201)
                .log().all();
    }
    @Test(priority = 9,groups = "regression")
    void testPartialUpdate() {
        HashMap data = new HashMap();
        data.put("firstname", "jack");
        data.put("lastname", "MAmaa");

        RestAssured.baseURI = BASE_URI;
        RestAssured.given().basePath("/booking/4")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .cookie("token", "fd771e317011e1f")
                .contentType(ContentType.JSON)
                .accept("application/json")

                .body(data)

                .when().patch()

                .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority=10,groups = "regression")
    void filterByValidName() {//booking?firstname=Jim&lastname=Brown
        RestAssured.baseURI = BASE_URI;
        RestAssured.given()
                .pathParam("mypath","booking")    // path parameters
                .queryParam("firstname","Jim")
                .queryParam("lastname","Brown")
                .basePath("/{mypath}")// query parameter
                .auth().basic("admin", "password123")

                .when().get()

                .then()
                .log().all()
                .statusCode(200);
                //.body("bookingid", equalTo(4));


    }


}
