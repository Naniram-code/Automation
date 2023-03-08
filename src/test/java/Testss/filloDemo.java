package Testss;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class filloDemo {

    public static void main(String[] args) {
                    String fileName = System.getProperty("user.dir") + "/src/main/java/resourse/TestData2.xlsx";
                    try (FileInputStream inputStream = new FileInputStream(fileName)) {
                        Workbook workbook = new XSSFWorkbook(inputStream);
                        Sheet sheet = workbook.getSheetAt(0);
                        for (Row row : sheet) {
                            for (Cell cell : row) {
                                System.out.print(cell.toString() + "\t");
                            }
                            System.out.println();
                        }
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



