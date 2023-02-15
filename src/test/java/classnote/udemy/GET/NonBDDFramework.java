package classnote.udemy.GET;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class NonBDDFramework {
    @Test

    void TokenCreate() {
  Map<String,String> payload =new HashMap<>();
       payload.put("username","admin");
       payload.put("password","password123");

        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload);
     // r.when().post();
      Response res=r.post();
        //r.post()
        //r.then().log().all();
        System.out.println(res.asPrettyString());
        //res.prettyPrint();

       res.then().log().all().statusCode(200);
        //System.out.println(res.then().log().all());
        //ResponseSpecification response  = r.then().statusCode(200);

    }
}
