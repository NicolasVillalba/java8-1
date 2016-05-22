package bq.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * One argument Supplier/Consumer/Function
 * 
 * @author qibo
 *
 */
public class FunctionInterfaceTest {

	@Test
	public void testConsumeSupplier(){
		// style 1: by class
		System.out.println("/n ---- testConsumeSupplier by class-------");
		BSupplier supplier = new BSupplier();
		List<Integer> values = supplier.get();
		values.forEach(new BConsumer().andThen((r) -> System.out.println("Consume Done!")));

		// style 2: by method
		System.out.println("/n ---- testConsumeSupplier by method-------");
		Supplier<List<Integer>> supplier2 = FunctionInterfaceTest::supplier;
		values = supplier2.get();
		values.forEach(FunctionInterfaceTest::consumer);
	}
	
	@Test
	public void testFunction(){
		System.out.println("/n/n ---- testFunction-------");
		
		List<String> strValues = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			strValues.add(String.valueOf(i));
		}
		
		BFunction function = new BFunction();
		
		List<Integer> intValues = new ArrayList<>();
		for (int i = 0; i < strValues.size(); i++) {
			// function with "andThen"
			Integer intValue = function.andThen((r) -> {System.out.println("Done!");return r;}).apply(strValues.get(i));
			intValues.add(intValue);
		}
		
	}
	
	public static List<Integer> supplier(){
		List<Integer> values = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			values.add(i);
		}
		
		return values;
	}
	
	public static void consumer(int value){
		System.out.println("I consume " + value);
	}
	
	
}

class BFunction implements Function<String, Integer>{

	@Override
	public Integer apply(String t) {
		return Integer.valueOf(t);
	}
	
}

class BConsumer implements Consumer<Integer>{

	@Override
	public void accept(Integer value) {
		System.out.println("I consume " + value);
	}
	
}

class BSupplier implements Supplier<List<Integer>>{

	@Override
	public List<Integer> get() {
		List<Integer> values = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			values.add(i);
		}
		
		return values;
	}
	
}
