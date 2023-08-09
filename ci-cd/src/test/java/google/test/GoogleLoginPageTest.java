package google.test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ExtentManager;

public class GoogleLoginPageTest {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;
	//commit- test
	
	@BeforeTest
	public void setUp() {
		// Set up WebDriver
		//C:\Users\91914\ci-cd workspace\ci-cd\driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\91914\\ci-cd workspace\\ci-cd\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/");
		extent = ExtentManager.getInstance();
		test = extent.createTest("Google Login Test", "Test scenario for Google login page");
	}

	// Scenario 1: Verify page title and URL
	@Test
	public void verifyPageTitleAndURL() {
		String expectedTitle = "Sign in - Google Accounts";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Page title is not as expected.");


	}


	
	@AfterTest
	public void tearDown() throws InterruptedException {

		

		if (driver != null) {
			if (test.getStatus() == Status.FAIL || test.getStatus() == Status.PASS ) {
				String testName = extent.getClass().getName();
				String screenshotPath = captureScreenshot(driver,testName);
				test.addScreenCaptureFromPath(screenshotPath);
			}
			extent.flush();
		}
		driver.quit();
	}

	private String captureScreenshot(WebDriver driver, String testName) {
	    // Generate a timestamp to be used in the screenshot file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    Date currentDate = new Date();
	    String timestamp = dateFormat.format(currentDate);

	    // Create a directory to store the screenshots if it doesn't exist
	    String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
	    Path screenshotPath = Paths.get(screenshotDir);
	    if (!Files.exists(screenshotPath)) {
	        try {
	            Files.createDirectories(screenshotPath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Take the screenshot using the WebDriver
	    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    // Generate the file name for the screenshot
	    String screenshotFileName = testName + "_" + timestamp + ".png";
	    String screenshotFilePath = screenshotDir + screenshotFileName;

	    try {
	        // Save the screenshot to the specified file path
	        FileHandler.copy(screenshotFile, new File(screenshotFilePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Return the file path of the captured screenshot
	    return screenshotFilePath;
	}
}
