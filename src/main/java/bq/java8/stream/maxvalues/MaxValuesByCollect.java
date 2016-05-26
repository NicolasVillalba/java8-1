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
public class MaxValuesByCollect {

	public Optional<Campaign> calcMaxValuesWithEqualDistribution(List<Campaign> campaigns){
		
		List<Campaign> maxCampaigns = campaigns.stream()
				.collect(() -> new Pair<Integer, List<Campaign>>(Integer.MIN_VALUE, new ArrayList<Campaign>()),
						this::accumulator,
						this::combiner
						)
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
	private void accumulator(Pair<Integer, List<Campaign>> pair, Campaign cam){
		if(pair.getA() < cam.getBidCents()){
			pair.getB().clear();
			pair.getB().add(cam);
		}
		
		if(pair.getA() == cam.getBidCents())
			pair.getB().add(cam);
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
	private void combiner(Pair<Integer, List<Campaign>> pair1, Pair<Integer, List<Campaign>> pair2){
		if(pair1.getA() == pair2.getA()){
			pair1.getB().addAll(pair2.getB());
			return;
		}
		
		if(pair1.getA() < pair2.getA()) {
			pair1.getB().clear();
			pair1.getB().addAll(pair2.getB());
			pair1.setA(pair2.getA());
		}
	}
	
}
