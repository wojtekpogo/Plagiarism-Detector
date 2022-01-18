package ie.gmit.sw;

import java.util.Set;

/**
 * Used for Jaccard similarity algorithm where we compare sets
 * 
 * @param a Set A which will be compared to b (Set B)
	 * @param b Set B which will be compared to a (Set A)
 * @return a double value of the comparison
 */

public interface Similarator {

	public double compareSimilarity(Set<Integer> a, Set<Integer> b);

}
