package utilty;

import java.io.File;
import java.io.FileInputStream;

import java.net.URL;

import java.util.Properties;
import java.util.Random;


import org.openqa.selenium.By;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.appium.java_client.MobileBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;



public class Utility{
	Properties prop;
	public DesiredCapabilities cap;
	public static AndroidDriver<AndroidElement> driver;
	
public Utility() {
	prop=new Properties();
	cap=new DesiredCapabilities();
	
}
	

/*
 * Method to initialize Driver Capabilities and Android Driver 
 * Attributes: report:Reporing object for Generating html reports
 * Created by : Benarji Enamandala
 * 
 */
public void driverIni(Reporting report) {
	try {
		
	
	prop=propFile(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\config.properties");
	System.out.println();
	File file= new File(System.getProperty("user.dir")+prop.getProperty("ApkPath"));
	File filename=new File(file,prop.getProperty("AmazonApp"));
	cap.setCapability(MobileCapabilityType.DEVICE_NAME,prop.getProperty("device"));
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
	cap.setCapability(MobileCapabilityType.APP, filename.getAbsolutePath());
	driver =new AndroidDriver<AndroidElement>(new URL(prop.getProperty("url")),cap);
	report.reportpass("driver initialized");
	}
	catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false,e.getMessage());
		// TODO: handle exception
	}
	
	
	
	
	
}

/*
 * Method to Load Properties file
 * Attributes: path of Configuration file as String
 * Created by : Benarji Enamandala
 * 
 */
public Properties propFile(String path) {
	
	try {
		
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Assert.assertTrue(false,e.getMessage());
	}
	return prop;
	
}

/*
 * Reusable Method for Clicking an Element
 * Attributes: Reporing object for Generating html reports
 * 				elementtype: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * Created by : Benarji Enamandala
 * 
 */
public void clickElement(String elementType,String identifier,Reporting report) {
	try

	{
		if(elementType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(identifier)).click();
			report.reportpass("element clicked");
			}
		else if(elementType.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(identifier)).click();
		report.reportpass("element clicked");

		}
	}
	catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		
	}
}
/*
 * Reusable Method to wait for  an Element to bee Clickable
 * Attributes: Reporing object for Generating html reports
 * 				elementtype: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * Created by : Benarji Enamandala
 * 
 */
public void waitForElementToBeClickable(String elementType,String identifier,Reporting report)
{
	try
	{
		WebDriverWait wait=new WebDriverWait(driver, 90);
		if(elementType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(identifier)));
			report.reportpass("Identifier is Clicked");
		}
		else if(elementType.equalsIgnoreCase("xpath"))
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(identifier)));
		report.reportpass("Identifier is Clicked");
	}
	catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
	}
}

/*
 * Reusable Method for Entering text into feilds
 * Attributes: Reporing object for Generating html reports
 * 				element: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * 				value : user sent data into field
 * Created by : Benarji Enamandala
 * 
 */

public void sendkeys(String element,String identifier,String value,Reporting report) {
	try {
	if(element.equalsIgnoreCase("id")) {
		driver.findElementById(identifier).sendKeys(value);
	report.reportpass("Sucessfully identifier passed");
	}
	else if(element.equalsIgnoreCase("xpath"))
		driver.findElement(By.xpath(identifier)).sendKeys(value);
	report.reportpass("Sucessfully identifier passed");
}catch (Exception e) {
	e.printStackTrace();
	report.reportfail(e.getMessage());
	Assert.assertTrue(false, e.getMessage());
	// TODO: handle exception
}
	}

/*
 * Reusable Method for Clicking Enter From Mobile KeyBoard
 * Attributes: Reporing object for Generating html reports
 * 				
 * Created by : Benarji Enamandala
 * 
 */

public void enter(Reporting report) {
	try {
	driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	report.reportpass("Enter Key Pressed");
	}
	catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		// TODO: handle exception
	}
	}


/*
 * Reusable Method for Clicking back Button from Android Mobile
 * Attributes: Reporing object for Generating html reports
 * Created by : Benarji Enamandala
 * 
 */
public void backButton(Reporting report) {
	try {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		report.reportpass("back button Pressed");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.reportfail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
			// TODO: handle exception
		}
}
/*
 * Reusable Method for Random selection of Element 
 * Attributes: Reporing object for Generating html reports
 * 				ele: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * Created by : Benarji Enamandala
 * 
 */
public void randSelection(String ele,String identifier,Reporting report) {
	try
	{
		int size=0;
		Random random = new Random();
		if(ele.equalsIgnoreCase("id"))
		{
			size=driver.findElements(By.id(identifier)).size();
			int num=random.nextInt(size);

			driver.findElements(By.id(identifier)).get(num).click();
		
			report.reportpass("selected Element");
		}
		else if(ele.equalsIgnoreCase("xpath"))
		{
			size=driver.findElements(By.xpath(identifier)).size();
			int num=random.nextInt(size);
			driver.findElements(By.xpath(identifier)).get(num).click();
			report.reportpass("selected Element");
		}
	}catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		// TODO: handle exception
	}
}
public void Scroll(String text,Reporting report) {
	try {
	
    driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
    		+ "scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));

	report.reportpass("view Scrolled");
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
	}
	}
/*
 * Reusable Method for Getting text from Element
 * Attributes: Reporing object for Generating html reports
 * 				id: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * Created by : Benarji Enamandala
 * 
 */
public String getText(String id,String identifier,Reporting report) {
	
	String text=null;
	try {
	if(id.equalsIgnoreCase("id")) {
		 text=driver.findElement(By.id(identifier)).getText();
		 report.reportpass("Extracted Text from element");
	}
	else if(id.equalsIgnoreCase("xpath")) {
		text=driver.findElement(By.xpath(identifier)).getText();
		report.reportpass("Extracted Text from element");
	
	}
	}
	catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		// TODO: handle exception
	}
	return text;
}
/*
 * Reusable Method for verifiying Text  is Same as Expected
 * Attributes: Reporing object for Generating html reports
 * 				Actual: string of actual text
 * 				Expected : String of expected text
 * Created by : Benarji Enamandala
 * 
 */
public boolean verifyText(String Actual,String Expected,Reporting report) {
	boolean action=true;
	try {
		
	
	if (Actual.equals(Expected)){
			action= true;
		report.reportpass("String Matched");
		Assert.assertTrue(Actual.equalsIgnoreCase(Expected));
	}
	else {
		action=false;
		report.reportfail("String not matched");
	}
	
	}catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		// TODO: handle exception
	}
	return action;
}
/*
 * Reusable Method for verifying Element is Displayed 
 * Attributes: Reporing object for Generating html reports
 * 				id: "id" or "xpath" as User input
 * 				identifier : id or xpath of element
 * Created by : Benarji Enamandala
 * 
 */
public boolean verifyTextPresent(String id,String identifier,Reporting report) {
	boolean action=true;
	try {
	
	
	if(id.equalsIgnoreCase("id")) {
		if (driver.findElement(By.id(identifier)).isDisplayed()) {
		action= true;
		report.reportpass("String present");
		}
		else {
			action= false;
			report.reportfail("String not present");
		}
		
}else if(id.equalsIgnoreCase("xpath")) {
	if(driver.findElement(By.id(identifier)).isDisplayed()) {
		action=true;
		report.reportpass("String present");
		}
	
		else {
			action= false;
			report.reportfail("String not present");
		}
	}}catch (Exception e) {
		e.printStackTrace();
		report.reportfail(e.getMessage());
		Assert.assertTrue(false, e.getMessage());
		
		// TODO: handle exception
	}
	return action;
	
}




}

