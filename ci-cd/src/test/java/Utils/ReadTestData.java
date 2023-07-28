package Utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

// ...
public class ReadTestData{
	String filePath = "C:\\Users\\91914\\ci-cd workspace\\ci-cd\\src\\test\\resources\\testData.xlsx";
    String sheetName = "loginData";
// Utility method to read test data from Excel file
private String readTestData(String filePath, String sheetName, int row, int column) throws IOException {
    FileInputStream fis = new FileInputStream(filePath);
    Workbook workbook = new XSSFWorkbook(fis);
    Sheet sheet = workbook.getSheet(sheetName);
    Row dataRow = sheet.getRow(row);
    Cell cell = dataRow.getCell(column);
    String data = cell.getStringCellValue();
    workbook.close();
    fis.close();
    return data;
}
}