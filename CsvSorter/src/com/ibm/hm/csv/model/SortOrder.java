package com.ibm.hm.csv.model;

import java.util.HashMap;

/**
 * 
 * @author Sneha
 * 
 */
public class SortOrder {

	private HashMap<String, Integer> sortOrder = new HashMap<String, Integer>();

	public SortOrder(HashMap<String, Integer> sortOrder1) {
		sortOrder = sortOrder1;
	}

	public Integer getSortIndex(String value) {
		return sortOrder.get(value);
	}

}
