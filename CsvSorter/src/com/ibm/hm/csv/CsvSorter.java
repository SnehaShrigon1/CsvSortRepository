package com.ibm.hm.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.ibm.hm.csv.exceptions.CsvException;
import com.ibm.hm.csv.messages.ErrorMessages;
import com.ibm.hm.csv.model.CsvItem;
import com.ibm.hm.csv.model.SortOrder;
import com.ibm.hm.csv.parser.CsvParser;
import com.ibm.hm.csv.util.CsvComparator;

/**
 * 
 * @author Sneha
 * 
 */
public class CsvSorter {

	private String sortOrderDefFilePath;
	private String sortOrderDefCol;
	private SortOrder sortOrder;
	private CsvComparator comparator;

	public CsvSorter(String sortOrderDefFilePath, String sortOrderDefCol) throws CsvException, IOException, FileNotFoundException {
		this.sortOrderDefFilePath = sortOrderDefFilePath;
		this.sortOrderDefCol = sortOrderDefCol;
		intilizeSortOrder();
	}

	private void intilizeSortOrder() throws CsvException, IOException {
		HashMap<String, Integer> sortOrd = new HashMap<String, Integer>();

		CsvParser defParser;
		defParser = new CsvParser(sortOrderDefFilePath, sortOrderDefCol);
		List<CsvItem> defCsvList = defParser.parse();

		for (CsvItem obj : defCsvList) {
			String key = obj.getCvsStr();
			int index = obj.getLineNumber() - 1;
			sortOrd.put(key, index);

		}
		sortOrder = new SortOrder(sortOrd);
		comparator = new CsvComparator(sortOrder);
	}

	public List<CsvItem> getCsvItemList(String fileToBeSorted, String colToSortOn) throws CsvException, IOException {
		CsvParser parser = new CsvParser(fileToBeSorted, colToSortOn);
		List<CsvItem> csvList = parser.parse();
		return csvList;
	}

	public List<CsvItem> getSortedCsvItemList(String fileToBeSorted, String colToSortOn) throws CsvException, IOException {
		CsvParser parser = new CsvParser(fileToBeSorted, colToSortOn);
		List<CsvItem> csvList = parser.parse();

		// check if the csv contains valid data
		// if the definition file doesn't include the data to be sorted then its
		// considered as invalid data
		for (CsvItem item : csvList) {
			if (sortOrder.getSortIndex(item.getSortColValue()) == null) {
				throw new CsvException(fileToBeSorted, item.getCvsStr(), ErrorMessages.ERR_MSG_INVALID_SORT_DATA);
			}
		}
		// if valid data then continue with sort
		Collections.sort(csvList, comparator);
		return csvList;
	}
}
