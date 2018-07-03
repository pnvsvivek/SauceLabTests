package SauceLabsTests.SauceLabTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleSearch {
	
	public static final String USERNAME = "vivek0123";
	public static final String ACCESS_KEY = "3cbdf857-702e-4dc7-9870-f7403439341b";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
	WebDriver driver=null;
	
	ExtentReports report = new ExtentReports("./testReports/report.html");
	ExtentTest test = null;
	
	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		DesiredCapabilities caps = DesiredCapabilities.safari();
		caps.setCapability("platform", "OS X 10.10");
		caps.setCapability("version", "8.0");
		driver = new RemoteWebDriver(new URL(URL),caps);
		driver.navigate().to("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void searchGoogle()
	{
		try
		{
		test = report.startTest("searchGoogle");
		driver.findElement(By.id("lst-ib")).sendKeys("Vivekananda");
		driver.findElement(By.className("lsb")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Swami Vivekananda')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains("Swami Vivekananda - Wikipedia"));
		test.log(LogStatus.PASS, "searchGoogle method is successful");
		report.endTest(test);
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Exception in method", e.getMessage());
		}
	}
	
	@Test
	public void changeSearchCriterion()
	{
		try
		{
		test = report.startTest("searchGoogle");
		driver.findElement(By.id("lst-ib")).sendKeys("Vivekananda");
		driver.findElement(By.className("lsb")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("camel");
		List<WebElement> list = driver.findElements(By.tagName("a"));
		if(list.size()!=0)
		{
			test.log(LogStatus.PASS, "changeSearchCriterion method is successful");
		}
		else
		{
			test.log(LogStatus.FAIL, "changeSearchCriterion method is failed");
		}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Exception in method", e.getMessage());
		}
	} 
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@AfterSuite
	public void getReport()
	{
		report.flush();
	}
}
