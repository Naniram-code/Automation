package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.DEFAULT_PORT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
public class jsonResponse{
    //Global variable
 String BASE_URI ="http://localhost:3000";//local Host URL
    @Test(priority =1)
    public void JasonResponseAssertion() {

        RestAssured.baseURI = BASE_URI;
        Response response= RestAssured.given()
                .basePath("/store").contentType(ContentType.JSON)
                .when().get();// extract  all the Body response of json in (response) variable.

        System.out.println(response.prettyPrint());//print all Body response in json pretty format(prettyPrint())

        //response.jsonPath().get("book[3].title").toString();//object value to string value toString() M
        Assert.assertEquals(response.jsonPath().get("book[0].author").toString(),"Nigel Rees");
        Assert.assertEquals(response.jsonPath().get("book[3].title").toString(),"The Lord of the Rings");
        Assert.assertEquals(response.jsonPath().get("book[1].price").toString(),"12.99");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }
    @Test(priority =2)
public void JasonResponseV() {

        RestAssured.baseURI = BASE_URI;
        //Response response = RestAssured.get();
        RestAssured.given().basePath("/store")
        .contentType(ContentType.JSON)

                .when().get()

                .then()
                .statusCode(200)
                .header("Content-Type",equalTo("application/json; charset=utf-8"))
                .body("book[3].title",equalTo("The Lord of the Rings"))
                .body("book[0].price.toString()",equalTo("8.95"))
                .log().all();
        //Assert.assertEquals(response.getStatusCode(),200);
}
     @Test(priority =3)
    public void JasonResponseArray() {

        RestAssured.baseURI = BASE_URI;
        Response response= RestAssured.given()
                .basePath("/store").contentType(ContentType.JSON)
                .when().get();// extract  all the Body response of json in (response) variable.
        //using JSONObject class
        JSONObject jo=new JSONObject(response.asString()); //converting response to JSON Object

        //print all titles of books
		for(int i=0; i<jo.getJSONArray("book").length();i++)
		{
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
         Double totalPrice=0.0;
         for(int i=0; i<jo.getJSONArray("book").length();i++)
         {
             String currentprice=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
             totalPrice=totalPrice+Double.parseDouble(currentprice);
             }
         System.out.println("Total price All Books="+totalPrice);
         Assert.assertEquals(totalPrice,53.92);
     }}
