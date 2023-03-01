package Tests.TestNG.RestFullTestNgGrouping.DataDrivenTest.DEMO2;

import io.restassured.RestAssured;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import static org.hamcrest.Matchers.equalTo;

public class DataDrivenTesting {
	String BASE_URI = "https://restful-booker.herokuapp.com";
	@Test(dataProvider="dp")
	void filterByID(String Booking, String bookingID)
	{
		RestAssured.baseURI = BASE_URI;
		RestAssured.given()
				.pathParam("mypath",Booking)
				.pathParam("bookingId",bookingID)
				.basePath("{mypath}/{bookingId}")
				//.basePath("/bookingInfo/bookingID")
				.headers("content-Type", "application/json")
				.when().get()
				.then()
				.log().all().statusCode(200);
	}

	@DataProvider(name="dp",parallel = true)//to run parallel we used parallel=true
	String [][] BookingDetails()
	{
		String data[][]= {  
							{ "booking", "321" },
							{ "booking", "33" },
							{ "booking", "238" },
							{ "booking", "186" },
							{ "booking", "543" }
						};return data;}}


	
	
	

