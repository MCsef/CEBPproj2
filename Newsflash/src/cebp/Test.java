package cebp;

public class Test {
	public static void main(String[] args) {
		System.out.println("Hello world");	
		
		Dispatcher<News> dispatcher = new Dispatcher<News>();
		Producer prod = new Producer(dispatcher);
		Consumer pc = new Consumer(prod);
		pc.registerEvents(dispatcher);
		
		Client c1 = new Client(dispatcher, "c1");
		Client c2 = new Client(dispatcher, "c2");
		
		Editor e1 = new Editor(dispatcher, "editor1");
		News n1 = new News(440,"Title #3", "Sport", "Maratonul incepe maine dimineata la ora 9", "Maria Octogon", "2020-08-09", "");
		//News n2 = new News(101,"Title #4", "Music", "New person in town", "El Serenda", "1990-07-09", "");
		News n3 = new News(441,"Title #3", "Sport", "Maratonul incepe maine dimineata la ora 9", "Maria Octogon", "2020-08-09", "");
		
		e1.addNews(n1);
		e1.publishNews(n1);
		e1.addNews(n3);
		e1.publishNews(n3);
		
		c1.addNews(n1);
		c1.addNews(n3);
		c2.addNews(n1);
		
	    System.out.println("C1:" + c1.getNews());
		System.out.println("C2:" +c2.getNews());
	    System.out.println("E1:" +e1.getEditorNews());
	    
	    System.out.println(n1.getID());
	    System.out.println(n3.getID());
	    System.out.println(c1.readNews(440));
	    System.out.println(c1.readNews(441));
	    System.out.println(c2.readNews(440));
	    
	    System.out.println(n1.getNumberOfClients());
	    System.out.println(n3.getNumberOfClients());
	    //System.out.println(c1.readNews(10));
	    //System.out.println(c2.readNews(10));
	}
}
