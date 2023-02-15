package classnote.udemy.GET;

import io.restassured.RestAssured;


public class BDDGETReq {

    public static void main(String[] args) {

        //Global vARIABLES
        String BASE_URI = "https://restful-booker.herokuapp.com";
        RestAssured.baseURI = BASE_URI;

        RestAssured.given().basePath("/booking").headers("Content-Type","application/json")
                .when().get()
                .then().log().all().statusCode(200);


    }
}
