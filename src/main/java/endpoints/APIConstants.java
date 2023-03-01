package endpoints;


import utils.PropertyReaderOptimized;

public class APIConstants {

//    public static String BASE_URL  = FillowUtils.fetchDataFromXlsx("Sheet1","baseurl","value");
    //public static String BASE_URL  = "https://restful-booker.herokuapp.com";
    public static String BASE_URL;

    static {
        try {
            BASE_URL = PropertyReaderOptimized.readKey("url","data.properties");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String CREATE_BOOKING  = "/booking";
    public static String UPDATE_BOOKING  = "/booking";



}
