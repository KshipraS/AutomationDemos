package TestScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogIn extends TestBaseSetUp
{
	 @Test(dataProvider="Authentication")
	 public void test(String sUsername, String sPassword) 
	 {  
		  
	      driver.findElement(By.xpath(object.getProperty("loginLink"))).click();
	      driver.findElement(By.xpath(object.getProperty("emailText"))).clear();
	      driver.findElement(By.xpath(object.getProperty("emailText"))).sendKeys(sUsername);
	      driver.findElement(By.xpath(object.getProperty("passwordText"))).sendKeys(sPassword);
	      driver.findElement(By.xpath(object.getProperty("logInButton"))).click();
	      driver.findElement(By.xpath(object.getProperty("nameDropdown"))).click();
	      driver.findElement(By.xpath(object.getProperty("logOut"))).click();
	  }
	  
	  @DataProvider(name = "Authentication")
	  public static Object[][] credentials() 
	  {
	        return new Object[][] { { "test103@boots.com", "hello123" }, { "test202@boots.com", "hello123" }};
	  }
}
