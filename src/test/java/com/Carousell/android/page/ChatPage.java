package com.Carousell.android.page;

import com.Carousell.android.ElementOP;
import com.Carousell.android.GeneralOP;
import com.Carousell.util.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/** Page object for the landing page **/
public abstract class ChatPage {

	/** Verify the chat page has loaded **/
	public static void loaded(AppiumDriver driver) {
		if (!ElementOP.isElementPresent(driver, MobileBy.xpath("//android.widget.EditText[@text='Type your message here']"),2)) {
			Log.info("--->Not into chat page. Please check");
			GeneralOP.takeScreenShot(driver);
			//Log.info(e.getMessage());
			throw new AssertionError("--->Fail to go into chat page");
		}
	}
}