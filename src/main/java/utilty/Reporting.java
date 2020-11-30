package utilty;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Reporting extends Utility{
	
	public ExtentHtmlReporter extentHtmlReport;
	public ExtentReports report;
	public ExtentTest logger;
	

	

/*
 * Method to Initialize Extent Reports
 * Created by : Benarji Enamandala
 * 
 */
public void extentReportInit() {
	extentHtmlReport=new ExtentHtmlReporter(new File("/Reports/Result.html")); 
	report=new ExtentReports();
	report.attachReporter(extentHtmlReport);
	
}

/*
 * Method to report Passed Reports Statements using Extent reports
 * Attributes: String Message generated when Event passed
 * Created by : Benarji Enamandala
 * 
 */
public void reportpass(String message) {
	String object=new Reporting().captureScreen();
	try {
		this.logger.pass(message,MediaEntityBuilder.createScreenCaptureFromPath(object).build());
	
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
}
/*
 * Method to report Failed Executions Using Extent Reports
 * Attributes: String Message generated when Failed
 * Created by : Benarji Enamandala
 * 
 */
public void reportfail(String message) {
	String object=new Reporting().captureScreen();
	try {
		this.logger.fail(message,MediaEntityBuilder.createScreenCaptureFromPath(object).build());
	
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
}
/*
 * Method to Capture Screenshots
 * Created by : Benarji Enamandala
 * 
 */

public String captureScreen() {
	
	String  Destination=null ;
	File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileInputStream fis=null;
	try {
		fis=new FileInputStream(srcfile);
		byte[] Bytes=new byte[(int)srcfile.length()];
		fis.read(Bytes);
		// capturing screenshot as output type file from getScreenshotAs method by up casting the AndroidDriver reference to TakeScreenshot Interface
		Destination="/Reports/Screenshots"+System.currentTimeMillis()+".png";
		// Coping screenshot file into Screenshots folder with Filename from  argument along with current time appended to file name
	File  Destfile=new File(Destination);
	FileUtils.copyFile(srcfile,Destfile);
				 
		
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
	
	return Destination;
	
}
}
