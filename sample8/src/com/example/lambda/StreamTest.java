package com.example.lambda;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
public static void main(String[] args) {
	
    List<Integer> numbers = new ArrayList<Integer>();
    for(int i = 0 ; i < 10; i++) {
    	numbers.add(i);
    }
//  Integer       result  = 0;
//  for (Integer n : numbers) {
//    result += n;
//  }
	Integer result = numbers.stream().reduce(0, (a, b) -> a + b);
	System.out.println("result: "+ result);
}
}
