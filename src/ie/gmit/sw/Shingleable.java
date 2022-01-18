package ie.gmit.sw;

import java.util.List;


/**
 * Shingleable Interface describes a contract for all shingling operations.
 * Shingleable objects are those used to work with words ArrayLists to create
 * shingles. This enhances abstraction and Single Responsibility Rule.
 */
public interface Shingleable {
	
	
	/**
	 * All Shinbleable  have a shingler method that takes a list of words and
	 * creates shingles.
	 * 
	 * @param words the list of words to be shingled
	 * @return return the list of words post shingling
	 */
	
	
	public List<String> shingler(List<String> words);

}
