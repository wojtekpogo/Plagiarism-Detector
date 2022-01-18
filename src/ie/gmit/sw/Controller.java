package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/* Performs GUI related actions.
 */
public class Controller {
	/* The following instance variables use the @FXML annotation to bind to the controls
	 * declared in the FXML file. The name of the control must match the fx:id attribute 
	 * of an FXML element, e.g. the VBox container is bound to the root VBox declared as
	 * fx:id="container" in the file window.xml.
	 */
	@FXML private VBox container;
	@FXML private VBox chartArea;
	@FXML private TextField txtFile;
	@FXML private Button btnClose;
	@FXML private Button btnOpen;
	@FXML private Button btnCompare;
	private Handler h = new Handler();
	
	
	@FXML public void initialize() {
		/*
		 * Plant an observer on the button btnClose. The event dispatch thread (the
		 * application thread in JavaFX) will call the action specified by the lambda
		 * expression. 
		 */
		btnClose.setOnAction(e -> ((Stage) btnClose.getScene().getWindow()).close());
		
		/*
		 * Plant an observer on the button btnCompare. The action or call-back method
		 * should compute the Jaccard similarity of the text file specified from the
		 * FileChooser against the subject document(s) stored by the application. When
		 * the Jaccard similarity has been computed, the application should display the
		 * result in some way.
		 */
		
		
		btnCompare.setOnAction(e -> {
			/*
			 * Do the comparing with Jaccard Similarity and MinHash and compute the data
			 * for the Pie Chart. *** Do not hard-code these values *** as shown below.
			 * The data needs to be computed dynamically.
			 */
					
			// Get the title of the file
			File f = new File(txtFile.getText());
			String title = f.getName();
			int size = title.length();
			title = title.substring(0,(size -4)); // remove .txt
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(txtFile.getText()));
			} catch(FileNotFoundException e1) {
				e1.printStackTrace();
			}
	
		
			Map<String, Double> map = new HashMap<>();
			try {
				// processing the file, Jaccard Similarity
				map = h.process(title, br);
				
	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			
			//Display the results in a pie chart
			PieChart chart = new PieChart(); //Use PieChart from javafx.scene.chart
			// Loop over a map to get a corresponding title
			 for (Map.Entry<String,Double> entry : map.entrySet())
				 	// getKey returns a title of a document, getValue is a JaccardIndex
		           addPieChartData(chart, entry.getKey(), entry.getValue());
			chartArea.getChildren().clear(); //Remove any existing chart
			chartArea.getChildren().add(chart); //Add the new chart to the tree
		});
		
		//The class FileChooser is not a control and cannot be specified in FXML
		FileChooser fc = new FileChooser(); //A leaf node
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt")); //Filter for TXT files
		btnOpen.setOnAction(e -> { //Plant an observer on the button btnOpen
			File f = fc.showOpenDialog(container.getScene().getWindow());
			
			txtFile.setText(f.getAbsolutePath());
		});
	}
	
	 /**
	 * Method to add the data to the pie chart.
	 * 
	 * @param PieChart pChart
	 * 			Piechart where data is displated
	 * @param String name
	 * 			name of the document
	 * @param double value
	 * 
	 */
	public void addPieChartData(PieChart pChart, String name, double value) {
	        final javafx.scene.chart.PieChart.Data data = new Data(name, value);
	        pChart.getData().add(data);
	    }
}