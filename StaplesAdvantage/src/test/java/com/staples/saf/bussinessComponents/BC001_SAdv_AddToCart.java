package com.staples.saf.bussinessComponents;

import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;
import com.staples.saf.pages.P005_SAdv_step_ProductDetailsPage;

public class BC001_SAdv_AddToCart extends BasePage {
	
	public BC001_SAdv_AddToCart(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasPageLoaded() {
		// TODO Auto-generated method stub
		return false;	
	}

	@Override
	public String getPageUrl() {
		// TODO Auto-generated method stub
		return null ;
	}
	public void AddToCart()
	{
		
	
	P005_SAdv_step_ProductDetailsPage productDetailsPage1=new P005_SAdv_step_ProductDetailsPage(getDriver());
  
	productDetailsPage1.addToCartButton();
}
}
