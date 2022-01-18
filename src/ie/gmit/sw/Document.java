package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;


/**
 * Document Object - An abstract template of how each text document looks like.
 * The goal is to provide a general Object-Oriented Programming by using templates to represent objects
 */

public class Document {
	
		// Title of the document
		private String title;
		// Contents of the document post hashing
		private Set<Integer> hashes = new TreeSet<Integer>();

		/**
		 * Default Constructor
		 * 
		 */
		public Document() {

		}

		/**
		 * Default Constructor generated using Fields
		 * 
		 * @param title
		 *            sets the String
		 * @param hashes
		 *            sets the TreeSet
		 */
		public Document(String title, Set<Integer> hashes) {
			super();
			this.title = title;
			this.hashes = hashes;
		}

		// Getters and Setters for stronger encapsulation and to be able to use
		// private variables
		// publicly.
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Set<Integer> getHashes() {
			return hashes;
		}

		public void setHashes(Set<Integer> hashes) {
			this.hashes = hashes;
		}

}
