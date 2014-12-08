package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P008_SAdv_step_ProductComparisionPage extends BasePage {

	public P008_SAdv_step_ProductComparisionPage(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By removeSelectedText=By.xpath("//span[text()='Remove Selected']");
	private By checkBox1=By.id("remove_1");
	private By selectMoreToCompareText=By.xpath("//span[text()='Select More to Compare']");
	private By addToCart=By.xpath(".//*[@id='container']/div/div/div[2]/div[2]/table/tbody/tr[7]/td[2]/form/a[1]/span[1]");
//	private By addToCart=By.xpath("//a[@class='button btn addtocart quickshoppad']/span");
	//private By 

	public boolean hasPageLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPageUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	//this function is to select product and remove
			public void removeProduct()
			{
				click(checkBox1);
				click(removeSelectedText);
			}

			//this function is to select more product to compare
			public void selectMoreToCompare()
			{
				click(selectMoreToCompareText);
			}

			//this function is to add product to cart
			public void addToCart()
			{
				click(addToCart);
			}




}