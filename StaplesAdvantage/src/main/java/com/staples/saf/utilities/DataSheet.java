package com.staples.saf.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.staples.saf.exceptions.DataSheetException;
import com.staples.saf.exceptions.InvalidBrowserException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataSheet {

	Logger logger = Log.getInstance(Thread.currentThread().getStackTrace()[1]
			.getClassName());

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method checks for the headings in the sheet and returns true
	 *              if the headings are present
	 * @throws BiffException
	 * @throws IOException
	 */
	public boolean validateHeading(String destFile, String sheetname)
			throws BiffException, IOException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);

		Sheet sheet = wb.getSheet(sheetname);

		if ((sheet.getCell(0, 0).getContents().equalsIgnoreCase("BROWSER"))
				&& (sheet.getCell(1, 0).getContents()
						.equalsIgnoreCase("EXECUTION STATUS"))) {
			return true;

		}
		return false;
	}

	
	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method checks for the browser in the sheet and returns true
	 *              if the browser values are correct
	 * @throws BiffException
	 * @throws IOException
	 * @throws NoDataInInputSheetException
	 */
	public boolean isInvalidBrowserName(String destFile, String sheetname)
			throws BiffException, IOException, DataSheetException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);
		Sheet sheet = wb.getSheet(sheetname);
		int rows = getRowCount(destFile, sheetname);
		for (int row = 1; row < rows; row++) {
			if (sheet.getCell(0, row).getContents().equalsIgnoreCase("Firefox")
					|| sheet.getCell(0, row).getContents()
					.equalsIgnoreCase("InternetExplorer")
					|| sheet.getCell(0, row).getContents()
					.equalsIgnoreCase("Chrome")) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method returns column count in the data sheet
	 * @throws BiffException
	 * @throws IOException
	 * @throws DataSheetException 
	 */
	public int getColumnCount(String destFile, String sheetname)
			throws BiffException, IOException,DataSheetException,FileNotFoundException

			{
		int column = 0;
		try
		{
		
		FileInputStream input = new FileInputStream(destFile);


		Workbook wb = Workbook.getWorkbook(input);

		Sheet sheet = wb.getSheet(sheetname);

		column = sheet.getColumns();

		if (column != 0) {
			return column;
		} else {
			logger.error("The input data sheet is blank");
			
		}
		}
		catch(FileNotFoundException fe){
			logger.error("Please provide a valid sheet path:"+destFile +" "+ "can not be found");
		}
		catch(DataSheetException de)
		{
			logger.error("The data sheet is blank");
		}
		return column;
			}

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return-This method returns the row count in the sheet
	 * @throws BiffException
	 * @throws IOException
	 */
	public int getRowCount(String destFile, String sheetname)
			throws BiffException, IOException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);

		Sheet sheet = wb.getSheet(sheetname);
		int row = sheet.getRows();
		return row;

		// return row;

	}

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return -This method returns the valid rows considering execution status
	 * @throws BiffException
	 * @throws IOException
	 * @throws DataSheetException 
	 * @throws NoDataInInputSheetException
	 */

	public int getValidRows(String destFile, String sheetname)
			throws BiffException, IOException, DataSheetException {
		FileInputStream input = new FileInputStream(destFile);
		Workbook wb = Workbook.getWorkbook(input);
		Sheet sheet = wb.getSheet(sheetname);
		int columns = getColumnCount(destFile, sheetname);
		int rows = getRowCount(destFile, sheetname);
		int count = 0;
		for (int row = 1; row < rows; row++) {

			if ((sheet.getCell(0, row).getContents().contains(","))) {
				if (sheet.getCell(1, row).getContents().equalsIgnoreCase("Y")) {
					String values = sheet.getCell(0, row).getContents();
					String[] cellarray = values.trim().split(",");
					count = cellarray.length + count;
				}
			}

			else {
				if (sheet.getCell(1, row).getContents().equalsIgnoreCase("Y")) {
					count++;
				}
			}

		}

		return count;
	}

	/**
	 * @param destFile
	 *            -This argument is for passing the location of the input data
	 *            sheet
	 * @param sheetname
	 *            -This argument is for passing the sheet name in the input data
	 *            sheet
	 * @return -Returns data object array which reads rows from the input data
	 *         sheet
	 * @throws BiffException
	 * @throws IOException
	 * @throws InvalidSheetHeadingException
	 * @throws IncorrectSheetPathException 
	 * @throws InvalidExecutionStatusException 
	 * @throws DataSheetException 
	 * @throws NoDataInInputSheetException
	 */
	public Object[][] readFromSheet(String destFile, String sheetname)
			throws BiffException, IOException, InvalidBrowserException,DataSheetException
			{
			
		int columns = getColumnCount(destFile, sheetname);
		int rows = getRowCount(destFile, sheetname);
		int dataObjectArraySize = getValidRows(destFile, sheetname);
		Object[][] dataObjectArray = new Object[dataObjectArraySize][getColumnCount(
				destFile, sheetname)];
		
			

			int index = 0;
			boolean headingStatus = validateHeading(destFile, sheetname);
			boolean browserNames = isInvalidBrowserName(destFile, sheetname);
			FileInputStream input = new FileInputStream(destFile);
			Workbook wb = Workbook.getWorkbook(input);
			Sheet sheet = wb.getSheet(sheetname);

			if (headingStatus) {

				/* Iterates through each row in excel */
				for (int row = 1; row < rows; row++) {

					/* Checking if execution status is 'Y' */
					if (sheet.getCell(1, row).getContents().equalsIgnoreCase("Y")) {
						/*
						 * Checking if each cell in the browser column contains coma
						 * separated values
						 */
						if ((sheet.getCell(0, row).getContents().contains(","))) {
							String comaSeparatedBrowsers = sheet.getCell(0, row)
									.getContents();
							String[] browserArray = comaSeparatedBrowsers.trim()
									.split(",");

							for (String browser : browserArray) {
								if (browser.equalsIgnoreCase("Firefox")
										|| browser
										.equalsIgnoreCase("InternetExplorer")
										|| browser.equalsIgnoreCase("Chrome")) {
									/*
									 * This hash map stores the coma separated
									 * values.This stores the browser and its values
									 */
									LinkedHashMap<String, String> linkedMap1 = new LinkedHashMap<String, String>();
									linkedMap1.put(sheet.getCell(0, 0)
											.getContents(), browser);
									for (int column = 1; column < columns; column++) {

										/* Stores the rest of the columns */
										linkedMap1.put(sheet.getCell(column, 0)
												.getContents(),
												sheet.getCell(column, row)
												.getContents());

									}

									dataObjectArray[index] = new Object[] { linkedMap1 };

									index++;
								} else {
									logger.error("Browser name"
											+ " "
											+ browser
											+ " "
											+ "is invalid:Please give InternetExplorer,Chrome or Firefox");
									throw new InvalidBrowserException(
											"Browser name"
													+ " "
													+ browser
													+ " "
													+ "is invalid:"
													+ "Please give InternetExplorer,Chrome or Firefox");

								}

							}
						} else {
							if (browserNames) {

								LinkedHashMap<String, String> linkedMap1 = new LinkedHashMap<String, String>();
								for (int column = 0; column < columns; column++) {
									linkedMap1.put(sheet.getCell(column, 0)
											.getContents(),
											sheet.getCell(column, row)
											.getContents());
								}
								dataObjectArray[index] = new Object[] { linkedMap1 };

								index++;
							} else {
								logger.error("Browser name is invalid:Please give InternetExplorer,Chrome or Firefox");
								throw new InvalidBrowserException(
										"Browser name is invalid:"
												+ "Please give InternetExplorer,Chrome or Firefox");
							}

						}

					}
					else if(sheet.getCell(1, row).getContents().equalsIgnoreCase("N"))
					{
						logger.info("Not exceuting this test case");
					
					}
					else
					{
						throw new DataSheetException("Please enter either Y or N for exceution status");
					}

				}
			}

			else {
				logger.error("The sheet headings are invalid:The headings should be Browser and Execution Status");
				throw new DataSheetException(
						"The sheet headings are invalid:The headings should be Browser and Execution Status");
			}

		
		
		return dataObjectArray;

	}
}
