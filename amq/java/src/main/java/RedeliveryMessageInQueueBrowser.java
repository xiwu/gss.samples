 /*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

/**
 * An example showing how messages are moved to dead letter destination when they are unsuccessfully delivered multiple times
 */
public class RedeliveryMessageInQueueBrowser {

   private static final Boolean TRANSACTED = true;
   public static void main(final String[] args) throws Exception {
      Connection connection = null;

      try {
         // Step 1. Create an initial context to perform the JNDI lookup.
//         initialContext = new InitialContext();

         // Step 2. Perfom a lookup on the queue
//         Queue queue = (Queue) initialContext.lookup("queue/exampleQueue");

         // Step 3. Perform a lookup on the Connection Factory
//         ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

     	ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616,tcp://localhost:61618)");

         
         // Step 4.Create a JMS Connection
         connection = cf.createConnection();
         connection.start();
         // Step 5. Create a * transacted* JMS Session
         Session session = connection.createSession(TRANSACTED, Session.SESSION_TRANSACTED);

        
     	Queue queue = session.createQueue("exampleQueue"); 
     	
        QueueBrowser browser = session.createBrowser(queue);
         Enumeration<?> enumeration = browser.getEnumeration();
         int received = 0;
         while (enumeration.hasMoreElements()) {
             Message m = (Message) enumeration.nextElement();
             
             received++;
             System.out.println("Browsed message " + received + ": " + m.getJMSMessageID() + ": " + m.getJMSRedelivered());
         }

         browser.close();
      } finally {
         // Step 24. Be sure to close our JMS resources!
         if (connection != null) {
            connection.close();
         }
      }
   }
}


