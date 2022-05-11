package usingJavaPL;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class SampleTC {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, AWTException {
	
	//Setting system properties of ChromeDriver
	//System.setProperty("webdriver.chrome.driver", "..\\AutomationTesting\\src\\drivers\\chromedriver.exe");

	//Creating an object of ChromeDriver
//	WebDriver driver = new ChromeDriver();
//	driver.manage().window().maximize();
	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setBrowserName("chrome");
//Comment from Vaishu m/c	
	try {
		driver = new RemoteWebDriver(new URL("http://192.168.0.112:4444"),caps);
	}catch(MalformedURLException e) {
		e.printStackTrace();
	}
	
	//Creating an object of HTMLUnitDriver
	//WebDriver driver = new HtmlUnitDriver();

	//Deleting all the cookies
	driver.manage().deleteAllCookies();

	//Specifiying pageLoadTimeout and Implicit wait
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	//launching the specified URL
	driver.get("https://www.google.com/");
	
	WebElement ele1 = driver.findElement(By.name("q"));
	Point point = ele1.getLocation();
	System.out.println("X cordinate : " + point.x + "Y cordinate: " + point.y);
	
	//Locating the elements using name locator for the text box
	driver.findElement(By.name("q")).sendKeys("Automation testing using Selenium");
	System.out.println("Title is:"+driver.getTitle());
	
	
	//name locator for google search button
//	WebElement searchIcon = driver.findElement(By.name("btnK"));
//	searchIcon.click();
	
	//Using Robot class
	Robot r = new Robot();
	r.keyPress(KeyEvent.VK_ENTER);
	r.keyRelease(KeyEvent.VK_ENTER);
	
	System.out.println("Title is:"+driver.getTitle());
	
	
	
	//r.createScreenCapture(screenRect);
	//Get all links in the page
	List<WebElement> ele = driver.findElements(By.xpath("//div[@style='width:600px']//a/h3"));
	//	List<WebElement> ele = driver.findElements(By.tagName("a"));
	for (int i = 0; i<ele.size(); i = i+1) {
        System.out.println("Name of Link# : " + i + ele.get(i).getText());
     }
	driver.quit();
	}
}
