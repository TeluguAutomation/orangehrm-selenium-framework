package com.OrangeHRM.UITest.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.OrangeHRM.UITest.testBase.BaseTest;
import com.OrangeHRM.UI.pageObjects.LoginPage;
import com.OrangeHRM.UI.config.TestConfig;

/**
 * LoginTest - Test cases for login functionality
 * 
 * Demonstrates the TestNG lifecycle flow:
 * @BeforeSuite → @BeforeMethod → Test Method → @AfterMethod → @AfterSuite
 * 
 * OOP CONCEPTS USED:
 * 1. INHERITANCE - Extends BaseTest to inherit setup/teardown
 * 2. ENCAPSULATION - Uses page objects to hide implementation details
 * 3. COMPOSITION - Uses LoginPage for login operations
 * 4. ABSTRACTION - Test focuses on business logic, not technical details
 * 
 * HOW IT WORKS:
 * - Each test method runs independently
 * - Fresh browser instance for each test
 * - Uses Page Object Model for maintainable code
 * - Validates login success/failure scenarios
 */
public class LoginTest extends BaseTest {

    // Global SoftAssert instance for this test class
    private SoftAssert softAssert;

    /**
     * @BeforeMethod - Initialize SoftAssert for each test method
     * This runs before each test method in this class
     */
    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
        System.out.println("✓ SoftAssert initialized for test method");
    }

    /**
     * Test Method - Valid login with config data
     * 
     * HOW IT WORKS:
     * 1. Get test data from config file
     * 2. Use LoginPage instance from BaseTest (already initialized)
     * 3. Pass test data to page object methods
     * 4. Verify login success
     * 
     * TEST DATA MANAGEMENT:
     * - Test data from TestConfig (config.properties)
     * - Page objects receive data as parameters
     * - Simple and clean approach
     * 
     * EXTENT REPORTS:
     * - Automatically handled by ExtentReportManager listener
     * - No need for manual logging in test methods
     */
    @Test
    public void testValidLogin() {
        System.out.println("=== Test Method: testValidLogin ===");
        
        // Test Data - From config file
        String username = TestConfig.DEFAULT_USERNAME;
        String password = TestConfig.DEFAULT_PASSWORD;
        
        // Use page object from BaseTest - No need to create new instance!
        // loginPage is already initialized in BaseTest @BeforeMethod
        loginPage.login(username, password);
        
        // Soft Assertions - Continue execution even if one fails
        // Using global softAssert instance (no need to create new one)
        softAssert.assertTrue(loginPage.isLoginSuccessful(), 
            "Login should be successful with valid credentials");
        
        softAssert.assertTrue(loginPage.isPageTitleContains("OrangeHRM"), 
            "Page title should contain 'OrangeHRM' after successful login");
        
        // Verify all soft assertions at the end
        softAssert.assertAll();
        
        System.out.println("✓ Valid login test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }

}
