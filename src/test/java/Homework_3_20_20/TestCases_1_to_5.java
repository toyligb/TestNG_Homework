package Homework_3_20_20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

import java.util.Arrays;
import java.util.List;

public class TestCases_1_to_5 {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        WebElement regForm = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[40]/a"));
        regForm.click();
        BrowserUtils.wait(2);
    }

    @Test
    public void testCase1() {
        WebElement dateOfBirth = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/input"));
        dateOfBirth.sendKeys("wrong_dob");
        BrowserUtils.wait(2);
        String expected = "The date of birth is not valid";
        String actual = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]")).getText();
        Assert.assertEquals(actual, expected);
        //System.out.println(expected.equals(actual));
    }

    @Test
    public void testCase2() {
        List<String> expectedLst = Arrays.asList("C++", "Java", "JavaScript");
        List<WebElement> actual = driver.findElements(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div//label"));
        for (WebElement each : actual) {
            System.out.println("each = " + each.getText());
        }

        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(actual.get(i).getText(), expectedLst.get(i));
        }

    }

    @Test
    public void testCase3() {
        driver.findElement(By.name("firstname")).sendKeys("a");
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[2]"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @Test
    public void testCase4() {
        driver.findElement(By.name("lastname")).sendKeys("a");
        WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[2]"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @Test
    public void testCase5() {
        driver.findElement(By.name("firstname")).sendKeys("Nabelli");
        driver.findElement(By.name("lastname")).sendKeys("Belliyev");
        driver.findElement(By.name("username")).sendKeys("bellinabelli");
        driver.findElement(By.name("email")).sendKeys("bellinabelli@nabelli.com");
        driver.findElement(By.name("password")).sendKeys("belli1234");
        driver.findElement(By.name("phone")).sendKeys("916-916-9116");
        driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[7]/div/div[1]/label/input")).click();
        driver.findElement(By.name("birthday")).sendKeys("03/20/2000");
        Select depSelect = new Select(driver.findElement(By.name("department")));
        depSelect.selectByValue("AO");
        Select jobSelect = new Select(driver.findElement(By.name("job_title")));
        jobSelect.selectByVisibleText("SDET");
        List<WebElement> chkBoxes = driver.findElements(By.xpath("//*[contains(@id, 'inline')]"));
        for (int i = 0; i < chkBoxes.size(); i++) {
            System.out.println(chkBoxes.get(i).getAttribute("value"));
            if (chkBoxes.get(i).getAttribute("value").equalsIgnoreCase("java")) {
                chkBoxes.get(i).click();
                break;
            }
        }
        BrowserUtils.wait(2);
        driver.findElement(By.id("wooden_spoon")).click();//click on submit button
        BrowserUtils.wait(2);
        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/p")).getText();
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(2);
        driver.quit();
    }

}
