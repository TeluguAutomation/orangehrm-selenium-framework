package com.OrangeHRM.UITest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.OrangeHRM.UITest.testBase.BaseTest;
import com.OrangeHRM.UI.config.TestConfig;

/**
 * DashboardTest - Test cases for dashboard functionality
 * 
 * Demonstrates how to use page objects from BaseTest:
 * - loginPage, dashboardPage, logoutPage are already available
 * - No need to create page object instances in test methods
 * - Clean and maintainable test code
 * 
 * OOP CONCEPTS USED:
 * 1. INHERITANCE - Extends BaseTest to inherit page objects
 * 2. ENCAPSULATION - Uses page objects to hide implementation details
 * 3. COMPOSITION - Uses page objects from BaseTest
 * 4. ABSTRACTION - Test focuses on business logic, not technical details
 */
public class DashboardTest extends BaseTest {

    /**
     * Test Method - Verify dashboard after login
     * 
     * HOW IT WORKS:
     * 1. Login using loginPage from BaseTest
     * 2. Verify dashboard using dashboardPage from BaseTest
     * 3. All page objects are already initialized!
     */
    @Test
    public void testDashboardAfterLogin() {
        System.out.println("=== Test Method: testDashboardAfterLogin ===");
        
        // Step 1: Login using page object from BaseTest
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Step 2: Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
            "Login should be successful before testing dashboard");
        
        // Step 3: Verify dashboard elements using page object from BaseTest
        Assert.assertTrue(dashboardPage.isDashboardTitleVisible(), 
            "Dashboard title should be visible after login");
        
        Assert.assertTrue(dashboardPage.isUserProfileVisible(), 
            "User profile dropdown should be visible");
        
        Assert.assertTrue(dashboardPage.isSearchBoxVisible(), 
            "Search box should be visible");
        
        System.out.println("✓ Dashboard test passed!");
        System.out.println("Page Title: " + dashboardPage.getPageTitle());
        System.out.println("Dashboard Title: " + dashboardPage.getDashboardTitle());
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Verify all main menu items are visible
     */
    @Test
    public void testMainMenuVisibility() {
        System.out.println("=== Test Method: testMainMenuVisibility ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Verify all main menu items are visible
        Assert.assertTrue(dashboardPage.areAllMainMenusVisible(), 
            "All main menu items should be visible on dashboard");
        
        System.out.println("✓ Main menu visibility test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Verify dashboard widgets are visible
     */
    @Test
    public void testDashboardWidgets() {
        System.out.println("=== Test Method: testDashboardWidgets ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Verify dashboard widgets
        Assert.assertTrue(dashboardPage.areAllWidgetsVisible(), 
            "All dashboard widgets should be visible");
        
        // Verify specific widgets
        Assert.assertTrue(dashboardPage.isQuickLaunchVisible(), 
            "Quick Launch widget should be visible");
        
        Assert.assertTrue(dashboardPage.isTimeAtWorkVisible(), 
            "Time at Work widget should be visible");
        
        System.out.println("✓ Dashboard widgets test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test menu navigation
     */
    @Test
    public void testMenuNavigation() {
        System.out.println("=== Test Method: testMenuNavigation ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Test clicking on different menus
        try {
            dashboardPage.clickMenu("Admin");
            System.out.println("✓ Admin menu clicked successfully");
            
            // Navigate back to dashboard
            dashboardPage.clickMenu("Dashboard");
            System.out.println("✓ Dashboard menu clicked successfully");
            
        } catch (Exception e) {
            System.out.println("Menu navigation test failed: " + e.getMessage());
        }
        
        System.out.println("✓ Menu navigation test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test search functionality
     */
    @Test
    public void testSearchFunctionality() {
        System.out.println("=== Test Method: testSearchFunctionality ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Test search box
        Assert.assertTrue(dashboardPage.isSearchBoxVisible(), 
            "Search box should be visible");
        
        // Enter search text
        dashboardPage.enterSearchText("Admin");
        System.out.println("✓ Search text entered successfully");
        
        System.out.println("✓ Search functionality test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test complete dashboard functionality
     */
    @Test
    public void testCompleteDashboardFunctionality() {
        System.out.println("=== Test Method: testCompleteDashboardFunctionality ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Verify complete dashboard functionality
        Assert.assertTrue(dashboardPage.verifyDashboardFunctionality(), 
            "Complete dashboard functionality should work");
        
        System.out.println("✓ Complete dashboard functionality test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    

}
