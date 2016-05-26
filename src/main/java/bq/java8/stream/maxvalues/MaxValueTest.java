package bq.java8.stream.maxvalues;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MaxValueTest {
	
	private List<Campaign> campaigns = new ArrayList<>();
	
	@Before
	public void setup(){
		campaigns.clear();
		
		for(int i = 0; i < 1000; i++) {
			int id = new Random().nextInt(10000);
			campaigns.add(new Campaign(id,  "description" + id,  id));
		}
		
		for(int i = 0; i < 10; i++){
			int id = new Random().nextInt(100) + 10000;
			campaigns.add(new Campaign(id,  "max value " + id,  10008));
		}
	}
	
	@Test
	public void testReduce(){
		MaxValuesByReduce reducer = new MaxValuesByReduce();
		Optional<Campaign> max = reducer.calcMaxValuesWithEqualDistribution(campaigns);
		assertTrue(max.get().getId() > 10000);
	}
	
	@Test
	public void testCollect(){
		MaxValuesByCollect collect = new MaxValuesByCollect();
		Optional<Campaign> max = collect.calcMaxValuesWithEqualDistribution(campaigns);
		assertTrue(max.get().getId() > 10000);
	}
	
}
