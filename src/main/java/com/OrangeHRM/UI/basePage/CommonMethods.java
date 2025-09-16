package com.OrangeHRM.UI.basePage;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CommonMethods {
	  private WebDriver driver;
	    private WebDriverWait wait;

	    public CommonMethods(WebDriver driver, WebDriverWait wait) {
	        this.driver = driver;
	        this.wait = wait; // Use the shared wait from BasePage
	    }

	    // Wait for element to be visible
	    public void waitForElementToBeVisible(WebElement element) {
	        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
	        highlightElement(element);
	    }

	    // Wait for element to be clickable
	    public void waitForElementToBeClickable(WebElement element) {
	        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	        highlightElement(element);
	    }

	    // Click on element
	    public void clickElement(WebElement element)  {
	        waitForElementToBeClickable(element);
	        highlightElement(element);
	        element.click();
	    }


	    // Enter text in input field
	    public void enterText(WebElement element, String text) {
	        waitForElementToBeVisible(element);
	        highlightElement(element);
	        // element.clear();
	        element.sendKeys(text);
	    }
	    // Enter text in input field
	    public void claerAndenterText(WebElement element, String text) {
	        waitForElementToBeVisible(element);
	        highlightElement(element);
	        element.clear();
	        element.sendKeys(text);
	    }

	    // Get text from element
	    public String getText(WebElement element) {
	        waitForElementToBeVisible(element);
	        highlightElement(element);
	        return element.getText();
	    }

	    // Highlight element
	    public void highlightElement(WebElement element) {
	        String originalStyle = element.getAttribute("style");
	        String highlightStyle = "background: yellow; border: 2px solid red;";
	        executeJavaScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightStyle);
	        try {
	            Thread.sleep(500); // Highlight for 500 ms
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        executeJavaScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
	    }

	    // Execute JavaScript
	    public void executeJavaScript(String script, Object... args) {
	        ((JavascriptExecutor) driver).executeScript(script, args);
	    }

	    // Switch to frame by index
	    public void switchToFrame(int index) {
	        driver.switchTo().frame(index);
	    }

	    // Switch to frame by name or ID
	    public void switchToFrame(String nameOrId) {
	        driver.switchTo().frame(nameOrId);
	    }

	    // Switch to frame by WebElement
	    public void switchToFrame(WebElement frameElement) {
	        driver.switchTo().frame(frameElement);
	    }

	    // Switch back to default content
	    public void switchToDefaultContent() {
	        driver.switchTo().defaultContent();
	    }

	    // Accept alert
	    public void acceptAlert() {
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	    }

	    // Dismiss alert
	    public void dismissAlert() {
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().dismiss();
	    }

	    // Get alert text
	    public String getAlertText() {
	        wait.until(ExpectedConditions.alertIsPresent());
	        return driver.switchTo().alert().getText();
	    }

	    // Select checkbox
	    public void selectCheckbox(WebElement checkbox) {
	        if (!checkbox.isSelected()) {
	            highlightElement(checkbox);
	            checkbox.click();
	        }
	    }

	    // Deselect checkbox
	    public void deselectCheckbox(WebElement checkbox) {
	        if (checkbox.isSelected()) {
	            highlightElement(checkbox);
	            checkbox.click();
	        }
	    }

	    // Select dropdown by visible text
	    public void selectDropdownByVisibleText(WebElement dropdown, String visibleText) {
	        highlightElement(dropdown);
	        Select select = new Select(dropdown);
	        select.selectByVisibleText(visibleText);
	    }

	    // Select dropdown by value
	    public void selectDropdownByValue(WebElement dropdown, String value) {
	        highlightElement(dropdown);
	        Select select = new Select(dropdown);
	        select.selectByValue(value);
	    }

	    // Select dropdown by index
	    public void selectDropdownByIndex(WebElement dropdown, int index) {
	        highlightElement(dropdown);
	        Select select = new Select(dropdown);
	        select.selectByIndex(index);
	    }

	    // Get selected option from dropdown
	    public String getSelectedOptionFromDropdown(WebElement dropdown) {
	        highlightElement(dropdown);
	        Select select = new Select(dropdown);
	        return select.getFirstSelectedOption().getText();
	    }



	    // Refresh the current page
	    public void refreshPage() {
	        driver.navigate().refresh();
	    }

	    // Navigate back
	    public void navigateBack() {
	        driver.navigate().back();
	    }

	    // Navigate forward
	    public void navigateForward() {
	        driver.navigate().forward();
	    }

	    // Scroll to specific coordinates
	    public void scrollToCoordinates(int x, int y) {
	        executeJavaScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
	    }

	    // Scroll to element
	    public void scrollToElement(WebElement element) {
	        highlightElement(element);
	        executeJavaScript("arguments[0].scrollIntoView(true);", element);
	    }

	    // Scroll to the bottom of the page
	    public void scrollToBottom() {
	        executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");
	    }

	    // Get all window handles
	    public List<String> getAllWindowHandles() {
	        return driver.getWindowHandles().stream().collect(Collectors.toList());
	    }

	    // Switch to window by title
	    public void switchToWindowByTitle(String title) {
	        for (String handle : driver.getWindowHandles()) {
	            driver.switchTo().window(handle);
	            if (driver.getTitle().equals(title)) {
	                break;
	            }
	        }
	    }

	    // Get current URL
	    public String getCurrentURL() {
	        return driver.getCurrentUrl();
	    }

	    // Get page title
	    public String getPageTitle() {
	        return driver.getTitle();
	    }

	    // Clear input field
	    public void clearInputField(WebElement element) {
	        waitForElementToBeVisible(element);
	        highlightElement(element);
	        element.clear();
	    }

	    // Upload file
	    public void uploadFile(WebElement fileInput, String filePath) {
	        highlightElement(fileInput);
	        fileInput.sendKeys(filePath);
	    }

	    // Get attribute value
	    public String getAttributeValue(WebElement element, String attribute) {
	        highlightElement(element);
	        return element.getAttribute(attribute);
	    }

	    // Capture screenshot of element
	    public void captureElementScreenshot(WebElement element, String filePath) {
	        highlightElement(element);
	        File screenshot = element.getScreenshotAs(OutputType.FILE);
	        try {
	            Files.copy(screenshot.toPath(), new File(filePath).toPath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Set implicit wait
	    public void setImplicitWait(Duration duration) {
	        driver.manage().timeouts().implicitlyWait(duration);
	    }

	    // Clear browser cookies
	    public void clearBrowserCookies() {
	        driver.manage().deleteAllCookies();
	    }

	    // Set browser window size
	    public void setWindowSize(int width, int height) {
	        driver.manage().window().setSize(new Dimension(width, height));
	    }
	    //Set page zoom level
	    public void setPageZoom(){
	        //driver.execute_script("document.body.style.zoom='80%'")
	        executeJavaScript("document.body.style.zoom='80%'");
	    }

	    // Maximize browser window
	    public void maximizeWindow() {
	        driver.manage().window().maximize();
	    }

	    // Minimize browser window
	    public void minimizeWindow() {
	        driver.manage().window().setPosition(new Point(-2000, 0));
	    }

	    // Print browser console logs
	    public void printBrowserConsoleLogs() {
	        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
	        for (LogEntry entry : logs) {
	            System.out.println(entry.getLevel() + " " + new Date(entry.getTimestamp()) + " " + entry.getMessage());
	        }
	    }

	    // Retry clicking on an element
	    public void retryClickElement(WebElement element, int attempts) {
	        int retries = 0;
	        while (retries < attempts) {
	            try {
	                clickElement(element);
	                break;
	            } catch (WebDriverException e) {
	                retries++;
	                if (retries == attempts) {
	                    throw e;
	                }
	                try {
	                    Thread.sleep(500); // Wait before retrying
	                } catch (InterruptedException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	    }

	    // Drag and drop from source to target
	    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
	        highlightElement(sourceElement);
	        highlightElement(targetElement);
	        new Actions(driver).dragAndDrop(sourceElement, targetElement).perform();
	    }

	    // Double click on element
	    public void doubleClickElement(WebElement element) {
	        highlightElement(element);
	        new Actions(driver).doubleClick(element).perform();
	    }


	    // Right click on element
	    public void rightClickElement(WebElement element) {
	        highlightElement(element);
	        new Actions(driver).contextClick(element).perform();
	    }

	    // Hover over element
	    public void hoverOverElement(WebElement element) {
	        highlightElement(element);
	        new Actions(driver).moveToElement(element).perform();
	    }

	    // Switch to new window
	    public void switchToNewWindow() {
	        String originalWindow = driver.getWindowHandle();
	        for (String windowHandle : driver.getWindowHandles()) {
	            if (!windowHandle.equals(originalWindow)) {
	                driver.switchTo().window(windowHandle);
	                break;
	            }
	        }
	    }

	    // Close current window and switch back to original window
	    public void closeCurrentWindowAndSwitchBack(String originalWindowHandle) {
	        driver.close();
	        driver.switchTo().window(originalWindowHandle);
	    }

	    // Get element's location
	    public Point getElementLocation(WebElement element) {
	        highlightElement(element);
	        return element.getLocation();
	    }

	    // Get element's size
	    public Dimension getElementSize(WebElement element) {
	        highlightElement(element);
	        return element.getSize();
	    }

	    // Wait for element to contain text
	    public void waitForElementToContainText(WebElement element, String text) {
	        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	    }

	    // Wait for element attribute value
	    public void waitForElementAttributeValue(WebElement element, String attribute, String value) {
	        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	    }

	    // Get all options from dropdown
	    public List<String> getAllOptionsFromDropdown(WebElement dropdown) {
	        highlightElement(dropdown);
	        Select select = new Select(dropdown);
	        return select.getOptions().stream()
	                .map(WebElement::getText)
	                .collect(Collectors.toList());
	    }

	    // Switch to alert and send keys
	    public void switchToAlertAndSendKeys(String text) {
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().sendKeys(text);
	    }

	    // Check if alert is present
	    public boolean isAlertPresent() {
	        try {
	            driver.switchTo().alert();
	            return true;
	        } catch (NoAlertPresentException e) {
	            return false;
	        }
	    }

	    // Get page source
	    public String getPageSource() {
	        return driver.getPageSource();
	    }

	    // Switch to nested frame
	    public void switchToNestedFrame(WebElement parentFrame, WebElement childFrame) {
	        driver.switchTo().frame(parentFrame);
	        driver.switchTo().frame(childFrame);
	    }

	    public void clickElementUsingJavaScript(WebElement element) throws InterruptedException {
	        highlightElement(element);
	        executeJavaScript("arguments[0].click();", element);
	    }

	    public boolean isElementSelected(WebElement element) {
	        return element.isSelected();

	    }

	    public boolean isElementEnabled(WebElement element) {
	        return element.isEnabled();

	    }

	    public boolean isElementDisplayed(WebElement element) {
	        try {
	            return element.isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }

	    public void scrollToElementAndClick(WebElement element) throws InterruptedException {
	        scrollToElement(element);
	        clickElement(element);
	    }

	    public void waitForElementToBeEnabled(WebElement element) {
	        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	    }

	    public void waitForElementToBeDisabled(WebElement element) {
	        wait.until(ExpectedConditions.refreshed(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element))));
	    }

	    // Select from dynamic dropdown
	    public void selectFromDynamicDropdown(WebElement dropdownElement, List<WebElement> dropdownOptions, String optionToSelect) {
	        try {
	            // Click on the dropdown element to reveal options
	            clickElement(dropdownElement);

	            new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> dropdownOptions.size() > 0);

	            System.out.println("Dropdown options size: " + dropdownOptions.size());

	            // Wait for options to be visible
	            waitForElementToBeVisible(dropdownOptions.get(0));
	            boolean optionFound = false;
	            // Iterate through the list of options and select the matching one
	            for (WebElement option : dropdownOptions) {
	                System.out.println("Option text: [" + option.getText().trim() + "]");
	                if (option.getText().trim().equalsIgnoreCase(optionToSelect.trim())) {
	                    System.out.println("Dropdown option  is ready to click: "+option.getText());
	                    clickElement(option);
	                    System.out.println("Dropdown option  is clicked: "+option.getText());
	                    optionFound = true;
	                    break;
	                }

	            }
	            // If the option was not found in the dropdown, enter it manually
	            if (!optionFound) {
	                System.out.println("Option not found in the list. Entering manually: " + optionToSelect);
	             //   dropdownElement.clear();  // Clear the existing text
	                dropdownElement.sendKeys(optionToSelect);  // Type the option manually
	                dropdownElement.sendKeys(Keys.RETURN);  // Press Enter to confirm the input
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Dropdown or Option not found: " + e.getMessage());
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Element is no longer attached to the DOM: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Exception occurred while selecting from dynamic dropdown: " + e.getMessage());
	        }
	    }
	    
	    


}
