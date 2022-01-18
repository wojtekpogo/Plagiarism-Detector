package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

/**
 * Jaccard is an implementation of Similarator.
 * The goal is to decouple classes and maintain SRP.
 * Jaccard IS-A Similarator (Inheritance).
 */
public class Jaccard implements Similarator{
	
	// Temporary set N
	private Set<Integer> n;
	// Intersection Cardinality| A n B |
	private int intersectionCardinality;
	// Union Cardinality | A u B |
	private int unionCardinatlity;
	
	
		
	/**
	* {@inheritDoc}
	*
	* This implementation of compareSimilarity method from Similarator interface.
	*/
	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		//Union cardinality | A u B | = |A| + |B|
		unionCardinatlity = a.size() + b.size();
		// Create a new Treeet of integers which consist of Set A
		n = new TreeSet<Integer>(a);
		// Delete all except common integers of N and a Set B
		n.retainAll(b);
		// The cardinality of intersection is the size of N
		intersectionCardinality = n.size();
		// Return the calculation (| A n B | / | A | + | B | - | A n B |)
		return Double.valueOf(intersectionCardinality)
						/ (Double.valueOf(unionCardinatlity) - Double.valueOf((intersectionCardinality)));
	}

}
