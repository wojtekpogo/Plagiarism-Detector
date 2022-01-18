package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;


/**
 * Shingles is an implementation of Shingleable. It performs a simple shingle function which
 * creates shingles of size N.
 * The goal was to decouple classes and maintain the SRP
 * Shingles IS-A Shingleable (Inheritance).
 */

public class Shingles implements Shingleable {
	
		// Shingled list of words
		private List<String> shingles = new ArrayList<String>();
		
		// Shingle size
		private final int SHINGLE_SIZE = 3;

		/**
		 * Default constructor
		 */
		public Shingles() {

		}
		
		// Almost like Schindler's List
		public List<String> shingler(List<String> words) {
			// Set a temp string to null
			String shingle = "";
			// Set the control to 0
			int element = 0;
			// Loop over the array of words fed as a parameter
			for (int i = 0; i < words.size(); i++) {
				// Add each word to the shingle using += operator
				shingle += words.get(i);
				// Increment control
				element++;
				// If the control is the same size as the shingle size
				if (element == SHINGLE_SIZE) {
					// Add the temp string to shingles list
					shingles.add(shingle);
					// Reset temp string
					shingle = "";
					// Reset control
					element = 0;
				}
				// If the control is less than the shingle size and we're at the end of the loop
				// We add a shingle, the purpose is to catch out any remainders of i % shingle_size
				if (element < SHINGLE_SIZE && i == words.size() - 1) {
					shingles.add(shingle);
				}
			}
			
			// Return the k-shingles list
			return shingles;
		}

}
