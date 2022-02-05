package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EmpPage {
	WebDriver driver;
	public void EmpPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="//b[normalize-space()='PIM']")
	WebElement clickpim;
	@FindBy(name="btnAdd")
	WebElement clickAdd;
	@FindBy(xpath="//input[@id='firstName']")
	WebElement fname;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement lname;
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement clickSave;
	public void verifyEmp(String fname,String lname)throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(clickpim).click().build().perform();
		//this.clickpim.clear();
		Thread.sleep(5000);
		ac.moveToElement(clickAdd).click().perform();
		Thread.sleep(5000);
		this.fname.sendKeys(fname);
		this.lname.sendKeys(lname);
		ac.moveToElement(clickSave).click().perform();
		Thread.sleep(5000);
	}

}
