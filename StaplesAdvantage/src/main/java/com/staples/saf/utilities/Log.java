package com.staples.saf.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author TCS
 *
 */
public class Log {
	
	/**
	 * This function configures the Log4j xml
	 * @param className
	 * @return
	 */
	public static Logger getInstance(String className)
	{
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-MM-SS");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	    DOMConfigurator.configure("log4j.xml");
		return Logger.getLogger(className);
	}

}
