package Homework_3_20_20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

public class TestCases_6 {

    /*
    * Step 1. Go to "https://www.tempmailaddress.com/"
    * Step 2. Copy and save email as a string.
    * Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
    * Step 4. And click on “Sign Up For Mailing List".
    * Step 5. Enter any valid name.
    * Step 6. Enter email from the Step 2.
    * Step 7. Click Sign Up
    * Step 8. Verify that following message is displayed:
        “Thank you for signing up. Click the button below to return to the home page.”
    * Step 9. Navigate back to the “https://www.tempmailaddress.com/”
    * Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
    * Step 11. Click on that email to open it.
    * Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
    * Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
     */

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase6() {
        // Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get("https://www.tempmailaddress.com/");
        BrowserUtils.wait(2);

        // Step 2. Copy and save email as a string.
        String email = driver.findElement(By.xpath("//*[@id=\"email\"]")).getText();

        // Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com");
        BrowserUtils.wait(2);

        // Step 4. And click on “Sign Up For Mailing List"
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[43]/a")).click();
        BrowserUtils.wait(1);

        // Step 5. Enter any valid name
        driver.findElement(By.name("full_name")).sendKeys("Nabelli");
        BrowserUtils.wait(1);

        // Step 6. Enter email from the Step 2
        driver.findElement(By.name("email")).sendKeys(email);
        BrowserUtils.wait(2);

        // Step 7. Click Sign Up
        driver.findElement(By.name("wooden_spoon")).click();

        // Step 8. Verify that following message is displayed:
        //        “Thank you for signing up. Click the button below to return to the home page.”
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        String actual = driver.findElement(By.name("signup_message")).getText();
        Assert.assertEquals(actual, expected);

        // Step 9. Navigate back to the “https://www.tempmailaddress.com/”
        driver.navigate().to("https://www.tempmailaddress.com/");
        BrowserUtils.wait(3);

        // Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
        String expectedEmail = "do-not-reply@practice.cybertekschool.com";
        //String actualEmail = driver.findElement(By.xpath("//*[@id=\"schranka\"]/tr[1]/td[1]")).getText();
        String actualEmail = driver.findElement(By.xpath("//*[@class=\"from\"]")).getText().trim();
        Assert.assertEquals(actualEmail, expectedEmail);

        // Step 11. Click on that email to open it
        driver.findElement(By.xpath("//*[@id=\"schranka\"]/tr[1]/td[1]")).click();

        // Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
        String expectedFrom = "do-not-reply@practice.cybertekschool.com";
        String actualFrom = driver.findElement(By.xpath("//*[@id=\"odesilatel\"]")).getText();
        Assert.assertEquals(actualFrom, expectedFrom);

        // Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject = driver.findElement(By.xpath("//*[@id=\"predmet\"]")).getText();
        Assert.assertEquals(actualSubject, expectedSubject);
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(2);
        driver.quit();
    }

}
