package classnote.udemy;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//json ---> jsonschema converter
//  https://jsonformatter.org/json-to-jsonschema
	
	


public class JSONSchemaValidation {

	@Test
	void jsonschemavalidation()
	{
		given()

		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

	}


}

