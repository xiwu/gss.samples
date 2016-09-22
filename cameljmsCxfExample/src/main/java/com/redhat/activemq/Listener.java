package com.redhat.activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {

	public void onMessage(Message message) {
		int i = 0 ;
		TextMessage txt = (TextMessage)message;
		try {
				System.out.println(txt.getJMSDestination() + "got message " + i + txt.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
