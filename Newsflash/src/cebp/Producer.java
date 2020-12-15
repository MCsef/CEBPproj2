package cebp;

public class Producer implements ProducerInterface<News>
{
	private Dispatcher<News> dispatcher;
	private final String name = "Producer";
	public Producer(Dispatcher<News> dispatcher) 
	{
		this.dispatcher = dispatcher;
	}
	@Override
	public void produce(Event<News> e, News data) 
	{
		// TODO Auto-generated method stub
		e.setEmmiter(this);
		this.dispatcher.sendEvent(e, data);
	}
	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return this.name;
	}
}