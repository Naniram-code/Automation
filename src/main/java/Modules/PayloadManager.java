package Modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import payloads.Booking;
import payloads.BookingDate;

public class PayloadManager {

    // JAVA -> JSON
    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Nani Ram");
        booking.setLastname("Bhujel");
        booking.setTotalprice(2343);
        booking.setDepositpaid(true);

        BookingDate bookingDate=new BookingDate();
        bookingDate.setCheckin("2023-02-15");
        bookingDate.setCheckout("2023-02-25");

        booking.setBookingdates(bookingDate);
        booking.setAdditionalneeds("lunch and dinner");

        Gson gson=new Gson();
        String jsonObj=gson.toJson(booking);
       /* ObjectMapper objMapper=new ObjectMapper();
        String jsonObj=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        System.out.println(jsonObj);*/
        //String jsonObj = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return jsonObj;
    }

    public String updatedPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname("RamKumar");
        booking.setLastname("Modhi");
        booking.setTotalprice(2343);
        booking.setDepositpaid(true);

        BookingDate bookingDate=new BookingDate();
        bookingDate.setCheckin("2023-02-15");
        bookingDate.setCheckout("2023-02-25");

        booking.setBookingdates(bookingDate);
        booking.setAdditionalneeds("lunch and dinner");

        Gson gson=new Gson();
        String jsonObj=gson.toJson(booking);
        //String jsonObj = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return jsonObj;
    }

    public String updatePayload(){
        return null;
    };


}
