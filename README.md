# CsvSortRepository
CSV Sorting application
This is a standalone java application to sorts csv input file on given column name 
as per sorting definition given in another csv file.

Main class: CsvSorterApplication

Application can be run as:
java CsvSorterApplication [file-to-be-sorted] [column-to-sort-on] [sort-order-definition-file] [sort-order-definition-column]

Example:
"C:/HandM/inputfiles_2/InputFile.csv"  "b"  "C:/HandM/inputfiles_2/SortOrder.csv" "d"

If the column to be sorted contains value which is not in sort order definition file then error is displayed.

If the csv files have incorrect format, i.e missing header line, mismatch in header column length and data column length, then error is displayed.
