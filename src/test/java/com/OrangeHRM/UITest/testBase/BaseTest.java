package com.OrangeHRM.UITest.testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.OrangeHRM.UI.config.TestConfig;
import com.OrangeHRM.UI.pageObjects.LoginPage;
import com.OrangeHRM.UI.pageObjects.DashboardPage;
import com.OrangeHRM.UI.pageObjects.LogoutPage;

/**
 * BaseTest - Base class for all test cases
 * 
 * TestNG Lifecycle Flow:
 * Test Suite Start
 *     ↓
 * @BeforeSuite (loadConfigurationProperties) - Load config once
 *     ↓
 * @BeforeMethod (setUp) - Fresh browser per test
 *     ↓
 * Test Method 1
 *     ↓
 * @AfterMethod (tearDown) - Close browser
 *     ↓
 * @BeforeMethod (setUp) - Fresh browser per test
 *     ↓
 * Test Method 2
 *     ↓
 * @AfterMethod (tearDown) - Close browser
 *     ↓
 * @AfterSuite (cleanUp) - Final cleanup
 *     ↓
 * Test Suite End
 */
public class BaseTest {

    protected WebDriver driver;
    
    // Page Object Fields - Available to all test classes
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected LogoutPage logoutPage;
    
    /**
     * @BeforeSuite - Load configuration properties once for entire test suite
     * Runs once before all tests in the suite
     */
    @BeforeSuite
    public void loadConfigurationProperties() {
        System.out.println("=== Test Suite Start ===");
        System.out.println("=== @BeforeSuite (loadConfigurationProperties) - Load config once ===");
        
        // Force loading of TestConfig class to initialize all properties
        System.out.println("Base URL: " + TestConfig.BASE_URL);
        System.out.println("Login URL: " + TestConfig.LOGIN_URL);
        System.out.println("Dashboard URL: " + TestConfig.DASHBOARD_URL);
        System.out.println("Browser: " + TestConfig.BROWSER);
        System.out.println("Headless Mode: " + TestConfig.HEADLESS);
        System.out.println("Explicit Wait: " + TestConfig.EXPLICIT_WAIT + " seconds");
        System.out.println("Implicit Wait: " + TestConfig.IMPLICIT_WAIT + " seconds");
        System.out.println("Page Load Timeout: " + TestConfig.PAGE_LOAD_TIMEOUT + " seconds");
        System.out.println("Maximize Window: " + TestConfig.MAXIMIZE_WINDOW);
        System.out.println("Default Username: " + TestConfig.DEFAULT_USERNAME);
        System.out.println("Screenshot on Failure: " + TestConfig.SCREENSHOT_ON_FAILURE);
        System.out.println("Screenshot Path: " + TestConfig.SCREENSHOT_PATH);
        System.out.println("Report Path: " + TestConfig.REPORT_PATH);
        
        // Validate critical properties
        validateConfigurationProperties();
        
        System.out.println("=== Configuration loaded successfully! ===");
        System.out.println("=== @BeforeSuite Complete ===\n");
    }
    
    /**
     * @BeforeMethod - Initialize driver, clear cookies, open URL, maximize
     * Runs before each test method
     * 
     * @param browser Browser name (chrome, firefox, edge, safari)
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("") String browser) {
        System.out.println("=== @BeforeMethod (setUp) - Fresh browser per test ===");
        
        // Initialize WebDriver - Priority: TestNG XML parameter > config.properties
        String browserName;
        if (browser != null && !browser.isEmpty()) {
            browserName = browser;
            System.out.println("Using browser from TestNG XML: " + browserName);
        } else {
            browserName = TestConfig.BROWSER;
            System.out.println("Using browser from config.properties: " + browserName);
        }
        
        initializeDriver(browserName);
        
        // Clear all cookies
        driver.manage().deleteAllCookies();
        
        // Open URL
        driver.get(TestConfig.BASE_URL);
        
        // Maximize window
        driver.manage().window().maximize();
        
        
        // Configure browser timeouts
        configureBrowserTimeouts();
        
        // Initialize Page Objects - Available to all test classes
        initializePageObjects();

        System.out.println("Final Browser: " + browserName);
        System.out.println("URL: " + TestConfig.BASE_URL);
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("=== @BeforeMethod Complete ===\n");
    }
    
    /**
     * @AfterMethod - Close browser
     * Runs after each test method
     */
    @AfterMethod
    public void tearDown() {
        System.out.println("=== @AfterMethod (tearDown) - Close browser ===");
        
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            driver = null;
        }
        
        System.out.println("=== @AfterMethod Complete ===\n");
    }
    
    /**
     * @AfterSuite - Clean data and quit driver
     * Runs once after all tests in the suite
     */
    @AfterSuite
    public void cleanUp() {
        System.out.println("=== @AfterSuite (cleanUp) - Final cleanup ===");
        
        // Final cleanup - ensure driver is closed
        if (driver != null) {
            System.out.println("Final driver cleanup...");
            driver.quit();
            driver = null;
        }
        
        // Clean up any temporary files, reports, etc.
        System.out.println("Cleaning up test data...");
        System.out.println("Suite cleanup complete!");
        System.out.println("=== Test Suite End ===\n");
    }
    
    /**
     * Initialize Page Objects - Create instances for all test classes
     * OOP CONCEPT: Composition - BaseTest "has-a" relationship with page objects
     * 
     * HOW IT WORKS:
     * - Creates fresh page object instances for each test
     * - All test classes can access these objects directly
     * - No need to create page objects in individual test methods
     * - Centralized page object management
     */
    private void initializePageObjects() {
        System.out.println("Initializing Page Objects...");
        
        // Create page object instances
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        logoutPage = new LogoutPage(driver);
        
        System.out.println("✓ Page Objects initialized successfully!");
        System.out.println("- LoginPage: " + (loginPage != null ? "✓" : "✗"));
        System.out.println("- DashboardPage: " + (dashboardPage != null ? "✓" : "✗"));
        System.out.println("- LogoutPage: " + (logoutPage != null ? "✓" : "✗"));
    }

    /**
     * Initialize WebDriver based on browser type
     * Uses WebDriverManager for automatic driver management
     *
     * @param browserName Browser to initialize
     */
    private void initializeDriver(String browserName) {
        System.out.println("Initializing " + browserName + " driver...");
        
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        
        System.out.println(browserName + " driver initialized successfully!");
    }
    
    /**
     * Configure browser timeouts
     * Sets implicit wait, page load timeout, and script timeout
     */
    private void configureBrowserTimeouts() {
        driver.manage().timeouts().implicitlyWait(
            java.time.Duration.ofSeconds(TestConfig.IMPLICIT_WAIT)
        );
        driver.manage().timeouts().pageLoadTimeout(
            java.time.Duration.ofSeconds(TestConfig.PAGE_LOAD_TIMEOUT)
        );
       
        
        System.out.println("Browser timeouts configured:");
        System.out.println("- Implicit Wait: " + TestConfig.IMPLICIT_WAIT + " seconds");
        System.out.println("- Page Load Timeout: " + TestConfig.PAGE_LOAD_TIMEOUT + " seconds");
        
    }
    
    /**
     * Validate that all critical configuration properties are loaded
     * Throws exception if any critical property is missing or invalid
     */
    private void validateConfigurationProperties() {
        // Validate URLs
        if (TestConfig.BASE_URL == null || TestConfig.BASE_URL.isEmpty()) {
            throw new RuntimeException("Base URL is not configured!");
        }
        if (TestConfig.LOGIN_URL == null || TestConfig.LOGIN_URL.isEmpty()) {
            throw new RuntimeException("Login URL is not configured!");
        }
        if (TestConfig.DASHBOARD_URL == null || TestConfig.DASHBOARD_URL.isEmpty()) {
            throw new RuntimeException("Dashboard URL is not configured!");
        }
        
        // Validate browser
        if (TestConfig.BROWSER == null || TestConfig.BROWSER.isEmpty()) {
            throw new RuntimeException("Browser is not configured!");
        }
        
        // Validate timeouts
        if (TestConfig.EXPLICIT_WAIT <= 0) {
            throw new RuntimeException("Explicit wait timeout must be greater than 0!");
        }
        if (TestConfig.IMPLICIT_WAIT <= 0) {
            throw new RuntimeException("Implicit wait timeout must be greater than 0!");
        }
        if (TestConfig.PAGE_LOAD_TIMEOUT <= 0) {
            throw new RuntimeException("Page load timeout must be greater than 0!");
        }
        
        // Validate credentials
        if (TestConfig.DEFAULT_USERNAME == null || TestConfig.DEFAULT_USERNAME.isEmpty()) {
            throw new RuntimeException("Default username is not configured!");
        }
        if (TestConfig.DEFAULT_PASSWORD == null || TestConfig.DEFAULT_PASSWORD.isEmpty()) {
            throw new RuntimeException("Default password is not configured!");
        }
        
        System.out.println("✓ All configuration properties validated successfully!");
    }
}