package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	//����properties�ļ�
	static{
		Properties props = new Properties();
		InputStream is = ConnectionFactory.class
			.getResourceAsStream("message.properties");
		try {
			props.load(is);
			DRIVER = props.getProperty("oracle.driver");
			URL = props.getProperty("oracle.url");
			USER = props.getProperty("oracle.user");
			PASSWORD = props.getProperty("oracle.password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//��������������Connection����
	public static Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager
					.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args){
		System.out.println(ConnectionFactory.getConnection());
	}
	
}
