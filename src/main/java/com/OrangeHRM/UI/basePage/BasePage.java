package com.OrangeHRM.UI.basePage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.OrangeHRM.UI.config.TestConfig;

public class BasePage {
	

	// make these protected so subclasses can use them directly
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected CommonMethods commonMethods;
	
 // default timeout constructor - reads from config
    public BasePage(WebDriver driver) {
        this(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT));
    }

    // overloaded constructor for custom timeouts
    public BasePage(WebDriver driver, Duration timeout) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver passed to BasePage is null. Ensure driver is initialized before creating page objects.");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        PageFactory.initElements(driver, this);              // init @FindBy fields
        this.commonMethods = new CommonMethods(driver, wait); // reusable UI helpers with shared wait
    }

}