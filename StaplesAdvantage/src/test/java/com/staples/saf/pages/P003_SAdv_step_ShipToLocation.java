package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P003_SAdv_step_ShipToLocation extends BasePage {
	
	public P003_SAdv_step_ShipToLocation(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	private By okButton = By.xpath("//div[@class='centered_button_relative']");
	private By okButton1=By.xpath("//*[@id='shiptolocation']/div/div[1]/a/span[1]"); 
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

	
//This function Clicks on OK Button 
	public void clickOnOk() throws Exception
	{  
		click(okButton);
		if(isTitleAsExpected("Ship-To Location"))
		{

			click(okButton1);
		}

		else
		{
			System.out.println("Next page is loaded sucessfully");

		} 

	}
}