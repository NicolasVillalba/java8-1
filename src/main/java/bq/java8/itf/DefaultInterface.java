package bq.java8.itf;

/**
 * Interface can have a default implemented method since java 8
 * 
 * @author qibo
 *
 */
public class DefaultInterface {

	public static void main(String[] args) {
		DefaultImpl di = new DefaultImpl();
		di.defaultMethod();
		di.normalMethod();
		
		OverrideImpl oi = new OverrideImpl();
		oi.defaultMethod();
		oi.normalMethod();
	}
	
}

interface IDefaultInterface{
	void normalMethod();
	
	default void defaultMethod(){
		System.out.println("Invoke default method!");
	}
}

class DefaultImpl implements IDefaultInterface{

	@Override
	public void normalMethod() {
		System.out.println("Invoke normal method!");
	}
	
}

class OverrideImpl extends DefaultImpl{

	@Override
	public void defaultMethod() {
		System.out.println("Invoke overrided default method");
	}
	
}
