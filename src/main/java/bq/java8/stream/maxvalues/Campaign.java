package bq.java8.stream.maxvalues;

import java.util.Objects;

/**
 * A Campaign represents an ad that we would like to place for our marketer,
 * and the price they would like to pay to show it.
 */
public class Campaign {

	private final int id;
	private final String description;
	private final int bidCents;

	public Campaign(int id,
			        String description, 
			        int bidCents) { 
		this.id = id;
		this.description = Objects.requireNonNull(description);
		this.bidCents = bidCents; 
	}
	
	public int getId() {
		return id;
	}
		
	public String getDescription() {
		return description;
	}

	public int getBidCents() {
		return bidCents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, bidCents);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Campaign)) {
			return false;
		}
		Campaign that = (Campaign)o;
		return Objects.equals(this.id, that.id) &&
			   Objects.equals(this.description, that.description) &&
			   Objects.equals(this.bidCents, that.bidCents);
	}

}
