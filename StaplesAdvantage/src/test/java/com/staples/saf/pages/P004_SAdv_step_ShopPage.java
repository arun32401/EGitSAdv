package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P004_SAdv_step_ShopPage extends BasePage {
	
	public P004_SAdv_step_ShopPage(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By searchTextbox = By.id("searchinput");
	private By goButton =By.id("searchBtn");
	private By myInk =By.id("esearch-ab-testing-myprinter");
	private By searchButton =By.xpath(".//*[@id='globalsearchfield']/fieldset/span[3]/span[1]/input");
	private By myInkGPM =By.xpath(".//*[@id='gpm']/li[2]/a");
	private By searchTextBoxGPM=By.xpath(".//*[@id='globalsearchfield']/fieldset/span[1]");
//	private By searchTextBoxGPM=By.xpath(".//*ul[@id='gpm']/li/div/ul/li/span/form/fieldset/span/input[@id='searchinputitf']");
	//private By cartIconLink=By.id("totalitems");
	private By cartICon=By.xpath(".//*[@id='cart']/form/a");
	
	
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
		
	//This function is to search corresponding SKU item
	public void searchSKU(String SkuName)
	{
		type(searchTextbox, SkuName);
//		type(searchTextbox, "#899992");
	    click(goButton);
	}
	public void searchSKUGPM(String SkuName)
	{
		mouseOver(myInkGPM);
		type(searchTextBoxGPM, SkuName);
		click(searchButton);
		//click(myInkIcon);
		
	}
	//This function is to search corresponding SKU item
		public void searchManufacturer(String Manufacturer )
		{
			type(searchTextbox, Manufacturer);
		    click(goButton);
		}
	
     public void clickCartICon()
     {
    	 click(cartICon);
     }
}
