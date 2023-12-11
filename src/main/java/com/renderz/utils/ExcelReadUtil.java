package com.renderz.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelReadUtil {
	public static void main(String[] args) throws IOException {
		String filePath = ".//src/main/java/com/renderz/datafiles/positions.xlsx";
		FileInputStream fis = new FileInputStream(filePath);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();

		System.out.println("Rows:--> " + rows + "Cols:--> " + cols);

		workbook.close();
	}

}