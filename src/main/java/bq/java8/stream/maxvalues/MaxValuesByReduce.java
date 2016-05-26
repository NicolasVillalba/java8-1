package bq.java8.stream.maxvalues;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Get all the max values from a list.
 * 
 * Pick out one in equal possibility
 * 
 * @author qibo
 *
 */
public class MaxValuesByReduce {

	public Optional<Campaign> calcMaxValuesWithEqualDistribution(List<Campaign> campaigns){
		
		List<Campaign> maxCampaigns = campaigns.stream()
				.reduce(new Pair<Integer, List<Campaign>>(Integer.MIN_VALUE, new ArrayList<Campaign>()), 
						this::accumulator,
						this::combiner)
				.getB();
		
		Campaign maxCampaign = null;
		if(!maxCampaigns.isEmpty())
			maxCampaign = maxCampaigns.get(new Random().nextInt(maxCampaigns.size()));
		
		return Optional.ofNullable(maxCampaign);
	}

	/**
	 * Customized accumulator. 
	 * Use Pair with List to keep all the campaigns with same value
	 * 
	 * For each campaign
	 * 1) drop if less then current max value
	 * 2) add to list if equals to current max value
	 * 3) create new list if bigger than current max value
	 * 
	 * @param pair
	 * @param cam
	 * @return
	 */
	private Pair<Integer, List<Campaign>> accumulator(Pair<Integer, List<Campaign>> pair, Campaign cam){
		if(pair.getA() < cam.getBidCents()){
			List<Campaign> newList = new ArrayList<>();
			newList.add(cam);
			Pair<Integer, List<Campaign>> newPair = new Pair<>(cam.getBidCents(), newList);
			return newPair;
		}
		
		if(pair.getA() == cam.getBidCents())
			pair.getB().add(cam);
		
		return pair;
	}
	
	/**
	 * Customized combiner.
	 * 
	 * Merge two Lists
	 * 
	 * if two lists have same value
	 * 		merge
	 * else
	 * 		return the list with bigger value
	 * 
	 * 
	 * @param pair1
	 * @param pair2
	 * @return
	 */
	private Pair<Integer, List<Campaign>> combiner(Pair<Integer, List<Campaign>> pair1, Pair<Integer, List<Campaign>> pair2){
		if(pair1.getA() == pair2.getA()){
			pair1.getB().addAll(pair2.getB());
			return pair1;
		}
			
		return pair1.getA() > pair2.getA() ? pair1 : pair2;
	}
	
}
