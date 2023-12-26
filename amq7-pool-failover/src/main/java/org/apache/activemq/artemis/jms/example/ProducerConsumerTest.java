package org.apache.activemq.artemis.jms.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

public class ProducerConsumerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProducerConsumerTest.class);
    public static void main(String[] args) throws JMSException, InterruptedException {

        // 创建并启动发送消息的线程
        Thread senderThread = new Thread(new MessageSender());
        senderThread.start();

        // 创建并启动接收消息的线程
        Thread receiverThread = new Thread(new MessageReceiver());
        receiverThread.start();

        Thread customerreceiverThread = new Thread(new CustomerMessageReceiver());
        //看一下为什么总是在delivering_count状态?
        customerreceiverThread.start();


//          String destination = "testqueue"; // this is the queue name
//        Producer producer = new Producer();
//        String messagetxt = "helloWorld";
//        for (int i = 0; i < 1000; i++) {
//            producer.sendMessageToAMQ(messagetxt,destination,null,0);
//            logger.info("sent to destination message: " + i );
//            Thread.currentThread().sleep(1000);
//        }

        // below are the consumer side code
//        Consumer consumer = new Consumer();
//        for (int i = 0; i < 1000; i++) {
//            consumer.polling(destination,null,0);
//            logger.info("trying to get message: " + i );
//        }
    }
}

class MessageSender implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
    @Override
    public void run() {
        String destination = "testqueue"; // this is the queue name
        Producer producer = new Producer();
        String messagetxt = "helloWorld";
        for (int i = 0; i < 1000; i++) {
            try {
                producer.sendMessageToAMQ(messagetxt,destination,null,0);
                logger.info("sent to destination message: " + i );
                Thread.currentThread().sleep(1000);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


class MessageReceiver implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @Override
    public void run() {
        String destination = "testqueue"; // this is the queue name
        Consumer consumer = new Consumer();
        for (int i = 0; i < 1000; i++) {
            consumer.polling(destination, null, 0);
            logger.info("trying to get message: " + i);
        }
    }
}
    class CustomerMessageReceiver implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(org.apache.activemq.artemis.jms.example.MessageReceiver.class);
        @Override
        public void run() {
            String destination = "testqueue"; // this is the queue name
            CustomerConsumer  consumer = new CustomerConsumer();
            for (int i = 0; i < 1000; i++) {
                consumer.polling(destination,null,0);
                logger.info("trying to get message: " + i );
            }
        }
}
