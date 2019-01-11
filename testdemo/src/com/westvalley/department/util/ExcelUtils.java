package com.westvalley.department.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static List<List<Object>> importExcel(File file) throws IOException{    
        String fileName = file.getName();    
        String extension = fileName.lastIndexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);    
        if("xls".equals(extension)){    
         return read2003Excel(file);    
        }else if("xlsx".equals(extension)){    
         return read2007Excel(file);    
        }else{    
         throw new IOException("��֧�ֵ��ļ�����");    
        }    
     }
	
	/**  
     * ��ȡ office 2003 excel  
     * @throws IOException   
     * @throws FileNotFoundException */    
     private static List<List<Object>> read2003Excel(File file) throws IOException{    
        List<List<Object>> list = new LinkedList<List<Object>>();    
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));    
        HSSFSheet sheet = hwb.getSheetAt(0);    
        Object value = null;    
        HSSFRow row = null;    
        HSSFCell cell = null;     
        for(int i = sheet.getFirstRowNum();i<= sheet.getPhysicalNumberOfRows();i++){    
         row = sheet.getRow(i);    
         if (row == null) {    
          continue;    
         }    
         List<Object> linked = new LinkedList<Object>();    
         for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {    
          cell = row.getCell(j);    
          if (cell == null) {    
           continue;    
          }    
          DecimalFormat df = new DecimalFormat("0");// ��ʽ�� number String �ַ�    
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ�������ַ���    
          DecimalFormat nf = new DecimalFormat("0");// ��ʽ������    
          switch (cell.getCellType()) {    
          case XSSFCell.CELL_TYPE_STRING:    
           value = cell.getStringCellValue();    
           break;    
          case XSSFCell.CELL_TYPE_NUMERIC:    
           if("@".equals(cell.getCellStyle().getDataFormatString())){    
              value = df.format(cell.getNumericCellValue());    
           } else if("General".equals(cell.getCellStyle().getDataFormatString())){    
              value = nf.format(cell.getNumericCellValue());    
           }else{    
             value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));    
           }    
           break;    
          case XSSFCell.CELL_TYPE_BOOLEAN:    
           value = cell.getBooleanCellValue();    
           break;    
          case XSSFCell.CELL_TYPE_BLANK:    
           value = "";    
           break;    
          default:    
           value = cell.toString();    
          }    
          if (value == null || "".equals(value)) {    
           continue;    
          }    
          linked.add(value);      
        }    
         list.add(linked);    
        }    
        return list;    
     }


     /**  
     * ��ȡOffice 2007 excel  
     * */    
     private static List<List<Object>> read2007Excel(File file) throws IOException {    
        List<List<Object>> list = new LinkedList<List<Object>>();    
        // ���� XSSFWorkbook ����strPath �����ļ�·��    
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));    
        // ��ȡ��һ�ű������    
        XSSFSheet sheet = xwb.getSheetAt(0);    
        Object value = null;    
        XSSFRow row = null;    
        XSSFCell cell = null;    
        for (int i = sheet.getFirstRowNum(); i <= sheet    
          .getPhysicalNumberOfRows(); i++) {    
         row = sheet.getRow(i);    
         if (row == null) {    
          continue;    
         }    
         List<Object> linked = new LinkedList<Object>();    
         for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {    
          cell = row.getCell(j);    
          if (cell == null) {    
           continue;    
          }    
          DecimalFormat df = new DecimalFormat("0");// ��ʽ�� number String �ַ�    
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ�������ַ���    
          DecimalFormat nf = new DecimalFormat("0");// ��ʽ������    
          switch (cell.getCellType()) {    
          case XSSFCell.CELL_TYPE_STRING:    
           value = cell.getStringCellValue();    
           break;    
          case XSSFCell.CELL_TYPE_NUMERIC:    
           if("@".equals(cell.getCellStyle().getDataFormatString())){    
             value = df.format(cell.getNumericCellValue());    
           } else if("General".equals(cell.getCellStyle().getDataFormatString())){    
             value = nf.format(cell.getNumericCellValue());    
           }else{    
            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));    
           }    
           break;    
          case XSSFCell.CELL_TYPE_BOOLEAN:    
           value = cell.getBooleanCellValue();    
           break;    
          case XSSFCell.CELL_TYPE_BLANK:    
           value = "";    
           break;    
          default:    
           value = cell.toString();    
          }    
          if (value == null || "".equals(value)) {    
           continue;    
          }    
          linked.add(value);    
         }    
         list.add(linked);    
        }    
        return list;    
     }    


         public static void main(String[] args) throws IOException  { 
//           excel ��������demo
             File file = new File("C:/Users/john/Downloads/Excel.xls");  
             List<List<Object>> dataList=importExcel(file);  
             for (int i = 1; i < dataList.size(); i++) {
                 for (int j = 1; j < dataList.get(i).size(); j++) {
                     System.out.println(dataList.get(i).get(j));
                 }
                 
             }
         }  
}

