package endpoints;


import utils.DataUtils;

import utils.PropertyReaderOptimized;

public class APIConstants {

//public  final static String BASE_URL  = DataUtils.fetchDataFromXlsx("Sheet1","baseurl","value");
    //public static String BASE_URL  = "https://restful-booker.herokuapp.com";
    //public static String BASE_URL= PropertyReaderOptimized.readKey("url","data.properties");
    public static String BASE_URL;//Class PropertyReaderOptimized use for reading key and value

    static {//Static block coz its should run first
        try {
            BASE_URL = PropertyReaderOptimized.readKey("url", "data.properties");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String UserName;//Class PropertyReaderOptimized use for reading key and value

    static {//Static block coz its should run first
        try {
            UserName = PropertyReaderOptimized.readKey("username","data.properties");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }}
        public static String Password;

        static {
            try {
                Password = PropertyReaderOptimized.readKey("password", "data.properties");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }}


            public static String CREATE_BOOKING = "/booking";
            public static String UPDATE_BOOKING = "/booking";


        }

