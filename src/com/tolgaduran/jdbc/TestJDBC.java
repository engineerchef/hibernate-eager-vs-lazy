package com.tolgaduran.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	public static void main(String[] args) {
		String jdbcUrl="jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false";
		String user="hbstudent";
		String pass="hbstudent";
		try {
			Connection conn=DriverManager.getConnection(jdbcUrl,user,pass);
			System.out.println("Connection successful!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
