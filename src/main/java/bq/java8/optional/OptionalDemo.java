package bq.java8.optional;

import java.util.Optional;

import org.junit.Test;

public class OptionalDemo {
	
	@Test
	public void test(){
		String value = null;
		
		// if null
		if(Optional.ofNullable(value).isPresent())
			System.out.println("value is null");
		
		// if null return xx, otherwise return original value
		String after = Optional.ofNullable(value).orElse("NULL");
		System.out.println("value = " + after);
		
		value = "value";
		
		after = Optional.ofNullable(value).orElse("NULL");
		System.out.println("value = " + after);
		
		// if null return function(xx), otherwise return function(xx)
		after = Optional.ofNullable(value).map(value1->"NOT null!! it's " + value1).orElseGet(()->"It's null");
		System.out.println("value = " + after);
		
		try{
			value = null;
			
			// if null, throw exception
			after = Optional.ofNullable(value).map(value1->"NOT null!! it's " + value1).orElseThrow(NullPointerException::new);
		} catch(NullPointerException e){
			System.out.println("Invalid value, cannot be null");
		}
	}
	
	
}
