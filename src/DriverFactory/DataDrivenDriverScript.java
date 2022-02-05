package DriverFactory;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDrivenDriverScript {
       WebDriver driver;
       String inputpath="G:\\Eclipse_1\\Automation_frameworks\\TestInput\\login.xlsx";
       String outputpath="G:\\Eclipse_1\\Automation_frameworks\\TestOutput\\loginResult.xlsx";
       Properties config;
       ExtentReports report;
       ExtentTest test;
       @BeforeTest
       public void setup() 
       {
    	   //Define path to generate reports
    	   report=new ExtentReports("./Reports/Datadriven.html");
    	   System.setProperty("webdriver.chrome.driver","G:\\Eclipse_1\\Automation_frameworks\\CommonDrivers\\chromedriver.exe");
    	   driver=new ChromeDriver();
    	   driver.manage().deleteAllCookies();
    	   driver.manage().window().maximize();
    	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       }
       @Test
       public void verifyLogin()throws Throwable
       {
    	   config=new Properties();
    	   config.load(new FileInputStream("G:\\Eclipse_1\\Automation_frameworks\\PropertyFile\\Login.properties"));
    	   //create reference object to call all excel methods
    	   ExcelFileUtil xl=new ExcelFileUtil(inputpath);
    	   //count no of rows in a sheet
    	   int rc=xl.rowCount("Login");
    	   Reporter.log("no.of rows::"+rc,true);
    	   for(int i=1;i<=rc;i++)
    	   {
    		   test=report.startTest("ValidateLogin");
    		   driver.get(config.getProperty("Url"));
    		   //Read cell data from Excel
    		   String username=xl.getCelldata("Login", i, 0);
    		   String password=xl.getCelldata("Login", i, 1);
    		   driver.findElement(By.xpath(config.getProperty("Objuser"))).sendKeys(username);
    		   driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
    		   driver.findElement(By.xpath(config.getProperty("Objlogin"))).click();
    		   String expected="dashboard";
    		   String Actual=driver.getCurrentUrl();
    		   if(Actual.contains(expected))
    		   {
    			 //write as login success into results cell
    			   xl.setCelldata("Login", i, 2, "Login Sucess", outputpath);
    			 //write as  pass into status cell  
    			   xl.setCelldata("Login", i, 3, "pass", outputpath);
    			   test.log(LogStatus.PASS, "Login Success:::"+expected+"         "+Actual);
    			   Reporter.log("Login Success"+expected+"          "+Actual);
    		   }else
    		   {

    				//java.io.File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    				//FileUtils.copyFile(screen, new File("./Screens/iteration/"+i+"Loginpage.jpg"));    			  
    				String errormessage=driver.findElement(By.xpath(config.getProperty("Objmessage"))).getText();
      			 //write as login failed into results cell
    			   xl.setCelldata("Login", i, 2,errormessage , outputpath);
    			 //write as  fail into status cell
    			   xl.setCelldata("Login", i, 3, "fail", outputpath);
    			   test.log(LogStatus.FAIL, errormessage+"       "+expected+"        "+Actual);
    			   Reporter.log("Login Fail"+errormessage+"       "+expected+"        "+Actual);
    		   }
    		  report.endTest(test);
    		  report.flush();
    	   }
       }
       @AfterTest
       public void tearDown()
       {
    	   driver.close();
       }
}
