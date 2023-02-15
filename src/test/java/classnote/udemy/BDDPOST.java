package classnote.udemy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BDDPOST {

    public static void main(String[] args) {

        // Payload

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";


        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth").contentType(ContentType.JSON)
                .body(payload)
                .when().post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

        // Verification of Token ?


    }
}
