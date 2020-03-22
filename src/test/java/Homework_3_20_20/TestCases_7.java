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

public class TestCases_7 {

    /**
     * Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
     * Step 2. And click on “File Upload".
     * Step 3. Upload any file with .txt extension from your computer.
     * Step 4. Click “Upload” button.
     * Step 5. Verify that subject is: “File Uploaded!”
     * Step 6. Verify that uploaded file name is displayed.
     * Note: use element.sendKeys(“/file/path”) with
     * specifying path to the file for uploading. Run this
     * method against “Choose File” button.
     */

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
    }

    @Test
    public void testCase7() {
        WebElement fileUpload = driver.findElement(By.xpath("//a[text()='File Upload']"));
        fileUpload.click();
        BrowserUtils.wait(3);

        WebElement uploadBtn = driver.findElement(By.id("file-upload"));
        String filePath = "C:\\Users\\admin\\Downloads\\uploadThisFile.txt";
        System.out.println(filePath);
        uploadBtn.sendKeys(filePath);

        BrowserUtils.wait(3);
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(3);

        String expectedSubject = "File Uploaded!";
        String actualSubject = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();

        Assert.assertEquals(actualSubject, expectedSubject);

        String expectedFileName = "uploadThisFile.txt";
        String actualFileName = driver.findElement(By.xpath("//*[@id='uploaded-files']")).getText();

        Assert.assertEquals(actualFileName, expectedFileName);

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(3);
        driver.quit();
    }

}
