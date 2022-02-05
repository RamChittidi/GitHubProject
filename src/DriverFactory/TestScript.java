package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.EmpPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;

public class TestScript {
	WebDriver driver;
	@BeforeTest
	public void setUp()
	{
		System.setProperty("WebDriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
   @Test
   public void employeeCreation() throws Throwable
   {
	   EmpPage emp=PageFactory.initElements(driver, EmpPage.class);
	   emp.verifyEmp("raman", "Chittidi");
   }
   @AfterTest
   public void tearDown() throws Throwable
   {
	   LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	   logout.verifyLogout();
	   driver.close();
   }

}
