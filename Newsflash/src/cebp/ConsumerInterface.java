package cebp;

public interface ConsumerInterface<T> 
{
	public void consume(Event<T> e, T data);
	public String getName();
}
