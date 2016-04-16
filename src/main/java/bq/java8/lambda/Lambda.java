package bq.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

public class Lambda {
	
	@Before
	public void before(){
		System.out.println();
	}

	@Test
	public void testCase1(){
		System.out.println("Test case 1:");
		
		List<Integer> values = new ArrayList<>(100);
		
		for (int i = 0; i < 100; i++) {
			int value = new Random().nextInt(100);
			values.add(value);
			System.out.print(value + ",");
		}
		
		// lambda: sort
		values.sort((a,b)->a.compareTo(b));
		
		System.out.println();
		
		// lambda: forEach
		values.forEach(a->System.out.print(a + ","));
	}
	
	@Test
	public void testCase2() throws InterruptedException{
		System.out.println("Test case 2:");
		
		ExecutorService threadpool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			// lambda: Runnable
			threadpool.execute(()->System.out.println(Thread.currentThread().getId()));
		}
		
	}
	
	@Test
	public void testCase3(){
		System.out.println("Test case 3:");
		
		List<Integer> values = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			values.add(new Random().nextInt(100));
		}
		
		ExecutorService threadpool = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			// lambda: Runnable with multiple statements and using value in main method
			threadpool.execute(()->{
							StringBuffer str = new StringBuffer("Thread-" + Thread.currentThread().getId() + " : ");
							//lambda: for each
							values.forEach(a->str.append(a).append(","));
							
							System.out.println(str.toString());
					}
			);
		}
		
	}
	
}
