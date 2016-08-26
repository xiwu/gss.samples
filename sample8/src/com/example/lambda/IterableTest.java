package com.example.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IterableTest {

	public static void main(String[] args) {
		List<String> persons = new ArrayList<String>();
		persons.add("wuxiaohui");
		persons.add("suyunpeng");
		persons.add("suwuyang");
		persons.forEach((e) -> System.out.println("hello," +e));
		MyConsumer myconsumer =  new MyConsumer();
		persons.forEach(myconsumer);
		
	}
}	
	class MyConsumer implements Consumer {

		@Override
		public void accept(Object t) {
			// TODO Auto-generated method stub
			System.out.println("hello again, " + t);
		}
	}


