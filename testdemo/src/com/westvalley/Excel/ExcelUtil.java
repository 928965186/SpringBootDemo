package com.westvalley.Excel;

import java.io.File;

import java.util.ArrayList;
import java.util.List;



import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	public static void getExcel(List<Object> list) {
	
			try {
				// ���ļ�
				WritableWorkbook book = Workbook.createWorkbook(new File("c:/image/�ͻ���Ϣ��.xls"));
				// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
				WritableSheet sheet = book.createSheet("��һҳ", 0);
				sheet.addCell(new Label(0, 0, "�ͻ�����"));
				sheet.addCell(new Label(1, 0, "����ˮƽ"));
				sheet.addCell(new Label(2, 0, "�ͻ��ֻ���"));
				sheet.addCell(new Label(3, 0, "�������"));
				sheet.addCell(new Label(4, 0, "�ͻ�QQ"));
				sheet.addCell(new Label(5, 0, "�ͻ�����"));
				sheet.addCell(new Label(6, 0, "�ͻ�״̬"));
				sheet.addCell(new Label(7, 0, "������"));
				
				int i=0;
						sheet.setRowView(i, 500);
						sheet.setColumnView(i, 30);
						
						
						sheet.addCell(new Label(0, i + 1, "1"));
						sheet.addCell(new Label(1, i + 1, "1"));
						sheet.addCell(new Label(2, i + 1, "1"));
						sheet.addCell(new Label(3, i + 1, "1"));
						sheet.addCell(new Label(4, i + 1, "1"));
						sheet.addCell(new Label(5, i + 1, "1"));
						sheet.addCell(new Label(6, i + 1, "1"));
						sheet.addCell(new Label(7, i + 1, "1"));
					
				
				book.write();
				book.close();
				System.out.println("��ӡ�ɹ�");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��ӡʧ��");
			}
		}
	

	
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
	
		ExcelUtil.getExcel(list);
	}
}
