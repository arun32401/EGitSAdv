package com.staples.saf.bussinessComponents;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;
import com.staples.saf.pages.P004_SAdv_step_ShopPage;

public class BC002_SAdv_CartIcon extends BasePage{
	
	public BC002_SAdv_CartIcon(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
   public void cartIcon() 
   {
	P004_SAdv_step_ShopPage shoppage=new P004_SAdv_step_ShopPage(getDriver());
	
	shoppage.clickCartICon();

}

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
}
