package com.ibm.hm.csv.model;

/**
 * 
 * @author Sneha
 * 
 */
public class CsvItem {

	private String cvsStr;

	private String sortColValue;

	private int lineNumber = 0;

	public String getCvsStr() {
		return cvsStr;
	}

	public void setCvsStr(String cvsStr) {
		this.cvsStr = cvsStr;
	}

	public String getSortColValue() {
		return sortColValue;
	}

	public void setSortColValue(String sortColValue) {
		this.sortColValue = sortColValue;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void print() {
		System.out.println(cvsStr);
	}
}
