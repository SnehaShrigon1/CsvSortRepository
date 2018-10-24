package com.ibm.hm.csv.messages;

public interface ErrorMessages {

	String ERR_MSG_INVALID_COL_NAME = "Invalid column name";
	String ERR_MSG_INVALID_CSV_FILE = "Invalid csv file, no values to read";
	String ERR_MSG_EMPTY_CSV_FILE = "Invalid csv file, file may be empty";
	String ERR_MSG_INCORRECT_DATA_COLS = "Number of data columns do not match number of header columns";
	String ERR_MSG_INVALID_SORT_DATA = "Invalid sort data";
	String ERR_MSG_FIEL_DO_NOT_EXIST = "Input file(s) do not exist";

}
