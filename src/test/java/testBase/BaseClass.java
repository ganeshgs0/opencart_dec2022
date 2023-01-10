package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	//public ResourceBundle rb;
	 public Properties p;
    
	@BeforeClass(groups= {"Sanity","Master","Regression"})    
    @Parameters({"browser"})
    public void setup(String br) throws Exception {
    	logger=LogManager.getLogger(this.getClass());
    	//approach1
    	// rb=ResourceBundle.getBundle("config");
    	
    	//approach2
    	FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
    	 p=new Properties();
    	 p.load(file);
    	if(br.equals("chrome"))
    	{
		driver = new ChromeDriver();
    	}
    	else if(br.equals("edge"))
    	{
    		driver = new EdgeDriver();
    	}
    	else {
    		driver=new FirefoxDriver();
    	}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get(rb.getString("appURL"));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
    
    @AfterClass(groups= {"Sanity","Master","Regression"})
   	public void tearDown() {
   		driver.quit();
   	}

   	public String randomNumeric() {
   		String num = RandomStringUtils.randomNumeric(10);
   		return num;
   	}

   	public String randomAlphabetic() {
   		String str = RandomStringUtils.randomAlphabetic(5);
   		return str;
   	}

   	public String randomAlphanumeric() {
   		String num = RandomStringUtils.randomNumeric(3);
   		String str = RandomStringUtils.randomAlphabetic(3);
   		return (str + "@" + num);
   	}
   	
   	public String screenCapture(String tname) {
   		String  timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
   		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
   	    File source=takesScreenshot.getScreenshotAs(OutputType.FILE);
   	    String destination=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
   	    File target=new File(destination);
   	    
   	    try {
			FileUtils.copyFile(source, target);		
		} catch (IOException e) {			
			e.getMessage();
		}  	     
   	    return destination;
   	}
	
}
