package sample8;

import java.util.function.Consumer;

public class SpecialCalculator {

	interface IntegerMath {
		int operation(int a, int b);
	}
	public int operateBinary(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}
	
    public static < Y> void precessElements(
    		Consumer<Y> block) {
    	block.accept(null);
    }
    
	public static void main(String ...strings ) {
		SpecialCalculator cal = new SpecialCalculator();
		IntegerMath addition = (a, b) -> a + b;
		IntegerMath subtraction = (a, b) -> a - b;
		System.out.println("40 + 2 = " + cal.operateBinary(40, 2, addition));
		System.out.println("20 - 8 = " + cal.operateBinary(20, 8, subtraction));
		cal.precessElements(X -> System.out.println(42));
		
	}
}
