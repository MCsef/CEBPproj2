package cebp;

import javax.annotation.Resource;
import javax.jms.*;

public class Editor{
	@Resource(mappedName="jms/ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	@Resource(mappedName="jms/Topic")private static Topic topic;

	public static void main(String args) throws JMSException {
		
		Destination dest = null;
		Connection connection = null;
		try {
			dest = (Destination) topic;

		connection = connectionFactory.createConnection(); 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
		MessageProducer producer = session.createProducer(dest);
		TextMessage message = session.createTextMessage();
		
		for (int i = 0; i < 10; i++) { 
		    message.setText("This is message " + (i + 1)); 
		    System.out.println("Sending message: " + message.getText()); 
		    producer.send(message);
		}

		producer.send(session.createMessage());
		
	} finally { 
	    if (connection != null) { 
	        try { connection.close(); } 
	        catch (JMSException e) { } 
	    }
	}
	}
}
