package com.OrangeHRM.UI.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.OrangeHRM.UI.basePage.BasePage;

/**
 * DashboardPage - Page Object Model for OrangeHRM Dashboard functionality
 */
public class DashboardPage extends BasePage {

    // ==================== HEADER ELEMENTS ====================
    
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardTitle;
    
    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement userProfileDropdown;
    
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBox;
    
    // ==================== MAIN MENU ITEMS ====================
    
    @FindBy(xpath = "//a[contains(@href, 'admin')]//span[text()='Admin']")
    private WebElement adminMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'pim')]//span[text()='PIM']")
    private WebElement pimMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'leave')]//span[text()='Leave']")
    private WebElement leaveMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'time')]//span[text()='Time']")
    private WebElement timeMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'recruitment')]//span[text()='Recruitment']")
    private WebElement recruitmentMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'performance')]//span[text()='Performance']")
    private WebElement performanceMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'dashboard')]//span[text()='Dashboard']")
    private WebElement dashboardMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'directory')]//span[text()='Directory']")
    private WebElement directoryMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'maintenance')]//span[text()='Maintenance']")
    private WebElement maintenanceMenu;
    
    @FindBy(xpath = "//a[contains(@href, 'buzz')]//span[text()='Buzz']")
    private WebElement buzzMenu;
    
    // ==================== DASHBOARD WIDGETS ====================
    
    @FindBy(xpath = "//p[text()='Time at Work']")
    private WebElement timeAtWorkWidget;
    
    @FindBy(xpath = "//p[text()='My Actions']")
    private WebElement myActionsWidget;
    
    @FindBy(xpath = "//p[text()='Quick Launch']")
    private WebElement quickLaunchWidget;
    
    @FindBy(xpath = "//p[text()='Employees on Leave Today']")
    private WebElement employeesOnLeaveWidget;
    
    @FindBy(xpath = "//p[text()='Employee Distribution by Sub Unit']")
    private WebElement employeeDistributionSubUnitWidget;
    
    @FindBy(xpath = "//p[text()='Employee Distribution by Location']")
    private WebElement employeeDistributionLocationWidget;
    
    // ==================== QUICK LAUNCH ITEMS ====================
    
    @FindBy(xpath = "//button[@title='Assign Leave']")
    private WebElement assignLeaveButton;
    
    @FindBy(xpath = "//button[@title='Leave List']")
    private WebElement leaveListButton;
    
    @FindBy(xpath = "//button[@title='Timesheets']")
    private WebElement timesheetsButton;
    
    @FindBy(xpath = "//button[@title='Apply Leave']")
    private WebElement applyLeaveButton;
    
    @FindBy(xpath = "//button[@title='My Leave']")
    private WebElement myLeaveButton;
    
    @FindBy(xpath = "//button[@title='My Timesheet']")
    private WebElement myTimesheetButton;
    

    /**
     * Constructor - Initializes DashboardPage with WebDriver
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ==================== DASHBOARD VERIFICATION METHODS ====================

    /**
     * Verify dashboard title is displayed
     */
    public boolean isDashboardTitleVisible() {
        try {
            commonMethods.waitForElementToBeVisible(dashboardTitle);
            return dashboardTitle.isDisplayed();
        } catch (Exception e) {
            System.out.println("Dashboard title not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get dashboard title text
     */
    public String getDashboardTitle() {
        try {
            commonMethods.waitForElementToBeVisible(dashboardTitle);
            return dashboardTitle.getText();
        } catch (Exception e) {
            System.out.println("Error getting dashboard title: " + e.getMessage());
            return "";
        }
    }

    /**
     * Verify user profile dropdown is visible
     */
    public boolean isUserProfileVisible() {
        try {
            return userProfileDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click user profile dropdown
     */
    public void clickUserProfileDropdown() {
        commonMethods.clickElement(userProfileDropdown);
    }

    /**
     * Verify search box is visible
     */
    public boolean isSearchBoxVisible() {
        try {
            return searchBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter text in search box
     */
    public void enterSearchText(String searchText) {
        commonMethods.enterText(searchBox, searchText);
    }

    // ==================== MENU VERIFICATION METHODS ====================

    /**
     * Verify all main menu items are visible
     */
    public boolean areAllMainMenusVisible() {
        try {
            boolean adminVisible = adminMenu.isDisplayed();
            boolean pimVisible = pimMenu.isDisplayed();
            boolean leaveVisible = leaveMenu.isDisplayed();
            boolean timeVisible = timeMenu.isDisplayed();
            boolean recruitmentVisible = recruitmentMenu.isDisplayed();
            boolean performanceVisible = performanceMenu.isDisplayed();
            boolean dashboardVisible = dashboardMenu.isDisplayed();
            boolean directoryVisible = directoryMenu.isDisplayed();
            boolean maintenanceVisible = maintenanceMenu.isDisplayed();
            boolean buzzVisible = buzzMenu.isDisplayed();
            
            return adminVisible && pimVisible && leaveVisible && timeVisible && 
                   recruitmentVisible && performanceVisible && dashboardVisible && 
                   directoryVisible && maintenanceVisible && buzzVisible;
        } catch (Exception e) {
            System.out.println("Error checking main menus: " + e.getMessage());
            return false;
        }
    }

    /**
     * Click on specific menu item
     */
    public void clickMenu(String menuName) {
        switch (menuName.toLowerCase()) {
            case "admin":
                commonMethods.clickElement(adminMenu);
                break;
            case "pim":
                commonMethods.clickElement(pimMenu);
                break;
            case "leave":
                commonMethods.clickElement(leaveMenu);
                break;
            case "time":
                commonMethods.clickElement(timeMenu);
                break;
            case "recruitment":
                commonMethods.clickElement(recruitmentMenu);
                break;
            case "performance":
                commonMethods.clickElement(performanceMenu);
                break;
            case "dashboard":
                commonMethods.clickElement(dashboardMenu);
                break;
            case "directory":
                commonMethods.clickElement(directoryMenu);
                break;
            case "maintenance":
                commonMethods.clickElement(maintenanceMenu);
                break;
            case "buzz":
                commonMethods.clickElement(buzzMenu);
                break;
            default:
                throw new IllegalArgumentException("Unknown menu: " + menuName);
        }
    }

    // ==================== WIDGET VERIFICATION METHODS ====================

    /**
     * Verify all dashboard widgets are visible
     */
    public boolean areAllWidgetsVisible() {
        try {
            boolean timeAtWorkVisible = timeAtWorkWidget.isDisplayed();
            boolean myActionsVisible = myActionsWidget.isDisplayed();
            boolean quickLaunchVisible = quickLaunchWidget.isDisplayed();
            boolean employeesOnLeaveVisible = employeesOnLeaveWidget.isDisplayed();
            boolean employeeDistributionSubUnitVisible = employeeDistributionSubUnitWidget.isDisplayed();
            boolean employeeDistributionLocationVisible = employeeDistributionLocationWidget.isDisplayed();
            
            return timeAtWorkVisible && myActionsVisible && quickLaunchVisible && 
                   employeesOnLeaveVisible && employeeDistributionSubUnitVisible && 
                   employeeDistributionLocationVisible;
        } catch (Exception e) {
            System.out.println("Error checking widgets: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify Quick Launch widget is visible
     */
    public boolean isQuickLaunchVisible() {
        try {
            return quickLaunchWidget.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify Time at Work widget is visible
     */
    public boolean isTimeAtWorkVisible() {
        try {
            return timeAtWorkWidget.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== QUICK LAUNCH METHODS ====================

    /**
     * Click Assign Leave button
     */
    public void clickAssignLeave() {
        commonMethods.clickElement(assignLeaveButton);
    }

    /**
     * Click Leave List button
     */
    public void clickLeaveList() {
        commonMethods.clickElement(leaveListButton);
    }

    /**
     * Click Timesheets button
     */
    public void clickTimesheets() {
        commonMethods.clickElement(timesheetsButton);
    }

    /**
     * Click Apply Leave button
     */
    public void clickApplyLeave() {
        commonMethods.clickElement(applyLeaveButton);
    }

    /**
     * Click My Leave button
     */
    public void clickMyLeave() {
        commonMethods.clickElement(myLeaveButton);
    }

    /**
     * Click My Timesheet button
     */
    public void clickMyTimesheet() {
        commonMethods.clickElement(myTimesheetButton);
    }


    // ==================== GENERAL VERIFICATION METHODS ====================

    /**
     * Verify complete dashboard functionality
     */
    public boolean verifyDashboardFunctionality() {
        System.out.println("=== Verifying Dashboard Functionality ===");
        
        boolean titleVisible = isDashboardTitleVisible();
        boolean profileVisible = isUserProfileVisible();
        boolean searchVisible = isSearchBoxVisible();
        boolean menusVisible = areAllMainMenusVisible();
        boolean widgetsVisible = areAllWidgetsVisible();
        
        System.out.println("Dashboard Title: " + (titleVisible ? "✓" : "✗"));
        System.out.println("User Profile: " + (profileVisible ? "✓" : "✗"));
        System.out.println("Search Box: " + (searchVisible ? "✓" : "✗"));
        System.out.println("Main Menus: " + (menusVisible ? "✓" : "✗"));
        System.out.println("Widgets: " + (widgetsVisible ? "✓" : "✗"));
        
        return titleVisible && profileVisible && searchVisible && menusVisible && widgetsVisible;
    }

    /**
     * Get current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Check if page title contains specific text
     */
    public boolean isPageTitleContains(String expectedText) {
        String pageTitle = getPageTitle();
        return pageTitle.contains(expectedText);
    }

    /**
     * Check if current URL contains specific text
     */
    public boolean isURLContains(String expectedText) {
        String currentURL = getCurrentURL();
        return currentURL.contains(expectedText);
    }

}
