package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	//elements
	@FindBy(name="firstname")
	WebElement txtFirstname;
	
	@FindBy(name="lastname")
	WebElement txtlastname;
	
	@FindBy(name="email")
	WebElement txtEmail;
	
	@FindBy(name="telephone")
	WebElement txtTelephone;

	@FindBy(name="password")
	WebElement txtPassword;

	@FindBy(name="confirm")
	WebElement txtConfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//action methods
	public void setFirstname(String firstname) {
		txtFirstname.sendKeys(firstname);
	}
	
	public void setlastname(String lastname) {
		txtlastname.sendKeys(lastname);
	}
	
	public void setEmail(String Email) {
		txtEmail.sendKeys(Email);
	}
	
	public void setTelephone(String Telephone) {
		txtTelephone.sendKeys(Telephone);
	}
	
	public void setPassword(String Password) {
		txtPassword.sendKeys(Password);
	}

	
	public void setConfirmpassword(String Confirmpassword) {
		txtConfirmpassword.sendKeys(Confirmpassword);
	}
	
	public void clickPrivacy() {
		chkPrivacy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();;
	}
	
	
	public String getConfirmMsg() {
		try {
		String str=msgConfirmation.getText();
		return 	str;
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		
	}
		
	
}
