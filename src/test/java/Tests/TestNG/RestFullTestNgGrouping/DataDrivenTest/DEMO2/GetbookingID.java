package Tests.TestNG.RestFullTestNgGrouping.DataDrivenTest.DEMO2;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GetbookingID {
    @Test
public void testCreateBooking() throws FilloException, FilloException {
    Fillo fillo = new Fillo();

    String filePath = System.getProperty("user.dir") + "/src/test/resources/dataDDD.xlsx";
    Connection connection = fillo.getConnection(filePath);

    String query = "SELECT * FROM Sheet1";//Define the SQL query to retrieve data from the Excel file:
    Recordset recordset = connection.executeQuery(query);//Execute the query and retrieve the data as a Recordset:

    while (recordset.next()) {//Loop through the Recordset and process the data:
        String ID = recordset.getField("BookingID");
        System.out.println(ID);

        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking"+"/"+ID)
                .cookie("token", "fd771e317011e1f")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType("application/json")
                .get();

        response.then().statusCode(200)
                .log().all();
    }

    recordset.close();
    connection.close();
}
}

