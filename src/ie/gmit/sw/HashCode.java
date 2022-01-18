package ie.gmit.sw;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * HashCode performs a hashing function. It's achieved using .hashCode()
 */
public class HashCode {
	
		// Integer TreeSets that hold the hashed value of the shingles
		private Set<Integer> hashedShingles = new TreeSet<Integer>();

		
		
		 /**
		 * Method to take a list of words and apply a hash function.
		 * 
		 * @param list of shingled words which will be hashed.
		 * @return The set of hashes.
		 */
		public Set<Integer> hashShingle(List<String> shingles) {
			// For each shingle in shingles list
			for (String shingle : shingles) {
				// Getting the hashCode of the shingle and add it to the hashedShingles list.
				hashedShingles.add(shingle.hashCode());
			}
			return hashedShingles;
		}
}
