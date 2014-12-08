package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P001_SAdv_step_SAHomePage extends BasePage {

	
		public P001_SAdv_step_SAHomePage(RemoteWebDriver driver)
		{
			super(driver);
		}


		private By loginButton = By.xpath("//div[@id='login']/a");
		//private By shop = By.xpath(".//*[@id='sign_in_out']/a[1]");
		
		
		
		@Override
		public boolean hasPageLoaded() {
			// TODO Auto-generated method stub
			return isTitleAsExpected("Business Supplies | Staples Advantage");	
		}

		@Override
		public String getPageUrl() {
			// TODO Auto-generated method stub
			return null ;
		}

		/**
		 * This function is to load the base URL
		 */
		public void launchBasePage(){
			
			launchBaseURL();
		}
		 
		/**
		 * This function is to handle the ModelDialogBox present
		 */
		public void handleAlert()
		{
			acceptAlert();
		}
		
		
		/**
		 * This function is to click on the link for User Login
		 */
		public void clickLoginButton()
		{
			click(loginButton);
		}
		
		
		/**
		 * This function is to click on the Home Link after Login
		 */
		/*public void clickShop()
		{
			click(shop);
		}*/
		
		
		
		
	}


