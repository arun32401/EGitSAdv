package com.staples.saf.base;


import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.InvalidCookieDomainException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.staples.saf.utilities.Log;



public abstract class BasePage 
{

	private final Logger logger = Log.getInstance(Thread.currentThread()
			.getStackTrace()[1].getClassName());
	public RemoteWebDriver driver;


	public RemoteWebDriver getDriver() {
		return driver;
	}

	public abstract boolean hasPageLoaded();

	public abstract String getPageUrl();

	public BasePage(RemoteWebDriver driver) 
	{
		this.driver = driver;
	}

	
	/**
	 * This function launches the Application URL from grid.properties
	 */
	public void launchBaseURL()
	{
		String URL = BaseTest.getBaseURL();
		if (URL.length()!=0) 
		{
			try
			{
				driver.get(URL);
				logger.info("The Base URL:"+URL +"is loaded");
			} 
			catch(UnreachableBrowserException e)
			{
				logger.error("Unable to load the Base URL: ",e.fillInStackTrace());
				throw new UnreachableBrowserException("Unable to load the Base URL: "+ URL);
			}
		} 
		else
		{
			logger.error("Unable to load the Base URL: "+URL+" Please provide a valid Base URL");
			throw new UnreachableBrowserException("Unable to load the Base URL: "
					+URL+" Please provide a valid Base URL.");
		}
	}

	
	/**
	 * This function is to navigate the browser to a URL
	 * 
	 * @param URL
	 * 			- URL to which browser has to be navigated
	 */
	public void navigateToURL(String URL)
	{
		try
		{
			driver.navigate().to(URL);
		}
		catch(UnreachableBrowserException e)
		{
			logger.error("Unable to launch the URL: "+URL);
			throw new UnreachableBrowserException("Unable to launch the URL: "+URL);
		}
	}
	
	
	/**
	 * This function returns the Current Window URL
	 * 
	 * @return String 
	 * 			- returns the Current Window URL
	 */
	public String getCurrentURL() 
	{
		logger.info("The current Browser URL is returned");
		return driver.getCurrentUrl();
	}

	/**
	 * This function returns the Current Window Title
	 * 
	 * @return String  
	 * 			- returns the Current Window Title
	 */
	public String getPageTitle() 
	{
		logger.info("The Current Window title is " + driver.getTitle());
		return driver.getTitle();
	}

	
	/**
	 * This function checks whether the Current Window URL is same as the
	 * Expected
	 * 
	 * @param expectedURL
	 *           	 - URL expected in the Current Window
	 * @return boolean 
	 * 				- returns true if the CurrentWindow URL matches the
	 *         		  expectedURL, else returns false
	 */
	public boolean isURLAsExpected(String expectedURL) 
	{
		logger.info("The Current URL:" + getCurrentURL() + "; Expected URL:"
				+ expectedURL);
		return expectedURL.equals(getCurrentURL());
	}

	
	/**
	 * This function is to load the previous URL in the browser history.
	 * 
	 */
	public void navigateBack() 
	{
		try 
		{
			driver.navigate().back();
			logger.info("The page is navigated backwards");
		} 
		catch (WebDriverException e) 
		{
			logger.error("The page cannot be navigated backward",e.fillInStackTrace());
			throw new WebDriverException("The page cannot be navigated backward");
		}
	}

	
	/**
	 * This function loads the URL which is forward in the browser's history.
	 * Does nothing if we are on the latest page viewed.
	 * 
	 */
	public void navigateForward() 
	{
		try 
		{
			driver.navigate().forward();
			logger.info("The page is navigated forward");
		} 
		catch (UnreachableBrowserException e) 
		{
			logger.error("The page cannot be navigated forward",e.fillInStackTrace());
			throw new UnreachableBrowserException("The page cannot be navigated forward");
		}
	}

	
	/**
	 * This function refresh the current page
	 * 
	 */
	public void refreshPage() 
	{
		try 
		{
			driver.navigate().refresh();
			logger.info("The page is refreshed");
		} 
		catch (UnreachableBrowserException e) 
		{
			logger.error("The page cannot be refreshed",e.fillInStackTrace());
			throw new UnreachableBrowserException("The page cannot be refreshed");
		}
	}

	
	/**
	 * This function is to make the driver wait explicitly for a condition to be
	 * satisfied
	 * 
	 * @param locator
	 *            - By object of the element whose visibility/presence/clickability has to be checked
	 */
	public void addExplicitWait(By locator, String condition, int inttimeoutinseconds )
	{

		WebDriverWait webDriverWait = new WebDriverWait(driver, inttimeoutinseconds, 250L);
		try
		{
			if(condition.equalsIgnoreCase("visibility"))
			{
				webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
				logger.info("Driver waits explicitly until the element with"+locator.
						toString().replace("By."," ")+" is visible");
			}
			else if(condition.equalsIgnoreCase("clickable"))
			{
				webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
				logger.info("Driver waits explicitly until the element with"+locator.
						toString().replace("By."," ")+" is clickable");
			}
			else if(condition.equalsIgnoreCase("presence"))
			{
				webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
				logger.info("Driver waits explicitly until the element with"+locator.
						toString().replace("By."," ")+" is present");
			}
			else
				logger.error("Condition String should be visibility or clickable or presence");
		}
		catch(NoSuchElementException e)
		{
			logger.error("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
		catch(UnsupportedCommandException e)
		{
			logger.error("The condition given to check the elemnt with"
					+ locator.toString().replace("By.", " ")
					+ " is invalid",e.fillInStackTrace());
			throw new NoSuchElementException("The condition given to check the elemnt with"
					+ locator.toString().replace("By.", " ")
					+ " is invalid",e.fillInStackTrace());
		}
	}


	/**
	 * This function clicks on the element which can be located by the By Object
	 * 
	 * @param locator
	 *            - By object to locate the element to be clicked
	 */
	public void click(By locator)  
	{
		try
		{
			driver.findElement(locator).click();
			logger.info("The element with"
					+ locator.toString().replace("By.", " ")
					+ " is clicked");
		}
		catch(NoSuchElementException e)
		{
			logger.error("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This function is to pass a text into an input field within UI
	 * 
	 * @param locator
	 *            - By object to locate the input field to which text has to be
	 *            send
	 * @param value
	 *            - Text value which is to be send to the input field
	 */
	public void type(By locator, String value)
	{
		try 
		{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(value);
			logger.info("String " + value + " is send to element with"
					+ locator.toString().replace("By.", " "));
		} 
		catch (NoSuchElementException e) 
		{
			logger.error("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This function is to get the visible text of an element within UI
	 * 
	 * @param locator
	 *            - By object to locate the element from which the text has to
	 *            be taken
	 * @return String - returns the innertext of the specified element
	 */
	public String getText(By locator) 
	{
		String text = null;
		try 
		{
			logger.info("The value on the field with"
					+ locator.toString().replace("By.", " ")
					+ " is obtained");
			text = driver.findElement(locator).getText();
		} 
		catch (NoSuchElementException e)
		{
			logger.error("Unable to get the text. The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("Unable to get the text. The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
		return text;
	}

	
	/**
	 * This function is to select a dropdown option using its index
	 * 
	 * @param locator
	 *            - By object to locate the dropdown which is to be selected
	 * @param index
	 *            - index of the dropdown option to be selected
	 */
	public void selectDropDownByIndex(By locator, int index)
	{
		try 
		{
			Select dropDown = new Select(driver.findElement(locator));
			dropDown.selectByIndex(index);
			logger.info("The dropdown option with index " + index
					+ " is selected");
		} 
		catch (NoSuchElementException e)
		{
			logger.error("Unable to select the dropdown; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This function is to select the dropdown options that have a value
	 * matching the argument
	 * 
	 * @param locator
	 *            - By object to locate the dropdown which is to be selected
	 * @param value
	 *            - value to match against the dropdown option to be selected
	 */
	public void selectDropDownByValue(By locator, String value)
	{
		try 
		{
			Select dropDown = new Select(driver.findElement(locator));
			dropDown.selectByValue(value);
			logger.info("The dropdown option with value " + value
					+ " is selected");
		} 
		catch (NoSuchElementException e) 
		{
			logger.error("Unable to select the dropdown; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This function is to select the dropdown options that displays text
	 * matching the argument
	 * 
	 * @param locator
	 *            - By object to locate the dropdown which is to be selected
	 * @param visibleText
	 *            - visible text to match against the dropdown option to be
	 *            selected
	 */
	public void selectDropDownByVisibleText(By locator, String visibleText) 
	{
		try
		{
			Select dropDown = new Select(driver.findElement(locator));
			dropDown.selectByVisibleText(visibleText);
			logger.info("The dropdown option with text " + visibleText
					+ " is selected");
		} 
		catch (NoSuchElementException e)
		{
			logger.error("Unable to select the dropdown; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This function is to move the mouse pointer to the specified location
	 * 
	 * @param locator
	 *            - By object to locate the element to which mouse pointer has
	 *            to be moved
	 */
	public void mouseOver(By locator) 
	{
		try
		{
			new Actions(driver).moveToElement(driver.findElement(locator))
			.build().perform();
			logger.info("Mouse hover is performed on element with"
					+ locator.toString().replace("By.", " "));
		} 
		catch (NoSuchElementException e)
		{
			logger.error("Unable to perform MouseOver; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	} 

	
	/**
	 * This function is to click on an element by moving the mouse pointer 
	 * to the specified location or to read the tip of a mouse 
	 * 
	 * @param locator 
	 * 				- By object to locate the element to which mouse pointer has 
	 * 				to be moved
	 */
	public void moveMouseTipAndClick(By locator)
	{
		try
		{
			WebElement element = driver.findElement(locator);
			Locatable hoverItem = (Locatable) element;
			Mouse mouse = ((HasInputDevices) driver).getMouse();
			mouse.mouseMove(hoverItem.getCoordinates());
			mouse.click(hoverItem.getCoordinates());
		}
		catch(NoSuchElementException e)
		{
			logger.error("Unable to perform click; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}
	
	
	/**
	 * This function performs a click-and-hold at the location of the source
	 * element; moves to the location of the target element, then releases the
	 * mouse.
	 * 
	 * @param initialElementLocator
	 *            - By object of the initial location of the source webelement
	 * @param finalElementLocator
	 *            - By object of the final location where the webelement has to
	 *              be moved
	 */
	public void dragAndDrop(By initialElementLocator, By finalElementLocator)
	{
		try 
		{
			Actions builder = new Actions(driver);
			Action dragAndDrop = builder
					.clickAndHold(driver.findElement(initialElementLocator))
					.moveToElement(driver.findElement(finalElementLocator))
					.release(driver.findElement(finalElementLocator))
					.build();
			dragAndDrop.perform();
			logger.info("The element is draged from"
					+ initialElementLocator.toString().replace("By.", " ")
					+ " to"
					+ finalElementLocator.toString().replace("By.", " "));
		} 
		catch (NoSuchElementException e) 
		{
				logger.error("Unable to perform dragAndDrop;The element is not draged from"
					+ initialElementLocator.toString().replace("By.", " ")
					+ " to"
					+ finalElementLocator.toString().replace("By.", " "),e.fillInStackTrace());
			throw new NoSuchElementException("Unable to perform dragAndDrop;The element is not draged from"
					+ initialElementLocator.toString().replace("By.", " ")
					+ " to"
					+ finalElementLocator.toString().replace("By.", " "));
		}
	}

	
	/**
	 * This function is to perform double click on a webelement
	 * 
	 * @param locator
	 *            - By object of the webelement on which double click has to be
	 *            performed
	 */
	public void doubleClick(By locator)
	{
		try
		{
			Actions builder = new Actions(driver);
			builder.doubleClick(driver.findElement(locator));
			logger.info("The element with" + locator.toString().replace("By.", " ")
					+ " is right clicked");
		}
		catch(NoSuchElementException e)
		{
			logger.error("Unable to perform doubleClick; The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
	}

	
	/**
	 * This funtion is to maximize the Current Browser Window
	 * 
	 */
	public void maximizeWindow() 
	{
		try 
		{
			driver.manage().window().maximize();
			logger.info("Window is maximized");
		} 
		catch (UnreachableBrowserException e) 
		{
			logger.error("Unable to maximize the window: ",e.fillInStackTrace());
			throw new NoSuchElementException("Unable to maximize the window");
		}
	}
	

	/**
	 * This function is to add a time delay
	 * 
	 * @param time
	 *            - time duration in MilliSeconds
	 */
	public void delay(int time) throws InterruptedException 
	{
		try 
		{
			Thread.sleep(time);
			logger.info("Delay for " + time + " MilliSeconds is added");
		} 
		catch (InterruptedException e) 
		{
			logger.error("Issue in adding extra delay",e.fillInStackTrace());
			throw new InterruptedException("Issue in adding extra delay");
		} 
	}

	
	/**
	 * This function is to get the current window handle
	 * 
	 * @return windowHandle 
	 * 				- returns the handle of current browser window
	 */
	public String getWindowHandle() 
	{
		String windowHandle = driver.getWindowHandle();
		logger.info("The current window handle " + windowHandle
				+ " is returned");
		try 
		{
			windowHandle = driver.getWindowHandle();
			logger.info("The current window handle "+windowHandle+" is returned");
		} 
		catch (WebDriverException e) 
		{
			logger.error("Unable to returm the window handle: ",e.fillInStackTrace());
			throw new WebDriverException("Unable to returm the window handle");
		}
		return windowHandle;
	}

	
	/**
	 * This function is to switch the driver from Current Window to newly opened Window
	 */
	public void switchToWindow() 
	{
		try 
		{
			for (String windowHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(windowHandle);
			}
			logger.info("The window is switched");
		} 
		catch (NoSuchWindowException e) 
		{
			logger.error("Unable to switch the window: ",e.fillInStackTrace());
			throw new NoSuchWindowException("Unable to switch the window");
		}
	}

	
	/**
	 * This function is to switch into a frame using frame index
	 * 
	 * @param index
	 *            - index of the frame to which driver has to be switched into
	 */
	public void switchToFrameByIndex(int index) {
		try 
		{
			driver.switchTo().frame(index);
			logger.info("The driver is switched into frame");
		} 
		catch (NoSuchFrameException e) 
		{
			logger.error("Unable to switch into frame: ",e.fillInStackTrace());
			throw new NoSuchFrameException("Unable to switch into frame");
		}
	}

	
	/**
	 * This function is to switch into a frame using the frame name
	 * 
	 * @param frameName
	 *            - name of the frame to which driver has to be switched into
	 */
	public void switchToFrameByName(String frameName) 
	{
		if (!frameName.equalsIgnoreCase(null)) 
		{
			try 
			{
				driver.switchTo().frame(frameName);
				logger.info("The driver is switched to frame: " + frameName);
			} 
			catch (NoSuchFrameException e) 
			{
				logger.error("Unable to switch into frame:" ,e.fillInStackTrace());
				throw new NoSuchFrameException("Unable to switch into frame");
			}
		}
		logger.info("Unable to switch into frame as framename is null");
	}

	
	/**
	 * This function is to switch into a frame; frame is located as a webelemet
	 * 
	 * @param locator
	 *            - By object of the webelemet using which frame can be located
	 */
	public void switchToFrameByWebElement(By locator) 
	{
		try 
		{
			driver.switchTo().frame(driver.findElement(locator));
			logger.info("The driver is switched to frame with"
					+ locator.toString().replace("By.", " "));
		} 
		catch (NoSuchFrameException e) 
		{
			logger.error("Unable to switch into frame: ",e.fillInStackTrace());
			throw new NoSuchFrameException("Unable to switch into frame");
		}
	}

	
	/**
	 * This function is to click on a webelemet using JavascriptExecutor
	 * 
	 * @param locator 
	 * 			- By object of the webelement which is to be clicked
	 */
	public void clickUsingJavascriptExecutor(By locator)
	{
		try 
		{
			JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;;
			WebElement webElement = driver.findElement(locator);
			javaScriptExecutor.executeScript("arguments[0].click();", webElement);
			logger.info("The element with"
					+locator.toString().replace("By."," ")
					+" is clicked");
		} 
		catch (NoSuchElementException e) 
		{
			logger.error("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
		
	}
	
	
	/**
	 * This function is to scroll the browser window to a webelement 
	 * using JavascriptExecutor
	 * 
	 * @param locator 
	 * 				- By object of the webelement to which the window has to be scrolled
	 */
	public void scrollToElementUsingJavascriptExecutor(By locator)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement webElement = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView(true);",webElement);
			logger.info("Browser window is scrolled to element with"
					+locator.toString().replace("By."," "));
		}
		catch (NoSuchElementException e) 
		{
			logger.error("Unable to scroll: ",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
					+ locator.toString().replace("By.", " ")
					+ " not found");
		}
		catch (MoveTargetOutOfBoundsException e) 
		{
			logger.error("Unable to scroll: ",e.fillInStackTrace());
			throw new MoveTargetOutOfBoundsException("Target element provided with"
					+locator.toString().replace("By."," ")+"is invalid");
		}
		
	}

	
	/**
	 * This function is to check whether a webelement is visible or not
	 * 
	 * @param locator 
	 * 				- By object of the webelement which is to be checked
	 * @return boolean 
	 * 				- returns true if the specified webelement is visible,
	 * 				  else it will return false
	 */
	public boolean isElementVisible(By locator)
	{
		logger.info("The element with "
					+ locator.toString().replace("By.", " ") + " is visible");
		return driver.findElement(locator).isDisplayed();
	}

	
	/**
	 * This function is to check whether a webelement is enabled or not
	 * 
	 * @param locator
	 * 				- By object of the webelement which is to be checked
	 * @return boolean 
	 * 				- returns true if the specified webelement is enabled, 
	 * 				  else it will return false
	 */
	public boolean isElementEnabled(By locator) 
	{
		logger.info("The element with" 
					+ locator.toString().replace("By.", " ")+ " is enabled");
		return driver.findElement(locator).isEnabled();
	}

	
	/**
	 * This function is to check whether the Current Window Title is as expected
	 * 
	 * @param expectedTitle 
	 * 				- Title expected in the Current Window
	 * @return boolean 
	 * 				- returns true if the CurrentTitle matches the expectedTitle, 
	 * 				  else it will return false
	 */
	public boolean isTitleAsExpected(String expectedTitle)
	{
		logger.info("The current window title is "+getPageTitle()
					+" whereas the expected is "+expectedTitle);
		return expectedTitle.equals(getPageTitle());
	}
	
	/**
	 * This function is to check whether there is any alert present.
	 * 
	 * 
	 * @return boolean 
	 * 				- returns true if alert is present, 
	 * 				  else it will return false
	 */
	
	public boolean isAlertPresent()
	{
		try{
			driver.switchTo().alert();
			logger.info("An alert box is present");
		    return true;
		}
		catch(Exception e)
		{
			logger.error("There is no alert present ", e.fillInStackTrace());
			return false;
		}
	}

	
	/**
	 * This function is to get a cookie with a specific name
	 * 
	 * @param cookieName
	 *            	- Name of the cookie which is to be returned
	 * @return Cookie 
	 * 				- Returns the cookie value for the name specified, or null
	 *         			if no cookie found with the given name
	 */
	public Cookie getCookie(String cookieName)
	{
		logger.info("The cookie:"+cookieName +" is obtained");
		return driver.manage().getCookieNamed(cookieName);
	}
	
	
	/**
	 * This function is to delete a cookie from the browser's "cookie jar"
	 * The domain of the cookie will be ignored.
	 * 
	 * @param cookieName 
	 * 				- name of the cookie which is to be deleted.
	 */
	public void deleteCookieNamed(String cookieName)
	{
		if(!cookieName.equalsIgnoreCase(null)) 
		{
			try 
			{
				logger.info("The cookie:"+cookieName +" is deleted");
				driver.manage().deleteCookieNamed(cookieName);
			}
			catch (InvalidCookieDomainException e) 
			{
				logger.error("Unable to delete the cookie: ",e.fillInStackTrace());
				throw new InvalidCookieDomainException("The cookie with name "
							+ cookieName+ " cannot be deleted");
			}
		}
		else
			logger.info("Cookie Name is null; Unable to delete");
	}
	
	
	/**
	 * This function is to delete all the cookies for the current domain
	 * 
	 */
	public void deleteAllCookie()
	{
		try 
		{
			driver.manage().deleteAllCookies();
			logger.info("All cookies are deleted");
		} 
		catch (InvalidCookieDomainException e)
		{
			logger.error("Unable to delete cookies: ",e.fillInStackTrace());
			throw new InvalidCookieDomainException("Unable to delete cookies");
		}
	}
	
	
	/**
	 * This function is to perform a right click on a particular webelement
	 * @param locator - By object of the Webelement on which right click 
	 * 					operation has to be performed
	 */
	public void rightClick(By locator)
	{
		try
		{
			WebElement webElement = driver.findElement(locator);
			Actions action= new Actions(driver);
			action.contextClick(webElement).build().perform();
			logger.info("The element with "
						+locator.toString().replace("By."," ")+" is right clicked");
		}
		catch (NoSuchElementException e) 
		{
			logger.error("Unable to scroll: ",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
		}
		catch (UnsupportedCommandException e) 
		{
			logger.error("Unable to scroll: ",e.fillInStackTrace());
			throw new UnsupportedCommandException("Command used by the webdriver is unsupported");
		}
	} 

	
	/**
	 * This function is to move the Webelement to an offset from the 
	 * top-left corner of the Webelement
	 * 
	 * @param locator 
	 * 				- By object to locate the Webelement which is to be moved
	 * @param xOffset 
	 * 				- xOffset by which the Webelement will be moved 
	 * 				  from the current position towards x-axis
	 * @param yOffset 
	 * 				- yOffset by which the Webelement will be moved 
	 * 				  from the current position towards y-axis
	 */
	public void moveToElement(By locator,int xOffset,int yOffset)
	{
		try
		{
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(locator), xOffset, yOffset);
			logger.info("Element with "+locator.toString().replace("By."," ")
						+" " +"is moved "+xOffset+" along x-axis and"
						+yOffset+" along y-axis");
		}
		catch (MoveTargetOutOfBoundsException e) 
		{
			logger.error("Unable to move the element from current position",e.fillInStackTrace());
			throw new MoveTargetOutOfBoundsException("Target provided x:"+xOffset
						+"and y:"+yOffset+"is invalid");
		}
		catch (NoSuchElementException e) 
		{
			logger.error("Unable to move the element from current position",e.fillInStackTrace());
			throw new NoSuchElementException("The element with"
						+ locator.toString().replace("By.", " ")
						+ " not found");
		}
		
	}
	
	
	/**
	 * This function is to handle the alert; Will Click on OK button
	 * First get a handle to the open alert, 
	 * prompt or confirmation and then accept the alert.
	 * 
	 */
	public void acceptAlert()
	{
		try 
		{
			Alert alertBox=driver.switchTo().alert();
			alertBox.accept();
			logger.info("The alert is accepted");
		} 
		catch (NoAlertPresentException e) 
		{
			logger.error("Unable to access the alert: ",e.fillInStackTrace());
			throw new NoAlertPresentException("Alert not present");
		}
	}
	
	
	/**
	 * This function is to handle the alert; Will Click on Cancel button
	 * First get a handle to the open alert, 
	 * prompt or confirmation and then dismiss the alert.
	 * 
	 */
	public void dismissAlert()
	{
		try 
		{
			Alert alertBox=driver.switchTo().alert();
			alertBox.dismiss();
			logger.info("The alert is dismissed");
		} 
		
		catch (NoAlertPresentException e) 
		{
			logger.error("Unable to access the alert: ",e.fillInStackTrace());
			throw new NoAlertPresentException("Alert not present");
		}
	}
	
	
	/**
	 * This function is to get the text which is present on the Alert.
	 * 
	 * @return String
	 * 			 - returns the text message which is present on the Alert.
	 */
	public String getAlertMessage()
	{	
		String alertMessage = null;
		try 
		{
			Alert alertBox=driver.switchTo().alert();
			logger.info("The text "+alertBox.getText()+" within popup is returned");
			alertMessage = alertBox.getText();
		} 
		catch (NoAlertPresentException e) 
		{
			logger.error("Unable to access the alert:",e.fillInStackTrace());
			throw new NoAlertPresentException("Alert not present");
		}
		return alertMessage;
	}
	
	
	/**
	 * This function closes the Current Browser Window
	 * 
	 */
	public void closeCurrentWindow()
	{
		driver.close();
		logger.info("Driver window is closed");
	}
	

}
