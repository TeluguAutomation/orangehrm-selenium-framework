package com.OrangeHRM.UI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.OrangeHRM.UI.basePage.BasePage;

/**
 * LogoutPage - Page Object Model for OrangeHRM Logout functionality only
 *
 * OOP CONCEPTS USED:
 * 1. INHERITANCE - Extends BasePage to inherit common functionality
 * 2. ENCAPSULATION - Private locators with public methods
 * 3. PAGE OBJECT MODEL - Represents a web page as a class
 * 4. COMPOSITION - Uses CommonMethods for element interactions
 * 5. ABSTRACTION - Hides implementation details from test classes
 *
 * HOW IT WORKS:
 * - Handles user profile dropdown and logout functionality only
 * - Focused on logout process: click dropdown → click logout
 * - Encapsulates logout-specific logic and locators
 * - Inherits WebDriver, WebDriverWait, and CommonMethods from BasePage
 *
 * DESIGN PATTERNS:
 * - Page Object Model (POM) - Each page represented as a class
 * - Factory Pattern - PageFactory for element initialization
 * - Template Method - Common structure for all page objects
 *
 * USAGE:
 * LogoutPage logoutPage = new LogoutPage(driver);
 * logoutPage.logout();
 * boolean isLoggedOut = logoutPage.isLogoutSuccessful();
 */
public class LogoutPage extends BasePage {

    // ==================== USER PROFILE DROPDOWN ELEMENTS ====================
    
    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement userProfileDropdown;
    
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    /**
     * Constructor - Initializes LogoutPage with WebDriver
     * OOP CONCEPT: Constructor - Initializes object state
     *
     * @param driver WebDriver instance
     */
    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    // ==================== PROFILE DROPDOWN METHODS ====================

    /**
     * Click user profile dropdown to open the menu
     * OOP CONCEPT: Encapsulation - Public method for external interaction
     *
     * HOW IT WORKS:
     * - Clicks on the user profile dropdown to reveal menu options
     * - Waits for element to be clickable before clicking
     * - Highlights element for visual feedback
     */
    public void clickUserProfileDropdown() {
        System.out.println("Clicking user profile dropdown...");
        commonMethods.clickElement(userProfileDropdown);
        System.out.println("✓ User profile dropdown clicked");
    }

    /**
     * Verify user profile dropdown is visible
     * OOP CONCEPT: Encapsulation - Public method for external validation
     *
     * @return true if user profile dropdown is visible
     */
    public boolean isUserProfileDropdownVisible() {
        try {
            return userProfileDropdown.isDisplayed();
        } catch (Exception e) {
            System.out.println("User profile dropdown not visible: " + e.getMessage());
            return false;
        }
    }

    // ==================== LOGOUT METHODS ====================

    /**
     * Click Logout link in profile dropdown
     * OOP CONCEPT: Encapsulation - Public method for external interaction
     */
    public void clickLogout() {
        System.out.println("Clicking Logout link...");
        commonMethods.clickElement(logoutLink);
        System.out.println("✓ Logout link clicked");
    }

    /**
     * Complete logout process
     * OOP CONCEPT: Abstraction - Hides implementation details
     *
     * HOW IT WORKS:
     * 1. Click user profile dropdown to open menu
     * 2. Click logout link to log out
     * 3. Wait for logout to complete
     */
    public void logout() {
        System.out.println("=== Starting Logout Process ===");
        
        // Step 1: Click user profile dropdown
        clickUserProfileDropdown();
        
        // Step 2: Click logout link
        clickLogout();
        
        System.out.println("=== Logout Process Complete ===");
    }

    /**
     * Verify logout was successful by checking if we're back to login page
     * OOP CONCEPT: Encapsulation - Public method for external validation
     *
     * @return true if logout was successful
     */
    public boolean isLogoutSuccessful() {
        try {
            // Check if current URL contains login
            String currentURL = driver.getCurrentUrl();
            boolean isLoginPage = currentURL.contains("login");
            
            if (isLoginPage) {
                System.out.println("✓ Logout successful - redirected to login page");
            } else {
                System.out.println("✗ Logout failed - still on: " + currentURL);
            }
            
            return isLoginPage;
        } catch (Exception e) {
            System.out.println("Error checking logout: " + e.getMessage());
            return false;
        }
    }


    // ==================== GENERAL METHODS ====================

    /**
     * Get current page title
     * OOP CONCEPT: Encapsulation - Public method for external access
     *
     * @return Current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     * OOP CONCEPT: Encapsulation - Public method for external access
     *
     * @return Current URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Check if page title contains specific text
     * OOP CONCEPT: Encapsulation - Public method for external validation
     *
     * @param expectedText Text to check in page title
     * @return true if page title contains expected text
     */
    public boolean isPageTitleContains(String expectedText) {
        String pageTitle = getPageTitle();
        return pageTitle.contains(expectedText);
    }

    /**
     * Check if current URL contains specific text
     * OOP CONCEPT: Encapsulation - Public method for external validation
     *
     * @param expectedText Text to check in URL
     * @return true if URL contains expected text
     */
    public boolean isURLContains(String expectedText) {
        String currentURL = getCurrentURL();
        return currentURL.contains(expectedText);
    }

}
