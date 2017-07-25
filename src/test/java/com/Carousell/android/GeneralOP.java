package com.Carousell.android;

import java.sql.Timestamp;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileInputStream;

import io.appium.java_client.AppiumDriver;

import com.Carousell.util.Log;

public class GeneralOP {

	public static int retry=0;

	public static void swipeUp(AppiumDriver driver, int duration) {
		Log.info("swipe up");

		try {
			Dimension size = driver.manage().window().getSize();
			int height = size.getHeight();
			int width = size.getWidth();
	
			//Log.info("h :"+height+" w : "+width);
		    driver.swipe(width/2, (int)height*4/5, width/2, (int)height*1/5 , duration);
		    waitForSecs(2);		
	    } catch(Exception e) {
			Log.info("--->issues in swipe up");
			takeScreenShot(driver);
		}
	}

	public static void swipeDown(AppiumDriver driver, int duration) {
		Log.info("swipe down");

		try {
			Dimension size = driver.manage().window().getSize();
			int height = size.getHeight();
			int width = size.getWidth();
	
			//Log.info("h :"+height+" w : "+width);
		    driver.swipe(width/2, (int)height*1/5 , width/2, (int)height*4/5, duration);
		    waitForSecs(2);
		} catch(Exception e) {
			Log.info("--->issues in swipe down");
			takeScreenShot(driver);
		}
	}

	public static void swipeLeft(AppiumDriver driver, int duration) {
		Log.info("swipe left");

		try {
			Dimension size = driver.manage().window().getSize();
			int height = size.getHeight();
			int width = size.getWidth();

			//Log.info("h :"+height+" w : "+width);
			driver.swipe((int)width*4/5, height/2, (int)width/10, height/2 , duration);
			waitForSecs(2);
		} catch(Exception e) {
			Log.info("--->issues in swipe left");
			takeScreenShot(driver);
		}
	}

	public static void swipeRight(AppiumDriver driver, int duration) {
		Log.info("swipe right");

		try {
			Dimension size = driver.manage().window().getSize();
			int height = size.getHeight();
			int width = size.getWidth();

			//Log.info("h :"+height+" w : "+width);
			driver.swipe((int)width/10, height/2, (int)width*4/5,height/2 , duration);
			waitForSecs(2);
		} catch(Exception e) {
			Log.info("--->issues in swipe right");
			takeScreenShot(driver);
		}
	}

	public static void waitForSecs(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			//e.printStackTrace();
			Log.infoTitle("Wait -- Fail. Please check!");
		}
	}

	public static String getFileName() {
		String filename=  getCurrentTimeStamp().replaceAll("[ .:-]", "_");
		return filename;
	}

	public static String getCurrentTimeStamp() {
		 java.util.Date date= new java.util.Date();

		 Timestamp ts = new Timestamp(date.getTime());
		 return ts.toString();
	}

	public static String takeScreenShot(AppiumDriver driver){
		try {
			Properties andauto = new Properties();
			FileInputStream andautoFis = new FileInputStream("androidauto.properties");
			andauto.load(andautoFis);

			File ssDir= new File("ScreenShots//AndroidTest");
			ssDir.mkdirs();
			String ssPath = ssDir.getAbsolutePath();

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere

			File newFile = new File(ssPath+"//"+getFileName()+".jpeg");
			Log.info("Screenshot at :"+newFile.getAbsolutePath());
			FileUtils.copyFile(scrFile,newFile );
			return newFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
