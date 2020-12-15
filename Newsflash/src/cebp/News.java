package cebp;

import java.util.*;

public class News 
{
	private int id;
	private String title;
	private String category;
	private String content;
	private String author;
	private Date publishDate;
	private Date modifyDate;
	
	public News(int id, String title, String category, String content, String author, Date publishDate, Date modifyDate) {
		this.id = id;
		this.title=title;
		this.category=category;
		this.content=content;
		this.author=author;
		this.publishDate=publishDate;
		this.modifyDate=modifyDate;
	}
	
	public int getID() {
		return id;
   }
	
	public String getTitle() {
		return title;

	public String getCategory() {
		return category;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setCategory(String category) {
		this.category=category;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}