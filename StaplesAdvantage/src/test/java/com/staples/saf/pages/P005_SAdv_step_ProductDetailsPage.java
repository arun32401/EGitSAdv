package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P005_SAdv_step_ProductDetailsPage extends BasePage{

	
	public P005_SAdv_step_ProductDetailsPage(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By img = By.id("enlImage");
	private By imgLargerLink = By.xpath("//p[@id='enlImageLink']/a");
	private By inkTonerLink=By.id("esearch-ab-testing-myprinter");
	private By writeReviewLink=By.xpath("//a[text()='Write a review']'");
	private By emailProductLink=By.xpath("//span[text()='Email this product']");
	private By checkDeliveryDateLink=By.xpath(".//*[@id='maindetailitem_id']/div[3]/ul/li[3]/a");
	private By addToListLink=By.xpath("//a[@class='canbedisabled']");
	private By close=By.xpath(".//*[@id='prodImageOvly']/div/div[1]/a/img");
	private By descriptionTab=By.id("tab0");
	private By specificationsTab=By.id("tab1");
	private By reviewsTab=By.id("tab2");
	private By addToCartButton=By.xpath("//a[@class='button addtocart mainproduct']/span");
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
	
	//This function is to look the larger view of image
	public void viewImage() 
	{
//	  mouseOver(img);
	  //Thread.sleep(3000); 
	  click(imgLargerLink);
	  click(close);
		
	}
	//This function is to view description,specification an reviews
	public void productDetails()
	{
		click(descriptionTab);
		click(specificationsTab);
		click(reviewsTab);
	}
	
	public void addToCartButton(){
		click(addToCartButton);
	}
}
