package com.ibm.hm.csv.util;

import java.util.Comparator;

import com.ibm.hm.csv.model.CsvItem;
import com.ibm.hm.csv.model.SortOrder;

/**
 * Comparator implementation for comparing two CsvItem objects.
 * 
 * Predefined sorting order is referred while comparing.
 * 
 * @author Sneha
 * 
 */
public class CsvComparator implements Comparator<CsvItem> {
	private SortOrder sortOrder;

	public CsvComparator(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(CsvItem object1, CsvItem object2) {
		Integer i1 = sortOrder.getSortIndex(object1.getSortColValue());
		Integer i2 = sortOrder.getSortIndex(object2.getSortColValue());
		return i1.compareTo(i2);
	}
}
