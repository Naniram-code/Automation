package RestAssuredProject.AssignmentREstAssured.xyz;

import RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1.Booking;
import RestAssuredProject.AssignmentREstAssured.ASSIGNMENT1.BookingDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.testng.annotations.Test;

public class c {
@Test
    public  void dddd(){
        //public static void main(String[] args) throws JsonProcessingException {

            String BASE_URI = "https://restful-booker.herokuapp.com";


            Booking booking = new Booking();
            booking.setFirstname("Nani Ram");
            booking.setLastname("Bhujel");
            booking.setTotalprice(2343);
            booking.setDepositpaid(true);

            BookingDate Date=new BookingDate();
            Date.setCheckin("2023-02-15");
            Date.setCheckout("2023-02-25");

            booking.setBookingdates(Date);
            booking.setAdditionalneeds("lunch and dinner");


            Gson gson=new Gson();
            String jsonObj=gson.toJson(booking);
            System.out.println(booking);
}}
