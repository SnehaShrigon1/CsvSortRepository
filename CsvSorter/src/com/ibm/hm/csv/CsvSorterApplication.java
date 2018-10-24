package com.ibm.hm.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ibm.hm.csv.exceptions.CsvException;
import com.ibm.hm.csv.model.CsvItem;

/**
 * Main class to take user inputs and initialize sorting of csv file for the
 * given sort definition file and input csv file to be sorted, for the given
 * column
 * 
 * Sorted csv lines are printed to console
 * 
 * For any file errors, data errors, detailed error message or stack trace is
 * displayed on console.
 * 
 * @author Sneha
 * 
 */
public class CsvSorterApplication {

	private static final String APP_USAGE_MSG = "usage:java CsvSorterApplication [file-to-be-sorted] [column-to-sort-on] [sort-order-definition-file] [sort-order-definition-column]";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println(APP_USAGE_MSG);

		} else {
			String fileToBeSortedPath = args[0];
			String colToSortOn = args[1];
			String sortOrderDefFilePath = args[2];
			String sortOrderDefCol = args[3];

			try {
				File sortOrderDefFile = new File(sortOrderDefFilePath);
				File fileToBeSorted = new File(fileToBeSortedPath);

				if (sortOrderDefFile.exists() && fileToBeSorted.exists()) {
					CsvSorter csvSorter = new CsvSorter(sortOrderDefFilePath, sortOrderDefCol);
					List<CsvItem> sortedCsvItems = csvSorter.getSortedCsvItemList(fileToBeSortedPath, colToSortOn);
					for (CsvItem item : sortedCsvItems) {
						item.print();
					}
				}
			} catch (CsvException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
