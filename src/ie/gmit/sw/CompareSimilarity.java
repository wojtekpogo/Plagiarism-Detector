package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CompareSimilarity is a gateway to be able to invoke an algorithm.
 * The intention of this class is to use the Strategy Pattern in other classes
 * by passing an instance of the interface into the calculateAllDocs function.
 */
public class CompareSimilarity {
	
		// A list of doubles that represents the similarity of all objects
		private List<Double> comparisons = new ArrayList<Double>();
		// A map to retrieve a corresponding title of a document
		private Map<String, Double> map = new HashMap<>();
		
		// A composition of a similarityalgorithm
		private Similarator similarity;
		
		List<Results> results = new ArrayList<>();
		
		/**
		 * This method is used to calculate the similarity of set A against an array
		 * of size N of sets B.
		 * 
		 * @param documents
		 * 			List of documents previously uploaded         
		 * @param a
		 *            Set A uploaded document
		 * @param sa
		 *            An instance of Similarator
		 * @param title
		 * 			title of a document
		 * @return
		 * 		   returns a calculateResult method
		 */
		public double calculateAllDocs(List<Document> documents, Set<Integer> a, Similarator sa, String title) {

			similarity = sa;
			double jaccardIndex;

			// If it's the first document in the document list, then no plagiarism has been detected
			if (documents.size() == 1) {
				results.add(new Results("Plagiarism not detected",100));
			} else {
				// Loop over the size of documents -1 so it won't compare to itself
				for (int i = 0; i < documents.size() - 1; i++) {
					// Add the result of the delegated similarity function				
					jaccardIndex = sa.compareSimilarity(a, documents.get(i).getHashes());
					// Map the title of a document with its corresponding jaccardIndex
					map.put(title, jaccardIndex);
					comparisons.add(jaccardIndex);
				}
			}
			return calculateResult();
		}
	
		
		/**
		 * Method to loop over the list of doubles and calculate the average in order to 
		 * measure the similarity of all documents.
		 * 
		 * @return the average of the list in a percentage.
		 */
		public double calculateResult() {
			// Set a temp double to 0
			double sum = 0;
			// Foreach to loop over each double and add it to the sum.
			for (double i : comparisons) {
				sum += i;
			}
			// Displays similarity percentage to the console
			System.out.println("Similarity % = " + (sum / comparisons.size()) * 100);
			System.out.println(map);
			System.out.println(map.keySet());
			// Return the average (sum/size) and multiplied by 100 (100/1) to represent a percentage
			return (sum / comparisons.size()) * 100;
		}
		
		/**
		 * Function to get a map and retrieve a corresponding title of a document
		 * and its jaccardIndex
		 * @return 
		 */
		public Map<String, Double> getMap() {
			return map;
			
		}
}
