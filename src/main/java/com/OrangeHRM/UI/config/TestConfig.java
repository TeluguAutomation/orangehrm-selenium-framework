package com.OrangeHRM.UI.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * TestConfig - Configuration management class for test properties
 * 
 * OOP CONCEPTS USED:
 * 1. ENCAPSULATION - Private static fields with public static access
 * 2. STATIC INITIALIZATION - Properties loaded once at class loading
 * 3. CONSTANT PATTERN - All configuration values as public static final
 * 4. SINGLETON-LIKE BEHAVIOR - Single instance of properties across framework
 * 5. IMMUTABILITY - Final fields prevent modification after initialization
 * 
 * HOW IT WORKS:
 * - Loads config.properties file once when class is first accessed
 * - Converts string properties to appropriate data types (int, boolean)
 * - Provides default values for missing properties
 * - Uses static initialization block for one-time setup
 * - All configuration accessible as constants throughout framework
 * 
 * DESIGN PATTERNS:
 * - Configuration Pattern - Centralized configuration management
 * - Constant Pattern - Immutable configuration values
 * - Lazy Loading - Properties loaded only when needed
 * 
 * USAGE:
 * String url = TestConfig.BASE_URL;
 * int timeout = TestConfig.EXPLICIT_WAIT;
 * boolean headless = TestConfig.HEADLESS;
 */
public class TestConfig {
    
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }
    
    // URL Properties
    public static final String BASE_URL = properties.getProperty("baseURL");
    public static final String LOGIN_URL = properties.getProperty("loginURL");
    public static final String DASHBOARD_URL = properties.getProperty("dashboardURL");
    
    // Wait Timeouts
    public static final int EXPLICIT_WAIT = Integer.parseInt(properties.getProperty("explicitWait", "30"));
    public static final int IMPLICIT_WAIT = Integer.parseInt(properties.getProperty("implicitWait", "10"));
    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(properties.getProperty("pageLoadTimeout", "30"));
    
    // Browser Settings
    public static final String BROWSER = properties.getProperty("browser", "chrome");
    public static final boolean HEADLESS = Boolean.parseBoolean(properties.getProperty("headless", "false"));
    public static final boolean MAXIMIZE_WINDOW = Boolean.parseBoolean(properties.getProperty("windowMaximize", "true"));
    
    // Test Data
    public static final String DEFAULT_USERNAME = properties.getProperty("defaultUsername", "Admin");
    public static final String DEFAULT_PASSWORD = properties.getProperty("defaultPassword", "admin123");
    
    // Screenshot Settings
    public static final boolean SCREENSHOT_ON_FAILURE = Boolean.parseBoolean(properties.getProperty("screenshotOnFailure", "true"));
    public static final String SCREENSHOT_PATH = properties.getProperty("screenshotPath", "target/screenshots/");
    
    // Report Settings
    public static final String REPORT_PATH = properties.getProperty("reportPath", "target/test-reports/");
}
