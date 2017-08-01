package com.Carousell.android;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class ElementOP {
	
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
