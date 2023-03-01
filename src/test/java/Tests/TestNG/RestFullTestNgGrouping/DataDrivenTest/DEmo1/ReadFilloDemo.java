package Tests.TestNG.RestFullTestNgGrouping.DataDrivenTest.DEmo1;

import org.testng.annotations.Test;


public class ReadFilloDemo {

    String BASE_URI = "https://restful-booker.herokuapp.com";
        @Test(dataProvider="getDataFillo",dataProviderClass = DataProvideFillo.class)

            public void filterByID(String[] Booking, String[] bookingID) {
           for (String i : Booking) {
                System.out.println(i);
            }
           for (String j : bookingID) {
                System.out.println(j);
            }

        }}

