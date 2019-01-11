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
				// 打开文件
				WritableWorkbook book = Workbook.createWorkbook(new File("c:/image/客户信息表.xls"));
				// 生成名为“第一页”的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet("第一页", 0);
				sheet.addCell(new Label(0, 0, "客户姓名"));
				sheet.addCell(new Label(1, 0, "教育水平"));
				sheet.addCell(new Label(2, 0, "客户手机号"));
				sheet.addCell(new Label(3, 0, "添加日期"));
				sheet.addCell(new Label(4, 0, "客户QQ"));
				sheet.addCell(new Label(5, 0, "客户邮箱"));
				sheet.addCell(new Label(6, 0, "客户状态"));
				sheet.addCell(new Label(7, 0, "邀请人"));
				
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
				System.out.println("打印成功");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("打印失败");
			}
		}
	

	
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
	
		ExcelUtil.getExcel(list);
	}
}
