package cebp;

import java.util.ArrayList;

public class Editor implements ProducerInterface<News>, ConsumerInterface<News>
{
	private Dispatcher<News> dispatcher;
	private String name;
	private ArrayList<News> editorNews = new ArrayList<News>();
	private String registerEvent;
	public Editor(Dispatcher<News> dispatcher, String name) 
	{
		this.dispatcher = dispatcher;
		this.name = name;
		this.dispatcher.registerListener(new Event<News>("UPDATE_READER_NEWS"), this);
		this.registerEvent = this.name + "_REGISTE_AUTHOR_NEWS";
		this.dispatcher.registerListener(new Event<News>(registerEvent), this);
		News filterNews = new News("", "", "", "", null, null);
		this.sendNewRegisterEvent(filterNews);
	}
	@Override
	public void consume(Event<News> e, News data)
	{
		// TODO Auto-generated method stub
		System.out.println("Editor " + this.name + " consume event: " + e.getType());
		switch (e.getType())
		{
			case "UPDATE_READER_NEWS":
				this.updateClientsFromNews(data);
				break;
			default:
				if (e.getType().equals(this.registerEvent)) 
				{
					this.editorNews.add(data);
				} 
				else
					System.out.println("Case not found in Editor");
				break;
		}
	}
	@Override
	public void produce(Event<News> e, News data)
	{
		// TODO Auto-generated method stub
		this.dispatcher.sendEvent(e, data);
	}
	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return this.name;
	}
	public void sendNewRegisterEvent(News n) 
	{
		Event<News> registerEvent = new Event<News>("NEW_REGISTER_EDITOR");
		registerEvent.setEmmiter(this);
		this.produce(registerEvent, n);
	}
	private void updateClientsFromNews(News n) 
	{
		for (News news : this.editorNews) 
		{
			if (news.equals(n)) 
			{
				Event<News> e = new Event<News>("EDITOR_" + name + "_VIEW_UPDATE_CLIENTS");
				e.setEmmiter(this);
				this.produce(e, n);
				news = n;
			}
		}
	}
}
