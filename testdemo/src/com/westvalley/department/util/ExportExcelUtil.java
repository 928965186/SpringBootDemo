package com.westvalley.department.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelUtil {

	public static void main(String[] args) throws Exception {

		ExportExcelUtil export = new ExportExcelUtil();
		String srcFilePath = "h:/人员信息模板.xlsx";
		String fileName = "test_" + System.currentTimeMillis() + ".xlsx";
		String desFilePath = "h:/" + fileName;

		export.exportExcel(srcFilePath, desFilePath);
	}

	// 根据指定的excel模板导出数据
	public void exportExcel(String srcFilePath, String desFilePath) throws Exception {

		// 创建Excel文件的输入流对象
		FileInputStream fis = new FileInputStream(srcFilePath);
		// 根据模板创建excel工作�?
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		// 创建Excel文件输出流对�?
		FileOutputStream fos = new FileOutputStream(desFilePath);
		// 获取创建的工作簿第一�?
		XSSFSheet sheet = workBook.getSheetAt(0);
		// 给指定的sheet命名
		workBook.setSheetName(0, "2016-11-30");

	/*	// 修改标题
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		// 获取指定单元格�??
		String s = cell.getStringCellValue();
		cell.setCellValue("修改后的标题�?:" + s);*/

		// 获取当前sheet�?后一行数据对应的行索�?
		int currentLastRowIndex = sheet.getLastRowNum();
		int newRowIndex = currentLastRowIndex + 1;
		XSSFRow newRow = sheet.createRow(newRowIndex);
		// �?始创建并设置该行每一单元格的信息，该行单元格的索引从 0 �?�?
		int cellIndex = 0;

		// 创建�?个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
		XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
		newNameCell.setCellValue("小宝");
		XSSFCell newGenderCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
		newGenderCell.setCellValue("�?");
		XSSFCell newAgeCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
		newAgeCell.setCellValue(25);
		XSSFCell newAddressCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
		newAddressCell.setCellValue("南宁兴宁�?");

		workBook.write(fos);

		// 关闭�?
		fis.close();
		fos.flush();
		fos.close();
		System.out.println("导出成功");
	}
}
