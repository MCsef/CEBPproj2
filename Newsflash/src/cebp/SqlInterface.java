package cebp;

import java.util.ArrayList;

public interface SqlInterface<T> 
{
	public void create(T t);
	public T get(String id);
	public ArrayList<T> getAll();
	public void update(T t);
	public void delete (int id);	
}