package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Parserable Interface describes a contract for all Parserable objects.
 * Parserable objects deals with Files which enhances
 * abstraction and SRP.
 */

public interface Parserable {
	
	 /**
	 * This method parses the document and adds each word to the list.
	 * @param br
	 *            The BufferedReader containing the document contents
	 * @return A List of words
	 * @throws IOException
	 *             for File Handling
	 */
	
	public List<String> readDocument(BufferedReader br) throws IOException;
}
