package com.Carousell.android;

import com.Carousell.util.Log;

import java.util.Properties;

import com.Carousell.android.ElementOP;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class OfferOP {

	public static void choose1st(AppiumDriver driver) throws Exception {

		try {
			//Tap the first product
			if (!ElementOP.isElementPresent(driver, MobileBy.id("com.thecarousell.Carousell:id/pic_product"),2)) {
				Log.info("--->Cannot find any product. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to find product");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/pic_product").click();
			}

			//Check guide
			if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Make an offer to buy this item!']"),2)) {
				driver.findElementByXPath("//android.widget.TextView[@text='OK, Got it!']").click();
			}
			if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Got questions? Ask the seller!']"),2)) {
				driver.findElementByXPath("//android.widget.TextView[@text='OK, Got it!']").click();
			}

			Log.info("first listing is " + driver.findElementById("com.thecarousell.Carousell:id/text_product_title").getText());
			Log.infoTitle("Choose the first listing  --Pass");
		} catch(Exception e) {
			Log.infoTitle("--->Choose the first listing Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Choose the first listing fails.");
		}
	}

	public static void makeOffer(AppiumDriver driver, Properties andauto) throws Exception {

		try {
			//Tap buy button
			//If it's bought before, cancel it
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Buy Now']"),2)) {
				if (ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='View Offer']"),2)) {
					Log.info("Bought. Cancel it.");
					driver.findElementById("com.thecarousell.Carousell:id/button_buy").click();
					driver.findElementById("com.thecarousell.Carousell:id/button_chat_left").click();
					driver.findElementByXPath("//android.widget.Button[@text='Yes']").click();
					driver.findElementById("com.thecarousell.Carousell:id/button_chat_offer").click();					
				}
				else {
					Log.info("--->Cannot find buy button. Please check");
					GeneralOP.takeScreenShot(driver);
					throw new AssertionError("--->Fail to find buy button");
				}
			}
			else {
				driver.findElementByXPath("//android.widget.TextView[@text='Buy Now']").click();
			}

			//Check offer page
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Make an offer to buy this item!']"),2)) {
				Log.info("--->Not into offer page. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to go into offer page");
			}
			else {
				driver.findElementById("com.thecarousell.Carousell:id/text_offer").clear();
				driver.findElementById("com.thecarousell.Carousell:id/text_offer").sendKeys("10");
				driver.findElementById("com.thecarousell.Carousell:id/action_submit").click();				
			}

			//Check confirm dialog
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='Confirm offer?']"),2)) {
				Log.info("--->Confirm dialog is not opened. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to open confirm dialog");
			}
			else {
				driver.findElementByXPath("//android.widget.Button[@text='Yes']").click();				
			}

			//Check result
			if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.TextView[@text='MADE AN OFFER\nNT$10']"),2)) {
				Log.info("--->Not into result page. Please check");
				GeneralOP.takeScreenShot(driver);
				throw new AssertionError("--->Fail to make offer");
			}
			else {
				Log.info("make and offer on " + driver.findElementById("com.thecarousell.Carousell:id/text_product_name").getText());
				driver.findElementById("com.thecarousell.Carousell:id/button_guide_close").click();
				driver.navigate().back();
				driver.navigate().back();
				driver.navigate().back();
			}

			Log.infoTitle("Make an offer  --Pass");
		} catch(Exception e) {
			Log.infoTitle("--->Make an offer Fails. Please check!");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Make an offer fails.");
		}
	}

}