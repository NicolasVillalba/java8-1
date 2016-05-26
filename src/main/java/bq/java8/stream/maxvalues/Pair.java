package bq.java8.stream.maxvalues;

import java.util.Objects;

public class Pair<A,B> {
	private A a;
	private B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	public void setA(A a){
		this.a = a;
	}
	
	public A getA() {
		return a;
	}
	
	public B getB() {
		return b;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Pair)) {
			return false;
		}
		Pair<?,?> that = (Pair<?,?>)o;
		return Objects.equals(this.a, that.a) &&
			   Objects.equals(this.b, that.b);
	}
		

}

