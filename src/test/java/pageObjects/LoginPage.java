package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement txtUsername;

	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setUsername(String uname) {
		txtUsername.sendKeys(uname);
	}
	
	public void setPassword(String pword) {
		txtPassword.sendKeys(pword);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
}
