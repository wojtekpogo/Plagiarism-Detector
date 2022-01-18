package ie.gmit.sw;

/**
 * Result class perform a template for results of similarity comparison.
 * The goal was to decouple classes and maintain a SRP
 */

public class Results {
	
	private String title;
	private double jaccardIndex;
	
	/**
	 * Default Constructor generated using Fields
	 * 
	 * @param title
	 *            sets the title
	 * @param jaccardIndex
	 *            to retrieve a jaccardIndex
	 */
	public Results(String title, double jaccardIndex) {
		super();
		this.title = title;
		this.jaccardIndex = jaccardIndex;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public double getJaccardIndex() {
		return jaccardIndex;
	}


	public void setJaccardIndex(double jaccardIndex) {
		this.jaccardIndex = jaccardIndex;
	}
	
}
