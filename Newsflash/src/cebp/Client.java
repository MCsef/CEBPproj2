package cebp;

import java.awt.event.TextListener;

import javax.annotation.Resource;
import javax.jms.*;

public class Client {
	@Resource(mappedName="jms/ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	@Resource(mappedName="jms/Topic")private static Topic topic;

	public static void main(String args) throws JMSException {
		TextListener listener;
		MessageConsumer consumer;
		Destination dest = null;
		Connection connection = null;
		try {
			dest = (Destination) topic;

		connection = connectionFactory.createConnection(); 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		MessageProducer producer = session.createProducer(dest);
		TextMessage message = session.createTextMessage();
		
		//listener = new TextListener();
		//consumer.setMessageListener((MessageListener) listener);
		
	} finally { 
	    if (connection != null) { 
	        try { connection.close(); } 
	        catch (JMSException e) { } 
	    }
	}
	}
}
