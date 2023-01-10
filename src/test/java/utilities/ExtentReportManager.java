package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repname;
	public void onStart(ITestContext testContext) {
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  repname="Test-Report-"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repname);
		sparkReporter.config().setDocumentTitle("opencart automation testing");
		sparkReporter.config().setReportName("opencart functional testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("application", "opencart");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("submodule", "customers");
		extent.setSystemInfo("os", System.getProperty("os.name"));
		extent.setSystemInfo("user", System.getProperty("user.name"));
		extent.setSystemInfo("environment", "qa");
				}
	
	public void onTestSuccess(ITestResult result ) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "test passed");
	}

	public void onTestFailure(ITestResult result ) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "test failed");
		test.log(Status.FAIL,result.getThrowable().getMessage()  );
		try {
		String imgpath=new BaseClass().screenCapture(result.getName());
		test.addScreenCaptureFromPath(imgpath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result ) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "test skiped");
		test.log(Status.SKIP,result.getThrowable().getMessage()  );
	}
	
	public void onFinish(ITestContext testcontext) {
		extent.flush();
		
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create the email message 
		 * ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); 
		 * email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
		 * email.setSSLOnConnect(true);
		 * email.setFrom("pavanoltraining@gmail.com"); //Sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
		 * email.attach(url, "extent report", "please check report..."); 
		 * email.send(); // send the email 
		 * }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}
}
