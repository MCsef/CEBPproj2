package cebp;

import java.util.ArrayList;

public class Consumer implements ConsumerInterface<News>
{
	private SqlNews sqlNews;
	private Producer producer;
	private final String name = "Consumer";
	public Consumer(SqlNews sqlNews, Producer producer) 
	{
		this.sqlNews = sqlNews;
		this.producer = producer;
	}
	public Consumer(Producer producer) 
	{
		JDBC factory = new JDBC();
		this.sqlNews = factory.getNews();
		this.producer = producer;
	}
	public void registerEvents(Dispatcher<News> disp) 
	{
		Event<News> createNews = new Event<News>("CREATE_NEWS");
		disp.registerListener(createNews, this);
		Event<News> updateNews = new Event<News>("UPDATE_NEWS");
		disp.registerListener(updateNews, this);
		Event<News> updateClientNews = new Event<News>("UPDATE_CLIENT_NEWS");
		disp.registerListener(updateClientNews, this);
		Event<News> deleteNews = new Event<News>("DELETE_NEWS");
		disp.registerListener(deleteNews, this);
		Event<News> newClient = new Event<News>("NEW_REGISTER_CLIENT");
		disp.registerListener(newClient, this);
		Event<News> newEditor = new Event<News>("NEW_REGISTER_EDITOR");
		disp.registerListener(newEditor, this);
	}
	@Override
	public void consume(Event<News> e, News data) 
	{
		// TODO Auto-generated method stub
		System.out.println("Consumer consumes event: <" + e.getType() + ">");
		switch (e.getType())
		{
			case "CREATE_NEWS":
				this.sqlNews.create(data);
				break;
			case "UPDATE_NEWS":
				this.sqlNews.update(data);
				break;
			case "UPDATE_CLIENT_NEWS":
				this.sqlNews.update(data);
				break;
			case "DELETE_NEWS":
				this.sqlNews.delete(data.getID());
				break;
			case "NEW_REGISTER_CLIENT":
				if (e.getEmmiter() != null)
					this.newRegisterClient(e, data);
				break;
			case "NEW_REGISTER_EDITOR":
				if (e.getEmmiter() != null) 
				{
					Event<News> newEvent = new Event<>(e.getEmmiter().getName() + "_REGISTE_AUTHOR_NEWS");
					ArrayList<News> news = this.sqlNews.getByAuthor(data.getAuthor());
					if (news.size() > 0)
						for (News n : news)
							this.producer.produce(newEvent, n);
				}
				break;
			default:
				System.out.println("Case not found in NewsPersistentConsumer: <" + e.getType() + ">");
				break;
		}
	}
	@Override
	public String getName() 
	{
		// TODO Auto-generated method stub
		return this.name;
	}
	private void newRegisterClient(Event<News> e, News data) 
	{
		Event<News> newEvent = null;
		ArrayList<News> news = null;
		switch (data.getCategory())
		{
			case "c": 
			{
				newEvent = new Event<>(e.getEmmiter().getName() + "_" + data.getCategory() + "_NEWS");
				news = this.sqlNews.getByCategory(data.getCategory().toString());
				break;
			}
			case "a": 
			{
				newEvent = new Event<>(e.getEmmiter().getName() + "_" + data.getAuthor() + "_NEWS");
				news = this.sqlNews.getByAuthor(data.getAuthor());
				break;
			}
			case "ca":
			{
				newEvent = new Event<>(e.getEmmiter().getName() + "_" + data.getCategory() + "_$" + data.getAuthor() + "$_NEWS");
				news = this.sqlNews.getByCategoryAndAuthor(data.getCategory(), data.getAuthor().toString());
				break;
			}
		}
		if (news != null && news.size() > 0)
			for (News n : news)
				this.producer.produce(newEvent, n);
	}
}