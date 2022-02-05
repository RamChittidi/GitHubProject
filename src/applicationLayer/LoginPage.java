package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define REPOSITORY
	@FindBy(name="txtUsername")
	WebElement Username;
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='btnLogin']")
	WebElement Loginbtn;
	//method for login
	public void verifyLogin(String Username,String Password)
	{
		Actions ac=new Actions(driver);
		this.Username.sendKeys(Username);
		this.Password.sendKeys(Password);
		ac.moveToElement(Loginbtn).click().perform();
	}
}
