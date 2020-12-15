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
		News filterNews = new News(0,"", "", "", "", null, null);
		this.sendNewRegisterEvent(filterNews);
	}
	
	@Override
	public void consume(Event<News> e, News data)
	{
		// TODO Auto-generated method stub
		System.out.println("Editor " + this.name + " consume event: " + e.getType());
		switch (e.getType())
		{
			case "UPDATE_CLIENT_NEWS":
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
	
	public void addNews(News n) {
		if (!this.editorNews.contains(n))
			this.editorNews.add(n);
	}

	public ArrayList<News> getEditorNews() {
		return editorNews;
	}

	public News getNews(int newsID) {
		News n = null;
		for (News nn : this.editorNews) {
			if (nn.getID() == newsID) {
				n = nn;
				break;
			}
		}
		return n;
	}

	public void publishNews(News n) {
		Event<News> createNews = new Event<News>("CREATE_NEWS");
		createNews.setEmmiter(this);
		Event<News> createNewsCategory = new Event<News>(n.getCategory() + "_NEWS");
		createNewsCategory.setEmmiter(this);
		Event<News> createNewsAuthor = new Event<News>(n.getAuthor() + "_NEWS");
		createNewsAuthor.setEmmiter(this);
		Event<News> createNewsAuthorCategory = new Event<News>(n.getAuthor() + "_$" + n.getCategory() + "$_NEWS");
		createNewsAuthorCategory.setEmmiter(this);
		this.produce(createNews, n);
		this.produce(createNewsCategory, n);
		this.produce(createNewsAuthorCategory, n);
		this.produce(createNewsAuthor, n);
	}
	
	public void updateNews(News n) {
		Event<News> updateNews = new Event<News>("UPDATE_NEWS");
		updateNews.setEmmiter(this);
		Event<News> updateNewsForClient = new Event<News>("UPDATE_" + n.getCategory() + "_NEWS");
		updateNewsForClient.setEmmiter(this);
		Event<News> updateNewsForClientAuthor = new Event<News>("UPDATE_" + n.getAuthor() + "_NEWS");
		updateNewsForClientAuthor.setEmmiter(this);
		Event<News> updateNewsForClientAuthorCategory = new Event<News>("UPDATE_" + n.getCategory() + "_$" + n.getAuthor() + "$_NEWS");
		updateNewsForClientAuthorCategory.setEmmiter(this);
		this.produce(updateNewsForClientAuthorCategory, n);
		this.produce(updateNewsForClientAuthor, n);
		this.produce(updateNewsForClient, n);
		this.produce(updateNews, n);
	}

	public void deleteNews(News n) {
		Event<News> deleteNews = new Event<News>("DELETE_NEWS");
		deleteNews.setEmmiter(this);
		this.produce(deleteNews, n);
		this.removeNews(n);
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
	
	private void removeNews(News nToDelete) {
		News n = this.getNews(nToDelete.getID());
		if (n != null)
			this.editorNews.remove(n);
	}
}
