package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The purpose of a Handler class is to connect all the other classes together.
 * This allows to use just this class to perform all the similarity comparison actions
 * such as parsing a document, shingling, hashing, saving to the database.
 */

public class Handler {
	
	
	List<String> words = new ArrayList<String>();
	
	private Parserable parserable;
	private Database db;
	private Shingles shingles;
	private CompareSimilarity cs;
	private Document doc;
	
	/**
	 * Default Constructor creates instances of the member classes. The classes
	 * will be deleted if the Handler is deleted.
	 */
	public Handler() {
		
		
		parserable = new DocumentParser();
		db= Database.getInstance();
		shingles = new Shingles();
		cs = new CompareSimilarity();
		
	}
	
	
	/**
	 * This method is responsible for delegating work to the Parserable to process
	 * the document.
	 * 
	 * @param title
	 *            Used to add a title to document to be processed and stored
	 * @param br
	 *            Used to add content to the document to be processed and stored
	 * @throws IOException
	 *             File operations
	 */
	
	
	public void readDocument(String title, BufferedReader br) throws IOException {

		try {
			// Set the words to the return of readDocumentor
			words = parserable.readDocument(br);
			// Set the title of the document
			doc.setTitle(title);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method is responsible for delegating the work to the database to add a
	 * document.
	 * 
	 * @return True if document was added successfully and false if
	 *         unsuccessfully.
	 */
	
	public boolean addDocument() {
		// Adds the document to the database
		return db.addDocument(doc);
	}
	
	/**
	 * This method is responsible for calling all the other methods in order,
	 * This allows to simply call a process method instead of all of the
	 * methods above. Just to keep things simple
	 * plus it makes this class look more like a Facade.
	 * 
	 * @param title
	 *            Title of the document
	 * @param br
	 *            BufferedReader containing the contents of the document
	 * 
	 * @return A map to allocate corresponding document title with its similarity value
	 * @throws IOException
	 *             For File operations
	 */
	
	
	public Map<String, Double> process(String title, BufferedReader br) throws IOException{
		
		doc = new Document();
		
		//doc.setTitle(title);
		//doc.setHashes(hashShingles);
		
		readDocument(title, br);
		
		//shingles.shingler(words);
		
		Shingles s = new Shingles();
		
		List<String> shingles = s.shingler(words);
		
		HashCode hc = new HashCode();		
		Set<Integer> hashShingles = new TreeSet<Integer>();
		hashShingles = hc.hashShingle(shingles);
		
		
		doc.setHashes(hashShingles);
		
		System.out.println("Total words: "+words.size());	
		System.out.println("Words after shingle: "+shingles.size());
		db.addDocument(doc);
		
		//List<Results> results = new ArrayList<>();
		cs.calculateAllDocs(db.getAll(), doc.getHashes(), new Jaccard(),title);
		//System.out.println(results);
		
		return cs.getMap();
			
	}
	
}
