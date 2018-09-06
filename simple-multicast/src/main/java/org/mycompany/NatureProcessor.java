package org.mycompany;

import java.util.concurrent.ExecutorService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class NatureProcessor implements Processor {
// 
    @Autowired
    private ApplicationContext context;
//
//    @Autowired
   private ExecutorService executor;
//    @Autowired
   private ERPTask task;

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
//		executor.submit(ERPTask);
//		executor = exchange.getContext().getExecutorServiceManager().getThreadPoolProfile("myPool");
//				.newFixedThreadPool(this, "ERP", 25);
		if(executor == null ) {
			System.out.println("null executor");
		executor = exchange.getContext().getExecutorServiceManager().newFixedThreadPool(this, "ERP", 25);
		}
		System.out.println("XXX executor: " + executor);
		System.out.println("XXX this: " + this);
/*		if(task == null) {
			task = new ERPTask(exchange, "123");
		} if (task != null) {
			task.setExchange(exchange);
			task.SetMsgid("456");
		}*/
		ERPTask r1 = (ERPTask) context.getBean("myTask");
		r1.setExchange(exchange);
		r1.SetMsgid("123");
		System.out.println("XXXXXX myTask: " + r1);
		executor.submit(r1);
		
		ERPTask r2 = (ERPTask) context.getBean("myTask");
        r2.setExchange(exchange); 
        r2.SetMsgid("456");
		executor.submit(r2);
//		executor.submit(new ERPTask(exchange));
//		executor.submit(new ERPTask(exchange));
    }
	}



