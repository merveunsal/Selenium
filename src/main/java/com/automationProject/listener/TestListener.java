package com.automationProject.listener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationProject.base.BaseTest;
import com.automationProject.util.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

 
 
public class TestListener extends BaseTest implements ITestListener {
	 
    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    String USER_DIR = System.getProperty("user.dir");
    public static List<String> list = new ArrayList<String>();
    
	public static String fileName = null;
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }
 
    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }
 
    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }
 
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        try {
			 String dest = takeScreenshot(result.getName());
			 logTest.info("ss dest path: "+ dest);
			 logTest.info("result scenerio name : "+ result.getName());
			 list.add(dest);	
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}	
        test.get().fail(result.getThrowable());
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
    
public synchronized String takeScreenshot(String methodName) throws IOException {
    	
        String path = USER_DIR + "/TestReport/";

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        String directory = path + "\\ErrorScreenshots\\";
        	        	       
        fileName = methodName + ".jpg";

        File f = new File(directory);

        if (!f.isDirectory()) {
            f.mkdir();
            logTest.info("Folder was created successfully: " + directory);
        }

        String destination = directory + fileName;
       
        Screenshot fpScreenshot = new AShot().takeScreenshot(getDriver());
        ImageIO.write(fpScreenshot.getImage(), "PNG", new File(destination));
        logTest.info("destination is " + destination);
        
        return fileName;
    }
}