package cebp;

public class DatabaseInfo 
{
	private String username;
	private String password;
	private String hostname;
	private String database;
	private int port;
	public DatabaseInfo(String username, String password, String hostname, String database, int port) 
	{
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.database = database;
		this.port = port;
	}
	public String getUrl() 
	{
		return this.hostname + ":" + this.port + "/" + this.database;
	}
	public String getUsername() 
	{
		return username;
	}
	public String getPassword() 
	{
		return password;
	}
	public String getHostname() 
	{
		return hostname;
	}
	public int getPort()
	{
		return port;
	}
	public String getDatabase()
	{
		return database;
	}
}