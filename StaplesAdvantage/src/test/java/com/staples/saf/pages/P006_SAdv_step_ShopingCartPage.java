package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P006_SAdv_step_ShopingCartPage extends BasePage {
	
	public P006_SAdv_step_ShopingCartPage(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By continueShoppingButton=By.xpath(".//*[@id='cart_header_buttons']/a[3]/span[1]");
	private By updateCartButton=By.xpath(".//*[@id='cart_header_buttons']/a[2]/span[1]");
	private By nextButton=By.xpath(".//*[@id='cart_header_buttons']/a[1]/span[1]");
	//private By checkoutButton=By.id("chk_href");
	private By orderTotalIcon=By.id("id=cartView");
	private By yourShoppingCartText=By.xpath(".//*[@id='cart_header']/div/div[1]/div/span[1]");
	private By viewAllDeliveryDatesLink=By.xpath(".//*[@id='cart_detail']/div[3]/div/div[2]/div[3]/div[3]/a/span[1]");
	private By sortByDropDown=By.xpath("//select[@class='sortby']");
	
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
		
	public void shoppingCart()
		{
			
		}
		
	}


