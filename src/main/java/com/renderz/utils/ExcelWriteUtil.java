package com.renderz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

public class ExcelWriteUtil {
	private static Workbook workbook;
	private static FileOutputStream fos;
	private static FileInputStream fis;
	private static XSSFSheet sheet;
	private static XSSFRow currRow;
	private static XSSFCell currCell;
	static String filePath = ".//src/main/java/com/renderz/datafiles/prices.xlsx";

	public static void imprtToExcel(int row, int col, String data) throws IOException {
		File file = new File(filePath);

		// If file not exists, create new
		if (!file.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(filePath);
			workbook.write(fos);
		}
		// If file already exists
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);

		sheet = (XSSFSheet) workbook.getSheetAt(0);

		// If any row not exists
		if (sheet.getRow(row) == null) {
			sheet.createRow(row);
		}
		// If row already exists
		currRow = sheet.getRow(row);

		currCell = currRow.createCell(col);
		currCell.setCellValue(data);

		fos = new FileOutputStream(filePath);
		workbook.write(fos);

		workbook.close();
		fos.close();
		fis.close();
	}
}