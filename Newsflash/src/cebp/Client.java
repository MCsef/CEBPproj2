package cebp;

import java.util.ArrayList;

public class Client implements ConsumerInterface<News>, ProducerInterface<News>
{
	private String name;
	private Dispatcher<News> dispatcher;
	private ArrayList<News> news = new ArrayList<>();
	public Client(Dispatcher<News> dispatcher, String name) {
		this.dispatcher = dispatcher;
		this.name = name;
	}
	public void addEvent(String eType) 
	{
		this.dispatcher.registerListener(new Event<News>(eType + "_NEWS"), this);
		this.dispatcher.registerListener(new Event<News>(this.name + "_" + eType + "_NEWS"), this);
		this.dispatcher.registerListener(new Event<News>("UPDATE_" + eType + "_NEWS"), this);
		News filterNews = new News("", "", "", "", null, null);
		this.sendNewRegisterEvent(filterNews);
	}
	@Override
	public void produce(Event<News> e, News data) 
	{
		// TODO Auto-generated method stub
		this.dispatcher.sendEvent(e, data);
	}
	@Override
	public void consume(Event<News> e, News data) 
	{
		// TODO Auto-generated method stub
		System.out.println("Client " + this.name + " consume event: " + e.getType());
		if (e.getType().contains("UPDATE_"))
		{
			this.updateNews(data);
		} 
		else 
		{
			this.addNews(data);
			if (!e.getType().contains(this.name)) { // there are no old news
				Event<News> event = new Event<News>("READER_" + this.getName() + "_VIEW_UPDATE_TABLE");
				event.setEmmiter(this);
				this.produce(event, data);
			}
		}
	}
	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return this.name;
	}
	public ArrayList<News> getNews() 
	{
		return this.news;
	}
	private void sendNewRegisterEvent(News n) 
	{
		Event<News> registerEvent = new Event<News>("NEW_REGISTER_READER");
		registerEvent.setEmmiter(this);
		this.produce(registerEvent, n);
	}
	private void updateNews(News n) 
	{
		for (News news : this.news)
		{
			if (news.getID() == (n.getID())) 
			{
				news = n;
				break;
			}
		}
		Event<News> event = new Event<News>("CLIENT_" + this.getName() + "_VIEW_UPDATE_TABLE_NEWS_UPDATE");
		event.setEmmiter(this);
		this.produce(event, n);
	}

	private void addNews(News n) 
	{
		if (!this.news.contains(n))
			this.news.add(n);
	}

}
