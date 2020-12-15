package cebp;

import java.sql.*;

public class JDBC {
	private String username = "root";
	private String password = "password";
	private String database = "news";
	private int port = 3306;
	
	private Connection con = null;
	
	private void connect() {
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/news_db",username,password);
		} catch (SQLException e) {
			this.con = null;
			System.out.println("Database connection error:"+e);
			e.printStackTrace();
		}
	}
	
	public SqlNews getNews() {
		this.connect();
		if(this.con == null) {
			return null;
		}
		else return new SqlNews(this.con, database);
	}
}
