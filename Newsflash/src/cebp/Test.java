package cebp;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		System.out.println("Hello world");
		Date d = new Date();
		System.out.println(d);
		System.out.println(d.getTime());
		System.out.println(new Date(d.getTime()));

//		MySqlDAOFactory f = new MySqlDAOFactory();
//		MySqlNewsDAO ff = f.getNewsDAO();
//
//		News n = new News("news1", "author", "information dasdas ds adafddsa sd fsda", NewsDomain.valueOf("EDUCATION"),
//				d, d);
//		News nn = new News("news2", "author", "information dasdas ds adafddsa sd fsda", NewsDomain.valueOf("SPORT"), d,
//				d);
//		ff.create(n);
//		ff.create(nn);
//		News n2 = ff.get("news1_news1");
//		System.out.println(n2.getInformation());
//		System.out.println(n2.getIdentifier());
//		System.out.println(n2.getPublicationDate());
//		n2.setReaders(100);
//		n2.setInformation("Updated information");
//		n2.setLastModified(new Date());
//		ff.update(n2);
//		News n3 = ff.get("news1_news1");
//		System.out.println(n3.getInformation());
//		System.out.println(n3.getIdentifier());
//		System.out.println(n3.getPublicationDate());
//		System.out.println(n3.getReaders());
//		System.out.println(n3.getLastModified());
//		System.out.println("---------------------------------");
//		System.out.println(ff.getByDomain(NewsDomain.EDUCATION.toString()));
//
//		Dispatcher<News> disp = new Dispatcher<News>();
//		Reader r1 = new Reader(disp, "r1");
//		Reader r2 = new Reader(disp, "r2");
//		Reader r3 = new Reader(disp, "r3");
//		Reader r4 = new Reader(disp, "r4");
//		NewsPersistentProducer prod = new NewsPersistentProducer(disp);
//		disp.registerListener(new Event<News>("E1"), r1);
//		disp.registerListener(new Event<News>("E1"), r2);
//		disp.registerListener(new Event<News>("E1"), r3);
//		disp.registerListener(new Event<News>("E2"), r4);
//		disp.registerListener(new Event<News>("E2"), new NewsPersistentConsumer(prod));
//		disp.registerListener(new Event<News>("E1"), new NewsPersistentConsumer(prod));
//		disp.registerListener(new Event<News>("E3"), new NewsPersistentConsumer(prod));
//		System.out.println(disp.toString());
//		disp.unregisterListener(new Event<News>("E1"), r1);
//		disp.unregisterListener(new Event<News>("E2"), new NewsPersistentConsumer(prod));
//		System.out.println("---------------------------------");
//		System.out.println(disp.toString());
		
		
		Dispatcher<News> dispatcher = new Dispatcher<News>();
		Producer prod = new Producer(dispatcher);
		Consumer pc = new Consumer(prod);
		pc.registerEvents(dispatcher);
		
		Client c1 = new Client(dispatcher, "c1");
		Client c2 = new Client(dispatcher, "c2");
		
		Editor e1 = new Editor(dispatcher, "editor1");
		News n1 = new News(1,"Title 1", "Sport", "Prima pagina a ziarului de ieri - DigiSport a anuntat ...", "N. Manciu", d , null);
		
		e1.addNews(n1);
		e1.publishNews(n1);
		System.out.println("until here");
		
		try {
	        while (true) {
	            System.out.println("1: " + new Date());
	            Thread.sleep(5 * 1000);
	            System.out.println("C1:" + c1.getNews());
	            System.out.println("C2:" +c2.getNews());
	            System.out.println("E1:" +e1.getEditorNews());
	            
	            System.out.println("2: " + new Date());
	            Thread.sleep(5 * 1000);
	            System.out.println(c1.readNews(1));
	            System.out.println(c1.readNews(2));
	            System.out.println(c2.readNews(1));
	            
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
	}

}
