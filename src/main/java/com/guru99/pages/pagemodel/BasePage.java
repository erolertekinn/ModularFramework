package com.guru99.pages.pagemodel;

import org.openqa.selenium.WebDriver;

import com.guru99.pages.page.ElementControl;

public class BasePage {
	WebDriver driver;
	
	public ElementControl elementControl;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		elementControl = new ElementControl(driver);
	}
}
