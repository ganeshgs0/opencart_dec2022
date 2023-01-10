package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDriven_Test extends BaseClass {

	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class)
	public void loginPage(String uname, String pwd, String exp) {
		logger.info("*****started TC_003_LoginTest *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();
			LoginPage login = new LoginPage(driver);
			login.setUsername(uname);
			login.setPassword(pwd);
			login.clickLogin();
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExist();

			if (exp.equals("Valid")) {
				if (targetpage == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equals("InValid")) {
				if (targetpage == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*****finished TC_003_LoginTest *****");
	}

}
