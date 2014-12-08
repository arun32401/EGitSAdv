package com.staples.saf.pages;

import com.staples.saf.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class P002_SAdv_step_SALoginPage extends BasePage {

	    public P002_SAdv_step_SALoginPage(RemoteWebDriver driver) 
	    {
		super(driver);
		 }

		private By customerId = By.id("MALLLOGON_ROW_COMPANY1");
		private By userId = By.id("MALLLOGON_ROW_USER1");
		private By password = By.id("MALLLOGON_ROW_PASSWORD1");
		private By loginButton = By.xpath("//span[text()='Log in']");
		//private By learn = By.id("myform:loginId");
		
		
		@Override
		public boolean hasPageLoaded() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getPageUrl() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * This function performs User Login
		 * @param userId - userId for Login
		 * @param userPassword - password for Login
		 */
		public void userLogin(String customerID,String userID,String userPassword)
		{
			type(customerId, customerID);
			type(userId, userID);
			type(password, userPassword);
			click(loginButton);
			reLogin(userPassword);
		}
		
		public void reLogin(String userPassword)
		{
			if(isTitleAsExpected("Staples Advantage"))
			{
				type(password, userPassword);
				click(loginButton);
			}
				
			else
			{
				System.out.println("login sucess");
				
			}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		/**
		 * This function is to click on the Home Link in Login page
		 */
		/*public void clickHome()
		{
			click(learn);
		}*/
		
		
		
		
	}
}


