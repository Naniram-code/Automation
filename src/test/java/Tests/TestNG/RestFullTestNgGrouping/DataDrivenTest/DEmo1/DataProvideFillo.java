package Tests.TestNG.RestFullTestNgGrouping.DataDrivenTest.DEmo1;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import java.util.ArrayList;
import java.util.Iterator;

public class DataProvideFillo {
    @DataProvider
    public Iterator<Object[]> getDataFillo() throws FilloException {
            ArrayList<Object[]> data = readCSV();
            return data.iterator();
        }

        public ArrayList<Object[]> readCSV() throws FilloException {
            try {
                Fillo fillo = new Fillo();
                ArrayList<Object[]> arrayList = null;
                Object[]objectBooking = null;
                Object[]objectbookingID = null;
                String filePath = System.getProperty("user.dir") + "/src/test/resources/dataDDD.xlsx";
                Connection connection = fillo.getConnection(filePath);
                Recordset recordset = connection.executeQuery("SELECT * FROM Sheet1");
                int numberOfRows = recordset.getCount();
                System.out.println("Size: " + numberOfRows);
                int i = 0;
                int j = 0;
                objectBooking = new String[numberOfRows];
                objectbookingID = new String[numberOfRows];
                while (recordset.next()) {
                    objectBooking[i] = recordset.getField("Booking");
                    objectbookingID[j] = recordset.getField("bookingID");
            //System.out.println(objectBooking[i]);
            // System.out.println(objectbookingID[j]);
                    i++;
                    j++;
                }
                recordset.close();
                connection.close();
                arrayList = new ArrayList<Object[]>();
                Object[] o = {
                        objectBooking, objectbookingID };
                arrayList.add(o);
                return arrayList;
            } catch (FilloException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }}





