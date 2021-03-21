package com.guru99.pages.pagemodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageModel extends BasePage{
	public LoginPageModel(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name = "uid")
	private WebElement userId;
	
	@FindBy(name = "password")
	private WebElement  userPassword;
	
	@FindBy(name = "btnLogin")
	private WebElement  loginButton;
	

	
	public void loginToApplication(String username, String password) {
		
		elementControl.setText(userId, username);
		elementControl.setText(userPassword, password);
		elementControl.clickElement(loginButton);
		
	}
}
