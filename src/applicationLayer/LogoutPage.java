package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
	WebDriver driver;
	public LogoutPage(WebDriver driver)
	{
		this.driver=driver;
	}
    @FindBy(xpath="//a[@id='welcome']")
    WebElement clickWelcome;
    @FindBy(xpath="//a[contains(text(),'Logout')]")
    WebElement clickLogout;
    public void verifyLogout()throws Throwable
    {
    	Actions ac=new Actions(driver);
    	ac.moveToElement(clickWelcome).click().perform();
    	Thread.sleep(5000);
    	ac.moveToElement(clickLogout).click().perform();
    }
}
