package trywithresource;

public class Resource2 
{ 
	public static void main(String s[]) 
	{ 
		Demo2 d = new Demo2(); Demo3 d1 = new Demo3();
		try 
		{ 
			int x = 10/0; 
			d.show(); 
			d1.show1(); 
		} 
		catch(ArithmeticException e) 
		{ 
			System.out.println(e); 
		}finally {
			d.close();
			d1.close();
		}
		
	} 
} 

//custom resource 1 
class Demo2 implements AutoCloseable 
{ 
	void show() 
	{ 
		System.out.println("inside show"); 
	} 
	public void close() 
	{ 
		System.out.println("close from demo"); 
	} 
} 

//custom resource 2 
class Demo3 implements AutoCloseable 
{ 
	void show1() 
	{ 
		System.out.println("inside show1"); 
	} 
	public void close() 
	{ 
		System.out.println("close from demo1"); 
	} 
} 
