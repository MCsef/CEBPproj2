package cebp;

public class Event<T> 
{
	private String type;
	private ProducerInterface<T> emmiter;
	
	public Event(String type) 
	{
		this.type = type;
	}
	public ProducerInterface<T> getEmmiter() 
	{
		return this.emmiter;
	}
	public void setEmmiter(ProducerInterface<T> emmiter) 
	{
		this.emmiter = emmiter;
	}
	public String getType() 
	{
		return this.type;
	}
	public String toString() 
	{
		return this.type;
	}
}
