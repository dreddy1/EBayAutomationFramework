package supportlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    List testData, cellData;

    /**
     * this method retrieve test data from given file
     * 
     * @param fileName
     *            - name of the test data file
     */
    public List getTestData(String fileName) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet excelSheet = workbook.getSheetAt(0);
            testData = new ArrayList();
            // Iterate through the sheet rows and cells.
            // Store the retrieved data in an arrayList
            Iterator rows = excelSheet.rowIterator();
            while (rows.hasNext()) {
                Row row = (Row) rows.next();
                Iterator cells = row.cellIterator();

                cellData = new ArrayList();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    cellData.add(cell);
                }

                testData.add(cellData);
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return testData;
    }
}