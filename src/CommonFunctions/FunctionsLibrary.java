package CommonFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.PBconstant;

public class FunctionsLibrary extends PBconstant {
       //method for Login
	public static boolean verifyLogin(String username,String Password)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Objusername"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("Objpassword"))).sendKeys(Password);
		driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).click();
		Thread.sleep(5000);
        String expected="adminflow";
        String Actual=driver.getCurrentUrl();
        if(Actual.toLowerCase().contains(expected.toLowerCase()))
        {
        	Reporter.log("Login success",true);
        	return true;
        }else
        {
        	Reporter.log("Login failed",true);
        	return false;
        }
        
	}
	//method for clicking on new branch
	public static void clickBranches()throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	Thread.sleep(5000);	
	}
    //method for new branch 
    public static boolean verifyNewBranch(String bname,String Address1,String Address2,String Address3,String Area,String zipcode,String country,String state,String city)throws Throwable
    {
    	driver.findElement(By.xpath(config.getProperty("Objnewbranch"))).click();
    	Thread.sleep(4000);
    	driver.findElement(By.xpath(config.getProperty("Objbname"))).sendKeys(bname);
    	driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
    	driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
    	driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
    	driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
    	driver.findElement(By.xpath(config.getProperty("Objzipcode"))).sendKeys(zipcode);
    	new Select(driver.findElement(By.xpath(config.getProperty("Objcountry")))).selectByVisibleText(country);
    	Thread.sleep(5000);	
    	new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(state);
    	Thread.sleep(5000);	
    	new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
    	Thread.sleep(5000);	
        //capture alert message
        String expectedalert="new Branch with";
        String Actualalert=driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        if(Actualalert.toLowerCase().contains(expectedalert.toLowerCase()))
        {
        	Reporter.log("Branch creation Sucess"+expectedalert+"        "+Actualalert);
        	return true;
        }else
        {
        	Reporter.log("Branch creation failed"+expectedalert+"        "+Actualalert);
        	return false;
        }
    }
    //method for branch updation
    public static boolean verifyBranchUpdate(String bname,String address1,String zipcode)throws Throwable
    {
    	driver.findElement(By.xpath(config.getProperty("Editbtn"))).click();
    	Thread.sleep(5000);
    	WebElement branchname=driver.findElement(By.xpath(config.getProperty("ObjBranch")));
    	branchname.clear();
    	branchname.sendKeys(bname);
    	Thread.sleep(4000);
    	WebElement Add=driver.findElement(By.xpath(config.getProperty("ObjAddress")));
        Add.clear();
        Add.sendKeys(address1);     	
        Thread.sleep(4000);
    	WebElement Objzipcode=driver.findElement(By.xpath(config.getProperty("Objzip")));
        Objzipcode.clear();
        Objzipcode.sendKeys(zipcode);
    	Thread.sleep(4000);
    	driver.findElement(By.xpath(config.getProperty("Objupdatebtn"))).click();
    	String expectedalert="Branch updated";
    	String Actualalert=driver.switchTo().alert().getText();
    	driver.switchTo().alert().accept();
    	if(Actualalert.toLowerCase().contains(expectedalert.toLowerCase()))
    	{
    		Reporter.log("Branch updated successfully"+expectedalert+"       "+Actualalert,true);
    		return true;
    	}else
    	{
    		Reporter.log("Branch updation failed"+expectedalert+"       "+Actualalert,true);
            return false;
    	}

    }
    //method to logout
    public static boolean verifyLogout()throws Throwable
    {
    	driver.findElement(By.xpath(config.getProperty("Objlogout"))).click();
    	if(driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).isDisplayed())
    	{
    		Reporter.log("admin logout sucess",true);
    		return true;
    	}else
    	{
    		Reporter.log("Admin logout fail",true);
    		return false;
    	}
    	
    }
    public static String generateDate()
    {
    	Date date=new Date();
    	DateFormat df=new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
    	return df.format(date);
    	
    }
}
