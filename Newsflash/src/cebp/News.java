package cebp;

import java.util.*;

public class News 
{
	private int id;
	private String title;
	private String category;
	private String content;
	private String author;
	private String publishDate;
	private String modifyDate;
	private int numberOfClients;
	
	public News(int id, String title, String category, String content, String author, String publishDate, String modifyDate) {
		this.id = id;
		this.title=title;
		this.category=category;
		this.content=content;
		this.author=author;
		this.publishDate=publishDate;
		this.modifyDate=modifyDate;
		numberOfClients = 0;
	}
	
	public int getID() {
		return id;
   }
	
	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public int getNumberOfClients() {
		return numberOfClients;
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
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}
	
	public String toString() {
		return this.title+" "+this.category+" "+this.content;
	}
}