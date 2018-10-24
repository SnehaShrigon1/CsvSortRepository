package com.ibm.hm.csv.exceptions;

/**
 * 
 * @author Sneha
 * 
 */
public class CsvException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName = "";
	private String line = "";
	private String errorMsg = "";
	private Exception exc = null;

	public CsvException(String fileName, String errorMsg) {
		super();
		this.fileName = fileName;
		this.errorMsg = errorMsg;
	}

	public CsvException(String fileName, String line, String errorMsg) {
		super();
		this.fileName = fileName;
		this.line = line;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getMessage() {
		StringBuffer strblr = new StringBuffer(errorMsg);
		strblr.append(" in file: ").append(fileName);
		if (line != null && !line.isEmpty()) {
			strblr.append(", line:" + line);
		}
		return strblr.toString();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		if (exc == null) {
			return super.getStackTrace();
		}
		return exc.getStackTrace();
	}

	public void setExc(Exception exc) {
		this.exc = exc;
	}

}
