package com.OrangeHRM.UITest.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.OrangeHRM.UITest.testBase.BaseTest;
import com.OrangeHRM.UI.config.TestConfig;

/**
 * LogoutTest - Test cases for logout functionality only
 * 
 * Demonstrates how to use LogoutPage from BaseTest:
 * - logoutPage is already available from BaseTest
 * - Tests profile dropdown visibility and clicking
 * - Tests logout process and verification
 * - Focused only on logout functionality (no About, Support, etc.)
 * 
 * OOP CONCEPTS USED:
 * 1. INHERITANCE - Extends BaseTest to inherit page objects
 * 2. ENCAPSULATION - Uses page objects to hide implementation details
 * 3. COMPOSITION - Uses LogoutPage for logout operations
 * 4. ABSTRACTION - Test focuses on business logic, not technical details
 */
public class LogoutTest extends BaseTest {

    /**
     * Test Method - Verify profile dropdown is visible
     */
    @Test
    public void testProfileDropdownVisibility() {
        System.out.println("=== Test Method: testProfileDropdownVisibility ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Test profile dropdown visibility
        Assert.assertTrue(logoutPage.isUserProfileDropdownVisible(), 
            "User profile dropdown should be visible");
        
        System.out.println("✓ Profile dropdown visibility test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test profile dropdown clicking
     */
    @Test
    public void testProfileDropdownClick() {
        System.out.println("=== Test Method: testProfileDropdownClick ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Test clicking profile dropdown
        logoutPage.clickUserProfileDropdown();
        System.out.println("✓ Profile dropdown clicked successfully");
        
        System.out.println("✓ Profile dropdown click test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test complete logout functionality
     */
    @Test
    public void testLogoutFunctionality() {
        System.out.println("=== Test Method: testLogoutFunctionality ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Test complete logout process
        logoutPage.logout();
        
        // Verify logout success
        Assert.assertTrue(logoutPage.isLogoutSuccessful(), 
            "Logout should be successful");
        
        // Verify we're back to login page
        Assert.assertTrue(logoutPage.isURLContains("login"), 
            "Should be redirected to login page after logout");
        
        System.out.println("✓ Logout functionality test passed!");
        System.out.println("Current URL: " + logoutPage.getCurrentURL());
        System.out.println("=== Test Method Complete ===\n");
    }
    
    /**
     * Test Method - Test logout step by step
     */
    @Test
    public void testLogoutStepByStep() {
        System.out.println("=== Test Method: testLogoutStepByStep ===");
        
        // Login first
        loginPage.login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
        
        // Verify login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Step 1: Click user profile dropdown
        logoutPage.clickUserProfileDropdown();
        System.out.println("✓ Step 1: Profile dropdown clicked");
        
        // Step 2: Click logout link
        logoutPage.clickLogout();
        System.out.println("✓ Step 2: Logout link clicked");
        
        // Step 3: Verify logout success
        Assert.assertTrue(logoutPage.isLogoutSuccessful(), 
            "Logout should be successful");
        
        System.out.println("✓ Logout step by step test passed!");
        System.out.println("=== Test Method Complete ===\n");
    }

}
