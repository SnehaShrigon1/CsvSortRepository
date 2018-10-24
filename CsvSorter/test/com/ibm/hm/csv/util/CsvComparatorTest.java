package com.ibm.hm.csv.util;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ibm.hm.csv.model.CsvItem;
import com.ibm.hm.csv.model.SortOrder;

/**
 * 
 * @author Sneha
 * 
 */
public class CsvComparatorTest {

	SortOrder sortOrder;
	CsvComparator comparatorObj;
	CsvItem item1;
	CsvItem item2;

	@Before
	public void setUp() throws Exception {
		HashMap<String, Integer> sortOrderMap = new HashMap<String, Integer>();
		sortOrderMap.put("6", 1);
		sortOrderMap.put("4", 2);
		sortOrderMap.put("3", 3);
		sortOrderMap.put("2", 4);
		sortOrderMap.put("1", 5);
		sortOrderMap.put("5", 6);

		sortOrder = new SortOrder(sortOrderMap);
		comparatorObj = new CsvComparator(sortOrder);

		item1 = new CsvItem();
		item1.setCvsStr("1,2,3");
		item1.setLineNumber(1);
		item1.setSortColValue("2");

		item2 = new CsvItem();
		item2.setCvsStr("2,3,2");
		item2.setLineNumber(2);
		item2.setSortColValue("3");
	}

	@Test
	public void testTestCompare() {
		Assert.assertEquals(1, comparatorObj.compare(item1, item2));
	}

}
