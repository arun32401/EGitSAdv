package com.staples.saf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.base.BasePage;

public class P007_SAdv_step_ManufacturerSearchResultsPage extends BasePage {

	public P007_SAdv_step_ManufacturerSearchResultsPage(RemoteWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private By itemsPerPageDropdown=By.id("itemsPerPage");
	private By compareButton=By.cssSelector("span.left > span.arrow");
	private By compareButton2=By.cssSelector("div.sortbybox.sortbyboxbottom > #gallerynav > a.button.compare > span.left > span.arrow");
	private By checkBox1=By.id("compare_1");
	private By checkBox2=By.id("compare_2");
	private By checkBox3=By.id("compare_5");
	private By handleAlert=By.cssSelector("#showErrorBtn > span.left");



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
	public void clickCheckBox1()
	{
			click(checkBox1);
	}

	public void clickCheckBox2()
	{
		click(checkBox2);

	}
	public void clickCheckBox3()
	{
		click(checkBox2);

	}


	public void clickcompareButton()
	{ 

	click(compareButton);

	}
	public void clickcompareButton2()
	{ 

	click(compareButton2);

	}
	public void handleAlert()
	{
		
		acceptAlert();
	}


} 