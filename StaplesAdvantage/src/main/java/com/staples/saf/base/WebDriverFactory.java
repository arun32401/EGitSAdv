package com.staples.saf.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.staples.saf.utilities.Log;

/**
 * @author TCS
 * 
 */
public class WebDriverFactory {

	/**
	 * This function initialize a WebDriver object using on the Browser type and
	 * remoteServerUrl
	 * 
	 * @param browserType
	 * @param remoteServerUrl
	 * @return RemoteWebdriver
	 * @throws MalformedURLException
	 */
	public static RemoteWebDriver getWebdriver(String browserType,
			URL remoteServerUrl) throws MalformedURLException {

		Logger logger = Log
				.getInstance(Thread.currentThread().getStackTrace()[1]
						.getClassName());
		logger.info("");
		logger.info("Browser type is:" + browserType);
		RemoteWebDriver webdriver = null;
		DesiredCapabilities driverCapability = null;
		ResourceBundle browserProperties = getBrowserProperties();

		if ("InternetExplorer".equalsIgnoreCase(browserType)) {
			try
			{

				driverCapability = DesiredCapabilities.internetExplorer();
				driverCapability.setCapability("browserName", "internet explorer");
				driverCapability.setCapability("acceptSslCerts", "true");
				driverCapability.setCapability("javascriptEnabled", "true");
				driverCapability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.ie.driver",
						browserProperties.getString("webdriver_ie_driver"));
				logger.info("getWebDriver - Set webdriver.ie.driver system property as: "
						+ System.getProperty("webdriver.ie.driver"));
			}

			catch(IllegalStateException e)
			{
				logger.error("The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file",e.fillInStackTrace());
				throw new IllegalStateException("The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file");

			}
		}

		else if ("Firefox".equalsIgnoreCase(browserType)) {
			
			System.out.println("browserType"+browserType);

			driverCapability = DesiredCapabilities.firefox();
			driverCapability.setCapability("browserName", "firefox");

		}

		else if ("Chrome".equalsIgnoreCase(browserType)) {
			try
			{
				driverCapability = DesiredCapabilities.chrome();
				driverCapability.setCapability("browserName", "chrome");
				System.setProperty("webdriver.chrome.driver",
						browserProperties.getString("webdriver_chrome_driver"));

				driverCapability.setCapability("acceptSslCerts", "true");
				driverCapability.setCapability("javascriptEnabled", "true");
			}

			catch(IllegalStateException e)
			{
				logger.error("The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file",e.fillInStackTrace());
				throw new IllegalStateException("The path to the driver executable must be set by the webdriver.ie.driver system property, Check IE driver path in Global.properties file");
			}
			driverCapability.setCapability("acceptSslCerts", true);
			driverCapability.setJavascriptEnabled(true);
		}

		else {
			logger.error("getWebDriver - Unable to instantiate new webDriver. Unrecognised browser identifier. "
					+ browserType);
		}

		webdriver = new RemoteWebDriver(remoteServerUrl, driverCapability);
		return webdriver;

	}

	/**
	 * This function reads Browser path from Global properties
	 * 
	 * @return Browser Properties
	 */
	public static ResourceBundle getBrowserProperties() {

		ResourceBundle browserProperties = null;
		browserProperties = ResourceBundle.getBundle("global");
		return browserProperties;

	}
}
