package it.polito.tdp.porto;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	
    	if(boxPrimo.getValue()!=null) {
    		List<Author> coautori=model.getCoautori(boxPrimo.getValue());
    		String s="";
    		for(Author a : coautori) {
    			s+= a+"\n";
    			}
    		txtResult.appendText(s);
    		List<Author> lista=model.getAutori();
    		lista.removeAll(coautori);
    		lista.remove(boxPrimo.getValue());
    		boxSecondo.getItems().addAll(lista);

    		
    	}

    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	if(boxSecondo.getValue()!=null) {
    		Path path=model.getPercorso(boxPrimo.getValue(),boxSecondo.getValue());
    		txtResult.appendText(path.toString());
    	}

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model m) {
		// TODO Auto-generated method stub
		model =m;
		boxPrimo.getItems().addAll(m.getAutori());
		m.creaGrafo();
		
	}
}
