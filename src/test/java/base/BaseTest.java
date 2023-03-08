package base;


import Modules.PayloadManager;
import actions.AssertActions;
import endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;


    /* Demo concept type casting
    RequestSpecification requestSpec = given()
            .header("Content-Type", "application/json")
            .queryParam("page", "1")
            .body("{ \"name\": \"John Doe\", \"age\": 30 }");


    RequestSpecBuilder requestBuilder = new RequestSpecBuilder()
        .setBaseUri("https://reqres.in")
        .setBasePath("/api/users")
        .addHeader("Content-Type", "application/json");

       RequestSpecification requestSpec = given().spec(requestBuilder.build());

            */


    @BeforeMethod
    public void setUpConfig() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = (RequestSpecification) new RequestSpecBuilder()//RequestSpecifiation class same as
                // RequestSpecfication now we doing type casting
                //used methods to set headers, query parameters, form parameters, request etc
                //
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }

    public String getToken() {
        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .when().post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");

    }
    @AfterMethod
    public void tearDown() {
        // Clean up resources
        // ...
    }

}
