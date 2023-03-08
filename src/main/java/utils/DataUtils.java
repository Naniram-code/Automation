package utils;


import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataUtils {

    private static Logger logger = LogManager.getLogger(DataUtils.class);


    static String FILEPATH = System.getProperty("user.dir") + "/src/main/java/resourse/TestData2.xlsx";

    public static String fetchDataFromXlsx(String sheetName, String id, String field) {
        String value = null;
        try {
            Fillo fillo = new Fillo();
            logger.info("File Path is " + FILEPATH);
            Connection connection = fillo.getConnection(FILEPATH);
            String query = "Select * from " + sheetName + " " + "where ID='" + id + "value";
            Recordset recordset = connection.executeQuery(query);
            while (recordset.next()) {
                value = recordset.getField(field);
            }

            recordset.close();
            connection.close();

        } catch (Exception e) {
            e.getMessage();
            logger.error("Not able to read File");
        }

        return value;
    }



}


