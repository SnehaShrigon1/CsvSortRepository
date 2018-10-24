package com.ibm.hm.csv.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.hm.csv.exceptions.CsvException;
import com.ibm.hm.csv.messages.ErrorMessages;
import com.ibm.hm.csv.model.CsvItem;

/**
 * Custom csv file parser for any csv file containing column header and column
 * referred for sorting of lines
 * 
 * @author Sneha
 * 
 */
public class CsvParser {

	private BufferedReader reader;
	private String inFileName;
	private String colName;
	private int columnIndex = 0;
	private int numberOfColumns = 0;

	public CsvParser(String inFileName, String columnName) throws FileNotFoundException {
		this.inFileName = inFileName;
		this.colName = columnName;
		this.reader = new BufferedReader(new FileReader(inFileName));
	}

	/**
	 * Method to parse csv file and return list of CsvItems, for each data line
	 * one CsvItem object is created.
	 * 
	 * @return list of CsvItem
	 * @throws CsvException
	 * @throws IOException
	 */
	public List<CsvItem> parse() throws CsvException, IOException {
		List<CsvItem> csvList = new ArrayList<CsvItem>();
		try {
			// check if first line is column header line or not
			String line = this.reader.readLine();
			int lineNumber = 1;

			if (line != null) {
				if (!line.isEmpty()) {
					setColumnIndex(line);
				}
			} else {
				throw new CsvException(inFileName, ErrorMessages.ERR_MSG_EMPTY_CSV_FILE);
			}

			// continue reading lines till end of file is reached
			line = this.reader.readLine();
			lineNumber++;
			while (line != null) {
				if (!line.isEmpty()) {
					CsvItem csvObj = getCSVObject(line);
					csvObj.setLineNumber(lineNumber);
					csvList.add(csvObj);
				}
				line = this.reader.readLine();
				lineNumber++;
			}

			// check if no valid data line in file
			if (csvList.size() == 0) {
				throw new CsvException(inFileName, ErrorMessages.ERR_MSG_INVALID_CSV_FILE);
			}
		} finally {
			this.reader.close();
		}
		return csvList;
	}

	/**
	 * Method calculates the column index for given column name in the csv file
	 * 
	 * @param line
	 * @throws CsvException
	 */
	private void setColumnIndex(String line) throws CsvException {
		boolean error = false;
		// if line contains column name, then its a column header line
		if (line.contains(colName)) {
			String[] csv = line.split(",");
			int i = 0;
			for (String val : csv) {
				i++;
				if (val.equals(colName)) {
					break;
				}
			}
			if (i == 0) {
				error = true;
			} else {
				columnIndex = i - 1;
				numberOfColumns = csv.length;
			}
		} else {
			error = true;
		}
		if (error) {
			throw new CsvException(inFileName, line, ErrorMessages.ERR_MSG_INVALID_COL_NAME);
		}
	}

	/**
	 * Method to create and initialize CsvItem for a given line of csv file. If
	 * header column number doesn't match with data column number exception is
	 * thrown
	 * 
	 * @param line
	 * @return
	 * @throws CsvException
	 */
	private CsvItem getCSVObject(String line) throws CsvException {
		String[] csv = line.split(",");
		// check if header column number matches with data column number
		if (csv.length == numberOfColumns) {
			CsvItem csvObj = new CsvItem();
			csvObj.setCvsStr(line);
			csvObj.setSortColValue(csv[columnIndex]);
			return csvObj;
		} else {
			throw new CsvException(inFileName, line, ErrorMessages.ERR_MSG_INCORRECT_DATA_COLS);
		}
	}
}
