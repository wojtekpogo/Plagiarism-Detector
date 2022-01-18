package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

/**
 * Database class - The goal of this class was to provide a way of accessing the
 * database for all the other classes. I've used a Singleton Pattern here so the database is instantiated once
 * and has a global access. We can also notice a use of Single Responsibility Principle - this class only deals with
 * database related operations.
 */

public class Database {
	// ArrayList of Document Objects
	private List<Document> root = new ArrayList<>();
	private EmbeddedStorageManager db = null;
	// The instance of the database
	private static Database instance = null;
	
	/**
	 * Private constructor to ensure it cannot be instantiated publicly.
	 */
	private Database() {

	}
	
	/**
	 * Creates an instance of database if one does not already exist.
	 * 
	 * @return the Instance of Database to be used by other classes.
	 */
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			System.out.println("An instance of a database has been created.");
		}
		// return the single instance of a database
		return instance;
	}
	

	/**
	 * Adds a Document object to the Microstream object persistence engine.
	 * 
	 * @param d
	 *            the Document object to be added to the database
	 * @return boolean value where true  if successful and false if
	 *         unsuccessful
	 */
	public boolean addDocument(Document d) {
		
		root.add(d);
		if(db == null) {
			try {
				db = EmbeddedStorage.start(root, Paths.get("./data"));
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		System.out.println("Document has been added to database.");
		db.storeRoot();
		query();
		return true;
		
	}
	
	/**
	 * Function to perform queries on the documents in the database. It displays all documents
	 * and also displays its corresponding title
	 *  
	 */
	public void query() {
		System.out.println("\n[Query] Show all documents");
		root.stream()
				.forEach(System.out::println);
		
		
		// display all the titles
		Collection<String> titles = root.stream()
											.map(d ->d.getTitle())
											.distinct()
											.collect(Collectors.toCollection(LinkedList::new));
		
		System.out.println(titles);
				
	}
	
	/**
	 * Function returns a list of all documents that are currently in a database
	 * 
	 * @return returns a list of all documents
	 */
	public List<Document> getAll(){
		return root;
		
	}
	
	
	
	
	
	
	
	
	
}