package cebp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlFactory 
{
	private DatabaseInfo db;
	private Connection conn = null;;
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public SqlFactory(DatabaseInfo c) 
	{
		this.db = c;
	}
	public SqlFactory() 
	{
		this.db = new DatabaseInfo("root", "password", "127.0.0.1", "cepb_newsflash", 8080);
	}
	public SqlNews getNews() 
	{
		this.getConnection();
		if (this.conn == null)
			return null;
		else
			return new SqlNews(this.conn, this.db.getDatabase());
	}
	private void getConnection() 
	{
		try 
		{
			this.conn = DriverManager.getConnection("jdbc:mysql://" + this.db.getUrl() + this.timeZone,this.db.getUsername(), this.db.getPassword());
		} 
		catch (SQLException e) 
		{
			this.conn = null;
			System.out.println("Error while connectiong to database : " + e);
			e.printStackTrace();
		}
	}
}
