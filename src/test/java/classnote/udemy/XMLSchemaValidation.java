package classnote.udemy;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class XMLSchemaValidation {

	@Test
	void xmlSchemavalidation()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		.then()
		   .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));	
		
	}
	
}
