package Homework_3_20_20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

public class TestCases_9_to_12 {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        WebElement statusCodeLink = driver.findElement(By.linkText("Status Codes"));
        statusCodeLink.click();
        BrowserUtils.wait(3);
    }

    @Test
    public void testCase9() {
        WebElement statusCode = driver.findElement(By.linkText("200"));
        statusCode.click();

        String expectedMessage = "This page returned a 200 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase10() {
        WebElement statusCode = driver.findElement(By.linkText("301"));
        statusCode.click();

        String expectedMessage = "This page returned a 301 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase11() {
        WebElement statusCode = driver.findElement(By.linkText("404"));
        statusCode.click();

        String expectedMessage = "This page returned a 404 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase12() {
        /**
         * Step 1. Go to "https://practice-cybertekschool.herokuapp.com"
         * Step 2. And click on "Status Codes".
         * Step 3. Then click on "500".
         * Step 4. Verify that following message is displayed: "This page returned a 500 status code"
         */
/*
  xpath :
     //a[text()='Status Codes']
     //a[contains(text(),'Status Codes')]
     //ul/li[46]
     //a[@href="/status_codes"]
  linkText :
     lintText("Status Codes")
  partialLinkText:
     partialLintText("Status Codes")
     partiallinkText("Status")
     partiallinkText("Codes")
     CssSelector
     [href="/status_codes"]
 */

//Step 3
        WebElement statusCode = driver.findElement(By.linkText("500"));
        statusCode.click();
// Step 4
        String expectedMessage = "This page returned a 500 status code";
        WebElement displayedMessageElement = driver.findElement(By.xpath("//p"));
        String actualMessage = displayedMessageElement.getText();
//System.out.println(actualMessage);
        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("FAILED");
        }
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(3);
        driver.quit();
    }

}
