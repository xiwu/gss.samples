package crash;

public class CrashSample {
    public static void main(String[] argv){
try {
	System.out.println("begin!");
    	java.lang.reflect.Field field = 
sun.misc.Unsafe.class.getDeclaredField("theUnsafe"); 
        field.setAccessible(true); 
        sun.misc.Unsafe a = (sun.misc.Unsafe) field.get(null); 
        a.putAddress(0, 0); 
}catch (Exception ex) {
	ex.printStackTrace();
}

   System.out.println("I didn't crash!");
    }
}
