package it.polito.tdp.porto;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
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
    		txtResult.clear();
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
    			
    		 txtResult.clear();
    		
    		 List<Paper> lista= new ArrayList<Paper>();
    		 lista =model.getPercorso3(boxPrimo.getValue(),boxSecondo.getValue());
    		 txtResult.appendText("il percorso tra "+boxPrimo.getValue()+" e "+boxSecondo.getValue()+" � :\n");
    		 for(Paper p: lista)
    			 txtResult.appendText(p+"\n");
    		 
    		 
    		 lista= new ArrayList<Paper>();
    		 txtResult.appendText("il percorso tra "+boxPrimo.getValue()+" e "+boxSecondo.getValue()+" � :\n");
    		 for(Paper p: model.getPercorso2(boxPrimo.getValue(),boxSecondo.getValue()))
    			 txtResult.appendText(p+"\n");
    		
    		 
    		 lista= new ArrayList<Paper>();
    		 lista =model.getPercorso1(boxPrimo.getValue(),boxSecondo.getValue());
    		txtResult.appendText("il percorso tra "+boxPrimo.getValue()+" e "+boxSecondo.getValue()+" � :\n");
    		 for(Paper p: lista)
    			 txtResult.appendText(p+"\n");
    		
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
