package testCases;


import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TestAccountRegistration extends BaseClass {

        @Test(groups= {"Master","Regression"})
	public void accountRegistration() {

        	logger.trace("tracing logs");
        	logger.debug("debug logs");
		try {
        	logger.info("********starting TestAccountRegistration**********");			
			HomePage hp = new HomePage(driver);
			logger.info("clicking on myAccount link");
			hp.clickMyaccount();
			logger.info("clicking on register link");
			hp.clickRegister();

			AccountRegistrationPage reg = new AccountRegistrationPage(driver);
			logger.info("entering customer registration details");
			reg.setFirstname(randomAlphabetic());
			reg.setlastname(randomAlphabetic());
			reg.setEmail(randomAlphabetic() + "@gmail.com");
			reg.setTelephone(randomNumeric());

			String pass = randomAlphanumeric();
			reg.setPassword(pass);
			reg.setConfirmpassword(pass);
			reg.clickPrivacy();
			
			 logger.info("clicking coninue button");
			reg.clickContinue();
			String str = reg.getConfirmMsg();
			if(str.equals("Your Account Has Been Created!")) {
				logger.info("customer registration is successful");
				Assert.assertTrue(false);					
			}
			else {			
				logger.warn("customer registration not successful");
				logger.error("test failed");
				Assert.fail();
			}
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
    
   
}
