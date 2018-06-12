package redhat.amq.fabric.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

public class SimpleConsumerWith2Threads {
	
	public static class MyRunnable implements Runnable {
		private Session session;
		private TextMessage message;

		private boolean flag = true;
		
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		public MyRunnable(Session session,Message message)
		{
			this.session = session;
			this.message = (TextMessage) message;
		}
		@Override
		public void run() {
    		try{
    			long oldTime = System.currentTimeMillis();
    			while(flag)
    			{   
    				
    				Thread.sleep(100);  		 
    				long currentTime = System.currentTimeMillis();
    				if((currentTime-oldTime)>20000) {
//    					session.recover();
//    					message.acknowledge();
    					
    					System.out.println("exec error do not insert data into db... "+ this.message.getText());
    					break;
    				}
    				
    		 	}
    			long currentTime = System.currentTimeMillis();
    			if((currentTime-oldTime)<=4000) {
    				message.acknowledge();
    			}
    		} catch (JMSException e) {
    			e.printStackTrace();
	         }catch(InterruptedException e){
	        	 e.printStackTrace();
	    	 }
     }
}

    private static final Boolean NON_TRANSACTED = false;

    public static void main(String args[]) {    	
    	//failover:(tcp://primary:61616,tcp://secondary:61616)?randomize=false
		try {
			 Context context = new InitialContext();
			 ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616)?jms.redeliveryPolicy.maximumRedeliveries=-1&timeout=5000&maxReconnectAttempts=-1");		     
			 ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
//			 RedeliveryPolicy policy = new RedeliveryPolicy();
//			 policy.setMaximumRedeliveries(4);
//			 //Get the policy map
//			 RedeliveryPolicyMap map = connection.getRedeliveryPolicyMap();
//			 map.put(new ActiveMQQueue("fabric.simple"), policy);
			 
			 connection.start();		     
			 final Session session = connection.createSession(NON_TRANSACTED, ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
			 Queue queque = session.createQueue("fabric.simple"); 		     
			 MessageConsumer consumer = session.createConsumer(queque);  
			 /*************************************************************************
			  * 队列中消息顺序为   message5>message4>message3>message2>message1>RD
			  * 模拟RD还未消费完毕时 message1 重复redelivery 阻塞队列
			  * RD消息启用线程消费，线程执行30秒才完成，主线程1秒后完成
			  *************************************************************************/
			
			 consumer.setMessageListener(new MessageListener() {
				 private int count = 0;
				 public void onMessage(Message message) {
					 try {
						 if (message instanceof TextMessage) {
							 TextMessage textMessage = (TextMessage) message;
							 String text = textMessage.getText();
							 System.out.println("Consuming message: " + text);
							 if(text.contains("RD")){
								 System.out.println("SimpleConsumer3 RD Message:"+text);
								 //开启线程处理 RD消息 ，线程需要30秒 执行完毕
								 MyRunnable runnable = new SimpleConsumerWith2Threads.MyRunnable(session,message);
								 System.out.println("start handle message>>>>>>>>>>>>>>>>>"+text);
								 new Thread(runnable).start();
								 //竹线程执行1秒结束
								 Thread.sleep(1000);
								 
							 } else{
								 System.out.println("SimpleConsumer3 number message:"+text);
//								 
								 if("message1".equalsIgnoreCase(text)) {
									 message.acknowledge();
									 
									
								 }else {
									 message.acknowledge();
								 }
								 
								 ++count;
								 System.out.println("after SimpleConsumer3 number message:"+text);		                           
							 }
						 }else{
							 System.out.println("Consuming Weird Message: " + message);
						 }
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
				 }
			 });	        
		} catch (Exception e) {		
			System.out.println("====================================================");
			e.printStackTrace();
		}
    }
}