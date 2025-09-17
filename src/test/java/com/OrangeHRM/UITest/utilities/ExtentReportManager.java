package com.OrangeHRM.UITest.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.OrangeHRM.UI.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ExtentReportManager - TestNG Listener for automatic Extent Reports generation
 * 
 * This class implements ITestListener to automatically:
 * - Create test entries for each TestNG test method
 * - Capture test results (PASS/FAIL/SKIP)
 * - Generate beautiful HTML reports
 * - Open reports automatically in browser
 * 
 * Design Patterns Used:
 * - Listener Pattern - Hooks into TestNG execution lifecycle
 * - Singleton Pattern - Single instance of ExtentReports
 * - Observer Pattern - Observes test execution events
 * 
 * HOW IT WORKS:
 * 1. onStart() - Initializes ExtentReports when test suite starts
 * 2. onTestSuccess() - Logs successful test execution
 * 3. onTestFailure() - Logs failed test with screenshots
 * 4. onTestSkipped() - Logs skipped tests
 * 5. onFinish() - Generates final report and opens in browser
 * 
 * @author TeluguAutomation
 * @version 1.0
 */
public class ExtentReportManager implements ITestListener {

    // Extent Reports instances
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    // Report file name with timestamp
    String repName;

    /**
     * onStart - Initialize Extent Reports when test suite starts
     * Called once before any test method runs
     * 
     * @param testContext TestNG context containing suite information
     */
    public void onStart(ITestContext testContext) {
        System.out.println("=== ExtentReportManager: onStart ===");
        
        // Generate timestamp for unique report names
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "OrangeHRM_TestReport_" + timeStamp + ".html";
        
        // Create report directory
        String reportPath = TestConfig.EXTENT_REPORT_PATH;
        try {
            Path reportDirPath = Paths.get(reportPath);
            if (!Files.exists(reportDirPath)) {
                Files.createDirectories(reportDirPath);
                System.out.println("Extent Reports directory created: " + reportPath);
            }
        } catch (IOException e) {
            System.out.println("Failed to create Extent Reports directory: " + e.getMessage());
        }
        
        // Initialize ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter(reportPath + repName);
        
        // Configure report appearance
        sparkReporter.config().setDocumentTitle(TestConfig.EXTENT_REPORT_DOCUMENT_TITLE);
        sparkReporter.config().setReportName(TestConfig.EXTENT_REPORT_TITLE);
        sparkReporter.config().setTheme(Theme.valueOf(TestConfig.EXTENT_REPORT_THEME.toUpperCase()));
        sparkReporter.config().setTimeStampFormat(TestConfig.EXTENT_REPORT_TIME_STAMP_FORMAT);
        
        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Set system information
        extent.setSystemInfo("Application", "OrangeHRM");
        extent.setSystemInfo("Framework", "Selenium WebDriver + TestNG");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "Test Environment");
        
        // Get browser from TestNG XML parameters
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null && !browser.isEmpty()) {
            extent.setSystemInfo("Browser", browser);
        } else {
            extent.setSystemInfo("Browser", TestConfig.BROWSER);
        }
        
        // Get included groups from TestNG XML
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Test Groups", includedGroups.toString());
        }
        
        System.out.println("Extent Reports initialized: " + reportPath + repName);
    }

    /**
     * onTestSuccess - Log successful test execution
     * Called when a test method passes
     * 
     * @param result TestNG result containing test information
     */
    public void onTestSuccess(ITestResult result) {
        System.out.println("=== ExtentReportManager: onTestSuccess ===");
        
        // Create test entry
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
        
        System.out.println("✓ Test passed: " + result.getName());
    }

    /**
     * onTestFailure - Log failed test execution with screenshots
     * Called when a test method fails
     * 
     * @param result TestNG result containing test information
     */
    public void onTestFailure(ITestResult result) {
        System.out.println("=== ExtentReportManager: onTestFailure ===");
        
        // Create test entry
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        // Add screenshot on failure (if enabled)
        if (TestConfig.SCREENSHOT_ON_FAILURE) {
            try {
                String screenshotPath = new com.OrangeHRM.UITest.testBase.BaseTest().captureScreen(result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
                System.out.println("Screenshot captured: " + screenshotPath);
            } catch (Exception e) {
                test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        
        System.out.println("✗ Test failed: " + result.getName());
    }

    /**
     * onTestSkipped - Log skipped test execution
     * Called when a test method is skipped
     * 
     * @param result TestNG result containing test information
     */
    public void onTestSkipped(ITestResult result) {
        System.out.println("=== ExtentReportManager: onTestSkipped ===");
        
        // Create test entry
        test = extent.createTest(result.getTestClass().getName() + " :: " + result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        // Add screenshot on skip (if enabled)
        if (TestConfig.SCREENSHOT_ON_FAILURE) {
            try {
                String screenshotPath = new com.OrangeHRM.UITest.testBase.BaseTest().captureScreen(result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
                System.out.println("Screenshot captured: " + screenshotPath);
            } catch (Exception e) {
                test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        
        System.out.println("⚠ Test skipped: " + result.getName());
    }

    /**
     * onFinish - Generate final report and open in browser
     * Called after all tests in the suite complete
     * 
     * @param testContext TestNG context containing suite information
     */
    public void onFinish(ITestContext testContext) {
        System.out.println("=== ExtentReportManager: onFinish ===");
        
        if (extent != null) {
            // Flush reports to generate HTML
            extent.flush();
            System.out.println("Extent Reports generated successfully!");
            
            // Report generated successfully
            String pathOfExtentReport = TestConfig.EXTENT_REPORT_PATH + repName;
            File extentReport = new File(pathOfExtentReport);
            
            if (extentReport.exists()) {
                System.out.println("Extent Report generated successfully: " + pathOfExtentReport);
                
                // To open the report automatically
                try {
                    Desktop.getDesktop().browse(extentReport.toURI());
                    System.out.println("Report opened in browser automatically!");
                } catch (IOException e) {
                    System.out.println("Could not open report automatically: " + e.getMessage());
                    System.out.println("Please open the report manually in your browser.");
                }
            } else {
                System.out.println("Report file not found: " + pathOfExtentReport);
            }
        }
    }

}
