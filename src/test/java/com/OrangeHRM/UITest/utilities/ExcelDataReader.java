package com.OrangeHRM.UITest.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.OrangeHRM.UI.config.TestConfig;

/**
 * ExcelDataReader - Utility class for reading test data from Excel files
 * 
 * OOP CONCEPTS USED:
 * 1. ENCAPSULATION - Private methods with public data provider methods
 * 2. STATIC METHODS - Utility methods for data access
 * 3. GENERICS - Type-safe data handling
 * 4. EXCEPTION HANDLING - Robust error handling for file operations
 * 5. DATA PROVIDER PATTERN - TestNG data provider implementation
 * 
 * HOW IT WORKS:
 * - Reads test data from Excel files (test-data.xlsx)
 * - Supports filtering by test type (ValidLogin, InvalidLogin)
 * - Converts Excel data to Object[][] for TestNG data providers
 * - Handles different data types (String, Integer, Boolean)
 * - Provides fallback to hardcoded data if Excel file not found
 * 
 * DESIGN PATTERNS:
 * - Data Provider Pattern - TestNG data provider implementation
 * - Factory Pattern - Creates test data objects
 * - Template Method - Common data reading structure
 * 
 * USAGE:
 * @Test(dataProvider = "loginData")
 * public void testLogin(String username, String password, String expectedResult) {
 *     // Test implementation
 * }
 * 
 * @author TeluguAutomation
 * @version 1.0
 */
public class ExcelDataReader {
    
    private static final String TEST_DATA_FILE = "src/test/resources/test-data/test-data.xlsx";
    private static final String LOGIN_DATA_SHEET = "Login Data";
    
    /**
     * Data Provider for valid login test cases
     * Reads from Excel sheet "Login Data" and filters for ValidLogin
     * 
     * @return Object[][] - Array of test data [username, password, expectedResult]
     */
    @DataProvider(name = "validLoginData")
    public static Object[][] getValidLoginData() {
        try {
            return readExcelDataByType(TEST_DATA_FILE, LOGIN_DATA_SHEET, "ValidLogin");
        } catch (Exception e) {
            System.out.println("⚠️ Excel file not found, using hardcoded valid login data");
            return getHardcodedValidLoginData();
        }
    }
    
    /**
     * Data Provider for invalid login test cases
     * Reads from Excel sheet "Login Data" and filters for InvalidLogin
     * 
     * @return Object[][] - Array of test data [username, password, expectedResult]
     */
    @DataProvider(name = "invalidLoginData")
    public static Object[][] getInvalidLoginData() {
        try {
            return readExcelDataByType(TEST_DATA_FILE, LOGIN_DATA_SHEET, "InvalidLogin");
        } catch (Exception e) {
            System.out.println("⚠️ Excel file not found, using hardcoded invalid login data");
            return getHardcodedInvalidLoginData();
        }
    }
    
    
    /**
     * Read data from Excel file by test type
     * 
     * @param filePath - Path to Excel file
     * @param sheetName - Name of the sheet to read
     * @param testType - Type of test to filter (ValidLogin, InvalidLogin)
     * @return Object[][] - Array of test data
     * @throws IOException - If file cannot be read
     */
    private static Object[][] readExcelDataByType(String filePath, String sheetName, String testType) throws IOException {
        List<Object[]> dataList = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet '" + sheetName + "' not found in Excel file");
            }
            
            // Skip header row (row 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    // Check if this row matches the test type
                    Cell testTypeCell = row.getCell(0); // First column is testType
                    if (testTypeCell != null && testType.equals(getCellValue(testTypeCell).toString())) {
                        List<Object> rowData = new ArrayList<>();
                        
                        // Read specific columns based on test type
                        if ("ValidLogin".equals(testType) || "InvalidLogin".equals(testType)) {
                            // For login tests: username, password, expectedResult
                            rowData.add(getCellValue(row.getCell(1))); // username
                            rowData.add(getCellValue(row.getCell(2))); // password
                            rowData.add(getCellValue(row.getCell(3))); // expectedResult
                        } else {
                            // For user data: firstName, lastName, username, password, confirmPassword
                            rowData.add(getCellValue(row.getCell(4))); // firstName
                            rowData.add(getCellValue(row.getCell(5))); // lastName
                            rowData.add(getCellValue(row.getCell(1))); // username
                            rowData.add(getCellValue(row.getCell(2))); // password
                            rowData.add(getCellValue(row.getCell(2))); // confirmPassword (same as password)
                        }
                        
                        if (!rowData.isEmpty()) {
                            dataList.add(rowData.toArray());
                        }
                    }
                }
            }
        }
        
        return dataList.toArray(new Object[0][]);
    }

    /**
     * Read data from Excel file
     * 
     * @param filePath - Path to Excel file
     * @param sheetName - Name of the sheet to read
     * @return Object[][] - Array of test data
     * @throws IOException - If file cannot be read
     */
    private static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        List<Object[]> dataList = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet '" + sheetName + "' not found in Excel file");
            }
            
            // Skip header row (row 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    List<Object> rowData = new ArrayList<>();
                    
                    // Read all cells in the row
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        rowData.add(getCellValue(cell));
                    }
                    
                    if (!rowData.isEmpty()) {
                        dataList.add(rowData.toArray());
                    }
                }
            }
        }
        
        return dataList.toArray(new Object[0][]);
    }
    
    /**
     * Get cell value based on cell type
     * 
     * @param cell - Excel cell
     * @return Object - Cell value
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    /**
     * Hardcoded valid login data (fallback)
     * 
     * @return Object[][] - Hardcoded valid login test data
     */
    private static Object[][] getHardcodedValidLoginData() {
        return new Object[][] {
            {"Admin", "admin123", "Success"},
            {"admin", "admin123", "Success"},
            {"ADMIN", "admin123", "Success"}
        };
    }
    
    /**
     * Hardcoded invalid login data (fallback)
     * 
     * @return Object[][] - Hardcoded invalid login test data
     */
    private static Object[][] getHardcodedInvalidLoginData() {
        return new Object[][] {
            {"invaliduser", "invalidpass", "Invalid credentials"},
            {"", "admin123", "Required"},
            {"Admin", "", "Required"},
            {"", "", "Required"},
            {"Admin", "wrongpassword", "Invalid credentials"},
            {"testuser", "testpass", "Invalid credentials"},
            {"user123", "pass456", "Invalid credentials"},
            {"admin", "wrongpass", "Invalid credentials"},
            {"ADMIN", "wrongpass", "Invalid credentials"}
        };
    }
    
    /**
     * Hardcoded user data (fallback)
     * 
     * @return Object[][] - Hardcoded user test data
     */
    private static Object[][] getHardcodedUserData() {
        return new Object[][] {
            {"John", "Doe", "Admin", "admin123", "admin123"},
            {"Jane", "Smith", "janesmith", "password456", "password456"},
            {"Bob", "Johnson", "bobjohnson", "password789", "password789"}
        };
    }
    
    /**
     * Get test data as Map for flexible data access
     * 
     * @param sheetName - Name of the sheet
     * @return List<Map<String, Object>> - List of test data maps
     */
    public static List<Map<String, Object>> getTestDataAsMap(String sheetName) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        
        try {
            Object[][] data = readExcelData(TEST_DATA_FILE, sheetName);
            
            // Assume first row contains headers
            String[] headers = {"testType", "username", "password", "expectedResult", "firstName", "lastName", "email", "phone", "department", "jobTitle"};
            
            for (Object[] row : data) {
                Map<String, Object> dataMap = new HashMap<>();
                for (int i = 0; i < row.length && i < headers.length; i++) {
                    dataMap.put(headers[i], row[i]);
                }
                dataList.add(dataMap);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error reading Excel data: " + e.getMessage());
        }
        
        return dataList;
    }
    
    /**
     * Get specific test data by key
     * 
     * @param sheetName - Name of the sheet
     * @param key - Key to search for
     * @return Map<String, Object> - Test data map
     */
    public static Map<String, Object> getTestDataByKey(String sheetName, String key) {
        List<Map<String, Object>> allData = getTestDataAsMap(sheetName);
        
        for (Map<String, Object> data : allData) {
            if (data.containsKey("key") && key.equals(data.get("key"))) {
                return data;
            }
        }
        
        return new HashMap<>();
    }
}
