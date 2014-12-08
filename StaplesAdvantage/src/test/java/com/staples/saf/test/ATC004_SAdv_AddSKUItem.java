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

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

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
import com.staples.saf.pages.P005_SAdv_step_ProductDetailsPage;
import com.staples.saf.pages.P006_SAdv_step_ShopingCartPage;
import com.staples.saf.utilities.TestLinkConnection;


public class ATC004_SAdv_AddSKUItem extends BaseTest {
	
	static int count=0;
	String result = null;
    String exception = null;
    String notes = null;
    TestLinkConnection testLinkConnector = new TestLinkConnection();
	
	public ATC004_SAdv_AddSKUItem(){

		super();
		
	}
	
	public ATC004_SAdv_AddSKUItem(String testName,String browser, HashMap<String, String> mapDataSheet){

		super(testName,browser,mapDataSheet);
		
	}
	
	@Factory(dataProvider = "dataBase")
	public Object[] testCreator(HashMap<String, String> mapDataSheet) {		
//		return new Object[] { new ATC001_Staples_AddSKUItem(this.getClass().getSimpleName(),mapDataSheet.get("Browser"), mapDataSheet) };
		return new Object[] { new ATC004_SAdv_AddSKUItem(this.getClass().getSimpleName(),mapDataSheet.get("Browser"), mapDataSheet) };

	}

	@DataProvider(name="dataBase")
	public  Object[][] getTestDataFromExcel() throws BiffException, IOException, InvalidBrowserException, DataSheetException, DatabaseConnectivityException, SQLException {
		
//		return testDataProvider.getTestDataFromExcel(externalSheetPath, this.getClass().getSimpleName());
		return testDataProvider.getDatabaseData("registration_table", this.getClass().getSimpleName());
	}

	
	
	
  @Test
  public void addSKUItem() throws TestLinkAPIException {
	 
	  Assert.assertEquals(true, false);
	 /* try
	  {
	  P001_SAdv_step_SAHomePage homePage= new P001_SAdv_step_SAHomePage();
	  P002_SAdv_step_SALoginPage loginPage=new P002_SAdv_step_SALoginPage();
	  P003_SAdv_step_ShipToLocation shipToLocation=new P003_SAdv_step_ShipToLocation();
	  P004_SAdv_step_ShopPage shopPage=new P004_SAdv_step_ShopPage();
	  P005_SAdv_step_ProductDetailsPage productDetailsPage=new P005_SAdv_step_ProductDetailsPage();
	  P006_SAdv_step_ShopingCartPage shopingCartPage=new P006_SAdv_step_ShopingCartPage();
	  BC001_SAdv_AddToCart addToCart=new BC001_SAdv_AddToCart();
	  BC001_SAdv_CartIcon cartIcon=new BC001_SAdv_CartIcon();
	  
	  homePage.launchBasePage();
//	  homePage.handleAlert();
      Assert.assertTrue(homePage.hasPageLoaded());
	  Reporter.log("Launched Home page successfully");
		homePage.clickLoginButton();
		Reporter.log("Clicked on Login link");
		loginPage.userLogin(mapDataSheet.get("CustomerId"),mapDataSheet.get("UserId"),mapDataSheet.get("Password"));
		Reporter.log("Logged into the application using customer id"+mapDataSheet.get("CustomerId")+ "user id "+mapDataSheet.get("UserId")+" and password "+mapDataSheet.get("Password"));
		shipToLocation.clickOnOk();
		Reporter.log("clicked on ok button");
		//shopPage.hoverOnMyInkTab();
		shopPage.searchSKU();
		productDetailsPage.viewImage();
		productDetailsPage.productDetails();
	   //addToCart.AddToCart();
		cartIcon.cartIcon();
		boolean condition = true;
		if (condition)
		{
			result = TestLinkAPIResults.TEST_PASSED;				
			notes="Executed successfully";
		}
		
		else
		{
			result = TestLinkAPIResults.TEST_FAILED;
//             exception = ex.getMessage();
             notes="Execution failed";
		}
		
	}
	catch(Exception ex) 
	{
		 result = TestLinkAPIResults.TEST_FAILED;
        exception = ex.getMessage();
        notes="Execution failed";
//        testLinkConnector.updateTestLinkResult("TC001-2", notes, result);
	}
	
	  finally
		{			 
			testLinkConnector.updateTestLinkResult("SAdv-1", notes, result);			
		}*/
		 
  }
  
    
  
}
