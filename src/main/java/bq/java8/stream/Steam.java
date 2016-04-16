package bq.java8.stream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class Steam {

	private List<Integer> values = new ArrayList<Integer>();
	
	@Before
	public void init(){
		System.out.println("\n-----------------------------------------");
		
		values.clear();
		
		for (int i = 0; i < 20; i++) {
			values.add(i);
		}
		
	}
	
	@Test
	public void testFilter(){
		System.out.println("before:");
		values.forEach(value -> System.out.print(value + ","));
		
		// steam: filter
		Stream<Integer> after = values.stream().filter(value -> value%5!=0);
		
		System.out.println("\nafter:");
		after.forEach(value -> System.out.print(value + ","));
	}
	
	@Test
	public void testDistinct(){
		for (int i = 0; i < 10; i++) {
			values.add(new Random().nextInt(20));
		}
		
		System.out.println("before:");
		values.forEach(value->System.out.print(value + ","));
		
		// stream: distinct
		Stream<Integer> after = values.stream().distinct();
		
		System.out.println("\nafter:");
		after.forEach(value->System.out.print(value + ","));
	}
	
	@Test
	public void testMap(){
		System.out.println("before:");
		values.forEach(value->System.out.print(value + ","));
		
		int sum = values.stream().mapToInt(value -> value%3==0?1:0).sum();
		System.out.println("\nTotal count of values(%3==0) : " + sum);
	}
	
	@Test
	public void testFlatMap(){
		List<List<Integer>> matrix = new ArrayList<>();
		matrix.add(values);
		matrix.add(values);
		
		// flatMap: list of list -> list
		List<Integer> after = matrix.stream().flatMap(dim->dim.stream()).collect(Collectors.toList());
		
		System.out.println("after flatMap:");
		after.stream().forEach(value -> System.out.print(value + ","));
	}
	
	@Test
	public void test1(){
		//
		int sum = values.stream().filter(value->value%2==0).skip(5).limit(2).mapToInt(value->value*2).sum();
		assertEquals(sum, 44);
	}
	
	@Test
	public void testReduce(){
		// 1+2+3+4+5+6+....+20
		Integer sum = values.stream().reduce((a, b) -> a+b).get();
		Integer sum2 = values.stream().mapToInt(value->value).sum();
		System.out.println("reduced sum:" + sum);
		assertEquals(sum, sum2);
		
		// 1x2x3x4x5x6x.....x20
		Integer factorial = values.stream().skip(1).reduce((a,b)->a*b).get();
		System.out.println("reduced factorial:" + factorial);
	}
	
	@Test
	public void testCount(){
		long total = values.stream().count();
		assertEquals(total, 20);
		
		long total2 = values.stream().mapToInt(value->value%3).filter(value1->(value1==2 || value1==1)).count();
		System.out.println("total number which value %3 == 1/2 :" + total2);
	}
	
}
