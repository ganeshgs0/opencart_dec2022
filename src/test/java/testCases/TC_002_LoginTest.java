package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
    @Test(groups= {"Sanity","Regression"})
	public void loginPage() {
    	logger.info("*****started TC_002_LoginTest *****");
	
    try {
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		LoginPage login=new LoginPage(driver);
		login.setUsername(p.getProperty("username"));
		login.setPassword(p.getProperty("password"));
		login.clickLogin();
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExist();
		Assert.assertEquals(targetpage, true);
	} catch (Exception e) {
		Assert.fail();
	}
	
	
	logger.info("*****finished TC_002_LoginTest *****");
	}
}
