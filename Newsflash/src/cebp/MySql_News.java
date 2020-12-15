package cebp;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySql_News implements INews{

	private Connection con;
	private Statement st;
	private String database;
	private ResultSet rs;
	
	public MySql_News(Connection con, String database) {
		this.con = con;
		this.database = database;
		
		try {
			this.st = this.con.createStatement();
		}catch (SQLException e) {
			System.out.println("Statement error at creation: "+e);
			e.printStackTrace();
		}
	}

	@Override
	public void create(News n) {
		String info = " values ('" + n.getID() + "', '" + n.getTitle() + "', '" + n.getAuthor() + "', '"
				+ n.getCategory() + "', '" + n.getContent() + "', '" + n.getPublishDate() + "', '" + n.getModifyDate() + "')";
		String qry = "insert into " + this.database+ " (id, title, category, content, author, publishDate, modifyDate) "+ info;
		try {
			this.st.execute(qry);
		} catch (SQLException e) {
			System.out.println("Error while inserting news into database: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public News get(int ID) {
		try {
			PreparedStatement qry = this.con.prepareStatement("select * from " + this.database + " where id=?");
			qry.setInt(1, ID);
			this.rs = qry.executeQuery();

			while (this.rs.next()) {
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

		} catch (SQLException e) {
			System.out.println("Error while getting news from the database: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<News> getAll() {
		ArrayList<News> news = new ArrayList<>();
		String qry = "select * from " + this.database;

		try {
			this.rs = this.st.executeQuery(qry);
			while (this.rs.next()) {
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
		} catch (SQLException e) {
			System.out.println("Error in retrieval of the complete set of news: " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(News n) {
		try {
			PreparedStatement qry = this.con.prepareStatement("UPDATE " + this.database + " set title=?, category=?, content=? , author=?, publishDate=?, modifyDate=? where id=?");
			qry.setString(1, n.getTitle());
			qry.setString(2, n.getCategory());
			qry.setString(3, n.getContent());
			qry.setString(4, n.getAuthor());
			qry.setDate(5, (Date) n.getPublishDate());
			qry.setDate(6, (Date) n.getModifyDate());
			qry.setInt(7, n.getID());

			qry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error in updating the news: " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			PreparedStatement qry = this.con.prepareStatement("DELETE from " + this.database + " where id=?");
			qry.setInt(1, id);
			qry.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error in deleting one of the news: " + e);
			e.printStackTrace();
		}
	}
	
	public ArrayList<News> getByCategory(String filter) {
		try {
			ArrayList<News> news = new ArrayList<>();
			PreparedStatement qry = this.con.prepareStatement("select * from " + this.database + " where category=?");
			qry.setString(1, filter);

			this.rs = qry.executeQuery();
			while (this.rs.next()) {
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
		} catch (SQLException e) {
			System.out.println("Error in getting the proper news by filtration: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<News> getByAuthor(String filter) {
		try {
			ArrayList<News> news = new ArrayList<>();
			PreparedStatement qry = this.con.prepareStatement("select * from " + this.database + " where author=?");
			qry.setString(1, filter);

			this.rs = qry.executeQuery();
			while (this.rs.next()) {
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
		} catch (SQLException e) {
			System.out.println("Error in getting the proper news by filtration: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
}
