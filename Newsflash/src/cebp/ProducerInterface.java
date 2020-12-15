package cebp;

public interface ProducerInterface<T> 
{
	public void produce(Event<T> e, T data);
	public String getName();
}
