package com.OrangeHRM.UI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.OrangeHRM.UI.basePage.BasePage;
import com.OrangeHRM.UI.config.TestConfig;

// Log4j2 imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * LoginPage - Page Object Model for OrangeHRM Login functionality
 * 
 * OOP CONCEPTS USED:
 * 1. INHERITANCE - Extends BasePage to inherit common functionality
 * 2. ENCAPSULATION - Private locators with public methods
 * 3. PAGE OBJECT MODEL - Represents a web page as a class
 * 4. COMPOSITION - Uses CommonMethods for element interactions
 * 5. ABSTRACTION - Hides implementation details from test classes
 * 
 * HOW IT WORKS:
 * - Uses @FindBy annotations for element location
 * - PageFactory.initElements() automatically initializes elements
 * - Provides business methods that test classes can call
 * - Encapsulates page-specific logic and locators
 * - Inherits WebDriver, WebDriverWait, and CommonMethods from BasePage
 * 
 * DESIGN PATTERNS:
 * - Page Object Model (POM) - Each page represented as a class
 * - Factory Pattern - PageFactory for element initialization
 * - Template Method - Common structure for all page objects
 * 
 * USAGE:
 * LoginPage loginPage = new LoginPage(driver);
 * loginPage.login("admin", "password");
 * boolean isLoggedIn = loginPage.isLoginSuccessful();
 */
public class LoginPage extends BasePage {

    // Logger instance
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    // Page Locators - Using @FindBy annotations
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='oxd-alert-content oxd-alert-content--error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardTitle;

    /**
     * Constructor - Initializes LoginPage with WebDriver
     * OOP CONCEPT: Constructor - Initializes object state
     * 
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Enter username in username field
     * OOP CONCEPT: Encapsulation - Private implementation, public interface
     * 
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        logger.debug("Entering username: {}", username);
        commonMethods.enterText(usernameField, username);
    }

    /**
     * Enter password in password field
     * OOP CONCEPT: Encapsulation - Private implementation, public interface
     * 
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        logger.debug("Entering password");
        commonMethods.enterText(passwordField, password);
    }

    /**
     * Click login button
     * OOP CONCEPT: Encapsulation - Private implementation, public interface
     * 
     * HOW IT WORKS:
     * - Waits for button to be clickable
     * - Highlights element for visual feedback
     * - Clicks the login button
     */
    public void clickLoginButton() {
        logger.debug("Clicking login button");
        commonMethods.clickElement(loginButton);
    }

    /**
     * Complete login process with valid credentials
     * OOP CONCEPT: Abstraction - Hides complex implementation details
     * 
     * HOW IT WORKS:
     * 1. Enter username and password
     * 2. Click login button
     * 3. Wait for page to load
     * 
     * Note: Navigation is handled by BaseTest @BeforeMethod
     * 
     * @param username Valid username
     * @param password Valid password
     */
    public void login(String username, String password) {
        logger.info("=== Starting Login Process ===");
        logger.info("Username: {}", username);
        logger.debug("Password: *****"); // Don't log actual password
        
        // Enter credentials
        enterUsername(username);
        enterPassword(password);
        
        // Click login button
        clickLoginButton();
        
        // Wait for dashboard to load (indicates successful login)
        try {
            commonMethods.waitForElementToBeVisible(dashboardTitle);
            logger.info("✓ Login successful - Dashboard loaded");
        } catch (Exception e) {
            // If dashboard doesn't appear, login might have failed
            logger.error("Dashboard not found after login attempt: {}", e.getMessage());
        }
        
        logger.info("=== Login Process Complete ===");
    }

    /**
     * Login with default credentials from configuration
     * OOP CONCEPT: Method overloading - Multiple ways to perform login
     * 
     * HOW IT WORKS:
     * - Uses default username and password from TestConfig
     * - Calls the main login method
     */
    public void login() {
        login(TestConfig.DEFAULT_USERNAME, TestConfig.DEFAULT_PASSWORD);
    }

    /**
     * Check if login was successful by verifying dashboard title
     * OOP CONCEPT: Encapsulation - Public method for external validation
     * 
     * HOW IT WORKS:
     * - Waits for dashboard title to be visible
     * - Returns true if dashboard is displayed
     * 
     * @return true if login successful, false otherwise
     */
    public boolean isLoginSuccessful() {
        try {
            commonMethods.waitForElementToBeVisible(dashboardTitle);
            return dashboardTitle.isDisplayed();
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if error message is displayed
     * OOP CONCEPT: Encapsulation - Public method for external validation
     * 
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get error message text
     * OOP CONCEPT: Encapsulation - Public method for external access
     * 
     * @return Error message text if displayed, empty string otherwise
     */
    public String getErrorMessage() {
        try {
            if (isErrorMessageDisplayed()) {
                return errorMessage.getText();
            }
        } catch (Exception e) {
            System.out.println("Error getting error message: " + e.getMessage());
        }
        return "";
    }

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

    /**
     * Verify successful login by checking dashboard title
     * OOP CONCEPT: Abstraction - Hides implementation details
     * 
     * @return true if dashboard title is visible
     */
    public boolean verifyLoginSuccess() {
        try {
            commonMethods.waitForElementToBeVisible(dashboardTitle);
            return dashboardTitle.isDisplayed();
        } catch (Exception e) {
            System.out.println("Dashboard not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify login failure by checking error message
     * OOP CONCEPT: Abstraction - Hides implementation details
     * 
     * @return true if error message is displayed
     */
    public boolean verifyLoginFailure() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
