package cebp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class SqlNews implements INews
{
	private Connection conn;
	private Statement st;
	private String database;
	private ResultSet rs;
	
	public SqlNews(Connection conn, String db)
	{
		this.conn = conn;
		this.database = db;
		try 
		{
			this.st = this.conn.createStatement();
		} 
		catch (SQLException e)
		{
			System.out.println("Cannot create sql statement: " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(News n) 
	{
		// TODO Auto-generated method stub
		String data = " values ('" + n.getID() + "', '" + n.getTitle() + "', '" + n.getCategory() + "', '"+ n.getContent() + "', '" + n.getAuthor() + "', '" + n.getPublishDate() + "', '" + n.getModifyDate() + "')";
		String querry = "insert into " + this.database + " (id, title, category, content, author, publishDate, modifyDate) " + data;
		try 
		{
			this.st.execute(querry);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error while inserting news: " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public News get(int ID) 
	{
		try 
		{
			PreparedStatement querry = this.conn.prepareStatement("select * from " + this.database + " where identifier=?");
			querry.setInt(1, ID);
			this.rs = querry.executeQuery();
			while (this.rs.next()) 
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Date publishDate = new Date(rs.getLong("publishDate"));
				Date modifyDate = new Date(rs.getLong("modifyDate"));
				News n = new News(id, title, category, content, author, publishDate, modifyDate);
				return n;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Error while getting news: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<News> getAll() 
	{
		ArrayList<News> news = new ArrayList<>();
		String querry = "select * from " + this.database;
		try 
		{
			this.rs = this.st.executeQuery(querry);
			while (this.rs.next()) 
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Date publishDate = new Date(rs.getLong("publishDate"));
				Date modifyDate = new Date(rs.getLong("modifyDate"));
				News n = new News(id, title, category, content, author, publishDate, modifyDate);
				news.add(n);
			}
			return news;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while getting all news: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void update(News n) 
	{
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement querry = this.conn.prepareStatement("UPDATE " + this.database + " set title=?, category=?, content=?, author=?, publishDate=?, modifyDate=? where identifier=?");
			querry.setString(1, n.getTitle());
			querry.setString(2, n.getCategory());
			querry.setString(3, n.getContent());
			querry.setString(4, n.getAuthor());
			querry.setLong(6, n.getPublishDate().getTime());
			querry.setLong(7, n.getModifyDate().getTime());
			querry.executeUpdate();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while updating news: " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(int id) 
	{
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement querry = this.conn.prepareStatement("DELETE from " + this.database + " where id=?");
			querry.setInt(1, id);
			querry.executeUpdate();

		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while deleting news: " + e);
			e.printStackTrace();
		}
	}
	
	public PreparedStatement getFilter(String query) {
		try {
			PreparedStatement querry = this.conn.prepareStatement(query);
			return querry;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<News> getByAuthor(String filter) 
	{
		try 
		{
			ArrayList<News> news = new ArrayList<>();
			PreparedStatement querry = this.conn.prepareStatement("select * from " + this.database + " where author=?");
			querry.setString(1, filter);
			this.rs = querry.executeQuery();
			while (this.rs.next()) 
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Date publishDate = new Date(rs.getLong("publishDate"));
				Date modifyDate = new Date(rs.getLong("modifyDate"));
				News n = new News(id, title, category, content, author, publishDate, modifyDate);
				news.add(n);
			}
			return news;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while getting filtered news: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<News> getByCategory(String filter) 
	{
		try 
		{
			ArrayList<News> news = new ArrayList<>();
			PreparedStatement querry = this.conn.prepareStatement("select * from " + this.database + " where category=?");
			querry.setString(1, filter);
			this.rs = querry.executeQuery();
			while (this.rs.next()) 
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Date publishDate = new Date(rs.getLong("publishDate"));
				Date modifyDate = new Date(rs.getLong("modifyDate"));
				News n = new News(id, title, category, content, author, publishDate, modifyDate);
				news.add(n);
			}
			return news;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while getting filtered news: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<News> getByCategoryAndAuthor(String categoryFilter, String authorFilter) 
	{
		try 
		{
			ArrayList<News> news = new ArrayList<>();
			PreparedStatement querry = this.conn.prepareStatement("select * from " + this.database + " where category=? and author=?");
			querry.setString(1, categoryFilter);
			querry.setString(2, authorFilter);
			this.rs = querry.executeQuery();
			while (this.rs.next()) 
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String content = rs.getString("content");
				String author = rs.getString("author");
				Date publishDate = new Date(rs.getLong("publishDate"));
				Date modifyDate = new Date(rs.getLong("modifyDate"));
				News n = new News(id, title, category, content, author, publishDate, modifyDate);
				news.add(n);
			}
			return news;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Error while getting filtered news: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
}
