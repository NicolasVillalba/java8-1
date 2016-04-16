package bq.java8.itf;

public class StaticInterface {

	public static void main(String[] args) {
		IStaticInterface.staticMethod();
		
		DefaultStaticImpl si = new DefaultStaticImpl();
		si.normalMethod();
	}
	
}

interface IStaticInterface{
	// "static default" cannot use together
	static void staticMethod(){
		System.out.println("Invoke static default method");
	}
	
	// this is wrong, static method must be implemented in interface (must have method body)
//	static void staticUnImpMethod();
	
	void normalMethod();
}

/**
 * 	static method cannot be overrided by impl
 */
class DefaultStaticImpl implements IStaticInterface{

	@Override
	public void normalMethod() {
		System.out.println("Invoke normal method");
	}
	
}