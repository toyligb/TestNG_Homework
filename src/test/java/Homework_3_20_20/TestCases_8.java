package Homework_3_20_20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

public class TestCases_8 {

    /**
     * Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
     * Step 2. And click on “Autocomplete”.
     * Step 3. Enter “United States of America” into country input box.
     * Step 4. Verify that following message is displayed:
     * “You selected: United States of America”
     */

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase8() {
        WebElement autoCompleteLink = driver.findElement(By.linkText("Autocomplete"));
        autoCompleteLink.click();
        BrowserUtils.wait(3);

        WebElement country = driver.findElement(By.id("myCountry"));
        country.sendKeys("United States of America");
        BrowserUtils.wait(2);

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
        BrowserUtils.wait(3);

        String expectedMsg = "You selected: United States of America";
        String actualMsg = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(actualMsg, expectedMsg);

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(3);
        driver.quit();
    }

}
