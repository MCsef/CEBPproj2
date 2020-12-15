package cebp;

import java.util.ArrayList;

public interface INews {
	
	public void create(News n);
	public News get(int id);
	public ArrayList<News> getAll();
	public void update(News n);
	public void delete(int id);
}
