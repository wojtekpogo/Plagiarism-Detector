package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DocumentParser Class parses the document and adds words to newly created list.
 * The goal was to decouple classes and maintain a SRP. It implements Parserable interface (IS-A)
 * 
 */

public class DocumentParser implements Parserable {
	
	// ArrayList of strings that make up the words of the document
	private List<String> words = new ArrayList<String>();
	
	
	/**
	 * {@inheritDoc}
	 *
	 * This is an implementation of readDocument method from Parserable interface
	 * 
	 */
	
	public List<String> readDocument(BufferedReader br) throws IOException {
		// Set a temporary String to blank
		String line = "";
		try {
			// Reads a full document
			while ((line = br.readLine()) != null) {
				// Create an array of split words
				String[] split = line.split(" ");
				// Add each split word to the ArrayList of words
				for (int i = 0; i < split.length; i++) {
					words.add(split[i]);
				}
			}
		} finally {
			// Close the Buffered Reader
			br.close();
		}

		// return the ArrayList of words
		return words;
	}
}
