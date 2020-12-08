package cebp;

import java.util.*;

public class News {
	private String title;
	private String category;
	private String content;
	private String author;
	private Date publishDate;
	private Date modifiedDate;
	
	public News(String title, String category, String content, String author, Date publishDate) {
		this.title=title;
		this.category=category;
		this.content=content;
		this.author=author;
		this.publishDate=publishDate;
		this.modifiedDate=publishDate;
	}
	
	public void deleteNews() {
		
	}
	
	public void updateNews() {
		
	}

}
