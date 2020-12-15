package cebp;

import java.util.ArrayList;
import java.util.HashMap;

public class Dispatcher<T> 
{
	private HashMap<String, ArrayList<ConsumerInterface<T>>> consumersMap = new HashMap<>();
	public void sendEvent(Event<T> e, T data) 
	{
		ArrayList<ConsumerInterface<T>> consumers = this.consumersMap.get(e.getType());
		if (consumers !=null && consumers.size() > 0) 
		{
			for (ConsumerInterface<T> consumer : consumers) 
			{
				consumer.consume(e, data);
			}
		}
	}
	public void registerListener(Event<T> e, ConsumerInterface<T> c) 
	{
		ArrayList<ConsumerInterface<T>> consumers = new ArrayList<>();
		if (this.consumersMap.containsKey(e.getType()))
			consumers = this.consumersMap.get(e.getType());
		consumers.add(c);
		this.consumersMap.put(e.getType(), consumers);
	}
	public void removeListener(Event<T> e, ConsumerInterface<T> c) 
	{
		ArrayList<ConsumerInterface<T>> consumers = new ArrayList<>();
		if (this.consumersMap.containsKey(e.getType())) 
		{
			consumers = this.consumersMap.get(e.getType());
			consumers.remove(c);
			this.consumersMap.put(e.getType(), consumers);
		}
	}
	public String toString() 
	{
		return this.consumersMap.toString();	
	}
}
