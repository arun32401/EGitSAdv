package com.staples.saf.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import jxl.read.biff.BiffException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.staples.saf.base.BaseTest;
import com.staples.saf.bussinessComponents.BC001_SAdv_AddToCart;
import com.staples.saf.bussinessComponents.BC002_SAdv_CartIcon;
import com.staples.saf.exceptions.DataSheetException;
import com.staples.saf.exceptions.DatabaseConnectivityException;
import com.staples.saf.exceptions.InvalidBrowserException;
import com.staples.saf.pages.P001_SAdv_step_SAHomePage;
import com.staples.saf.pages.P002_SAdv_step_SALoginPage;
import com.staples.saf.pages.P003_SAdv_step_ShipToLocation;
import com.staples.saf.pages.P004_SAdv_step_ShopPage;
import com.staples.saf.pages.P007_SAdv_step_ManufacturerSearchResultsPage;
import com.staples.saf.pages.P008_SAdv_step_ProductComparisionPage;

public class ATC002_SAdv_AddManufacturerName extends BaseTest {
	
	static int count=0;
	public ATC002_SAdv_AddManufacturerName(){
		super();
}
		public ATC002_SAdv_AddManufacturerName(String testName,String browser, HashMap<String, String> mapDataSheet){

		super(testName,browser,mapDataSheet);
			}
	
	@Factory(dataProvider = "dataBase")
	public Object[] testCreator(HashMap<String, String> mapDataSheet) {		
//		return new Object[] { new ATC002_Staples_AddManufacturerName(this.getClass().getSimpleName(),mapDataSheet.get("Browser"), mapDataSheet) };
		return new Object[] { new ATC002_SAdv_AddManufacturerName(this.getClass().getSimpleName(),mapDataSheet.get("Browser"), mapDataSheet) };

	}

	@DataProvider(name="dataBase")
	public  Object[][] getTestDataFromExcel() throws BiffException, IOException, InvalidBrowserException, DataSheetException, DatabaseConnectivityException, SQLException {
		
//		return testDataProvider.getTestDataFromExcel(externalSheetPath, this.getClass().getSimpleName());
		return testDataProvider.getDatabaseData("registration_table", this.getClass().getSimpleName());
	}

	
	
	
  @Test
  public void AddManufacturerName() throws Exception {
	
	  P001_SAdv_step_SAHomePage homePage= new P001_SAdv_step_SAHomePage(getDriver());
	  P002_SAdv_step_SALoginPage loginPage=new P002_SAdv_step_SALoginPage(getDriver());
	  P003_SAdv_step_ShipToLocation shipToLocation=new P003_SAdv_step_ShipToLocation(getDriver());
	  P004_SAdv_step_ShopPage shopPage=new P004_SAdv_step_ShopPage(getDriver());
	  P007_SAdv_step_ManufacturerSearchResultsPage manufacturerSearchResultPage=new  P007_SAdv_step_ManufacturerSearchResultsPage(getDriver());
	  P008_SAdv_step_ProductComparisionPage productComparisonPage=new P008_SAdv_step_ProductComparisionPage(getDriver());
	  BC001_SAdv_AddToCart addToCart=new BC001_SAdv_AddToCart(getDriver());
	  BC002_SAdv_CartIcon cartIcon=new BC002_SAdv_CartIcon(getDriver());
	  homePage.launchBasePage();
	  Assert.assertTrue(homePage.hasPageLoaded());
	  Reporter.log("Launched Home page successfully");
	  homePage.clickLoginButton();
	  Reporter.log("Clicked on Login link");
	  loginPage.userLogin(getValue("CustomerId"),getValue("UserId"),getValue("Password"));
//	  loginPage.userLogin(mapDataSheet.get("CustomerId"),mapDataSheet.get("UserId"),mapDataSheet.get("Password"));
	  Reporter.log("Logged into the application using customer id"+getValue("CustomerId")+ "user id "+getValue("UserId")+" and password "+getValue("Password"));
	  shipToLocation.clickOnOk();
	  System.out.println("clicked ok");
	  Reporter.log("clicked on ok button");
	  shopPage.searchManufacturer(mapDataSheet.get("Manufacturer"));
	  manufacturerSearchResultPage.clickCheckBox1();
	  manufacturerSearchResultPage.clickCheckBox2();
	 // manufacturerSearchResultPage.handleAlert();
	  manufacturerSearchResultPage.clickcompareButton();
	  productComparisonPage.removeProduct();
	  productComparisonPage.selectMoreToCompare();
	  manufacturerSearchResultPage.clickCheckBox3();
	  manufacturerSearchResultPage.clickcompareButton();
	  productComparisonPage.addToCart();
	  cartIcon.cartIcon();

}
}
