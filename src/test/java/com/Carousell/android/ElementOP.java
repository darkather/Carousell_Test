package com.Carousell.android;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;

public class ElementOP {

	public static WebElement waitForElementPresent(AppiumDriver driver, final By by, int timeOutInSeconds) {
		WebElement element;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 80);
			element = wait.until(ExpectedConditions.elementToBeClickable(by));

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //reset implicitlyWait

			return element;
		} catch (Exception e) {
			Log.info("--->WaitforElementPresent fail. pls check!");
		}
		return null;
	}
	
	public static Boolean isElementPresent(AppiumDriver driver, final By by, int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //reset implicitlyWait

			return true;
		} catch (Exception e) {
			return false;
		}
	}
 
	public static Boolean isElementClickable(AppiumDriver driver, final By by, int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(by));

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //reset implicitlyWait

			return true;
		} catch (Exception e) {
			return false;
		} 
	}
}
