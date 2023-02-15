package RestAssuredProject.AssignmentREstAssured;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class Patchrequest {
    public static void main(String[] args) {
        String BASE_URI = "https://restful-booker.herokuapp.com";
        Booking booking = new Booking();
        booking.setFirstname("Suzana");
        Gson gson=new Gson();
        String jsonObj=gson.toJson(booking);

        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;
        requestSpecification.baseUri(BASE_URI)

                .basePath("/booking/3739")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .cookie("token", "fd771e317011e1f")
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(jsonObj);

        Response response=requestSpecification.patch();
        validatableResponse = response.then()

                .assertThat()
                .statusCode(200)
                .body("firstname",Matchers.equalTo("Suzana"))
                .log().all();

        JsonPath extractor=response.jsonPath();//JsonPath assertion
        extractor.get("firstname").toString().equals("Suzana");






    }
}