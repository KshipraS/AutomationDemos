package TestScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

import Utility.Xlfile_Reader;

public class TestBaseSetUp 
{	
	public static WebDriver driver;
	public static Xlfile_Reader excel;
	public static Properties config = new Properties();
	public static Properties object = new Properties();
	public static final Logger SELENIUM_LOGS = Logger.getRootLogger();
	public static final Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void init() throws IOException
	{
		if(driver==null)
		{
			FileInputStream fis = new FileInputStream("./src/Properties/Configuration.properties");
			config.load(fis);		
			
			FileInputStream fis1 = new FileInputStream("./src/Properties/Object.properties");
			object.load(fis1);	
		
			excel =new Xlfile_Reader("./src/TestDataFile/BootsTestData.xlsx");
		
			if(config.getProperty("browser").equals("firefox"))
			{
				driver = new FirefoxDriver();
			}
			else 
				if(config.getProperty("browser").equals("IE"))
				 {
				     System.setProperty("webdriver.ie.driver", "./src/Utility/BrowserDrivers/IEDriverServer.exe");
				     driver = new InternetExplorerDriver();
				 }
				 else
					 if(config.getProperty("browser").equals("chrome"))
					 {
						 System.setProperty("webdriver.chrome.driver","./src/Utility/BrowserDrivers/chromedriver.exe");
						 driver = new ChromeDriver();
					 }
		}
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//System.out.println("Window maximised");
		//System.out.println(config.getProperty("testsite"));
		driver.get(config.getProperty("testsite"));	
		//System.out.println("URL entered");
		driver.findElement(By.xpath(object.getProperty("closePopUpon"))).click();	 
	}
}
