/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tendina"
    private ComboBox<Corso> tendina; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="imgVerde"
    private ImageView imgVerde; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCerca(ActionEvent event) {
    	int matricola;
    	List<Studente> studentil = new LinkedList<Studente>(model.getStudenti());
    	Corso c = tendina.getValue();

    	//controlla se non ho selezionato nulla
    	if(c==null) {
    		txtResult.setText("Errore: selezionare un corso.");
    		return;
    	}
    	
    	//controlla se è un intero
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e){
    		txtResult.setText("Errore: matricola non valida.\n");
    		return;
    	}
    	
    	//controlla se lo studente è presente
    	Studente s1 = new Studente(matricola);
    	
    	if(!studentil.contains(s1)) {
    		txtResult.setText("Errore: studente non presente nel database.");
    		return;
    	}
    	
    	if(model.isIscritto(s1, c))
    		txtResult.setText("Lo studente è iscritto a questo corso");
    	else
    		txtResult.setText("Lo studente non è iscritto a questo corso");
    	
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola;
    	List<Studente> studentil = new LinkedList<Studente>(model.getStudenti());

    	//controlla se è un intero
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e){
    		txtResult.setText("Errore: matricola non valida.\n");
    		return;
    	}
    	
    	//controlla se lo studente è presente
    	Studente s1 = new Studente(matricola);
    	
    	if(!studentil.contains(s1)) {
    		txtResult.setText("Errore: studente non presente nel database.");
    		return;
    	}
    	
    	String risultato ="";
    	
    	risultato += model.getCorsiStudente(s1);
    	txtResult.setText(risultato);
    	
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	Corso c = tendina.getValue();
    	String risultato = "";
    	if(c==null) {
    		txtResult.setText("Errore: selezionare un corso.");
    		return;
    	}
    	risultato+=model.getStudentiIscritti(c);
    	txtResult.setText(risultato);

    }

    @FXML
    void doCompletamento(MouseEvent event) {
    	int matricola;
    	List<Studente> studentil = new LinkedList<Studente>(model.getStudenti());
    	
    	//controlla se è un intero
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e){
    		txtResult.setText("Errore: matricola non valida.\n");
    		return;
    	}
    	
    	//controlla se lo studente è presente
    	Studente s1 = new Studente(matricola);
    	if(!studentil.contains(s1)) {
    		txtResult.setText("Errore: studente non presente nel database.");
    	}
    	
    	
    	for(Studente s : studentil) {
    		if(s.getMatricola()==matricola) {
    			txtCognome.setText(s.getCognome());
    			txtNome.setText(s.getNome());
    		}
    	}
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola;
    	List<Studente> studentil = new LinkedList<Studente>(model.getStudenti());

    	//controlla se è un intero
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e){
    		txtResult.setText("Errore: matricola non valida.\n");
    		return;
    	}
    	
    	//controlla se lo studente è presente
    	Studente s = new Studente(matricola);
    	
    	if(!studentil.contains(s)) {
    		txtResult.setText("Errore: studente non presente nel database.");
    		return;
    	}
    	
       	Corso c = tendina.getValue();
    	if(c==null) {
    		txtResult.setText("Errore: selezionare un corso.");
    		return;
    	}
    	
    	if(!model.isIscritto(s, c)) {
    		model.iscrivi(s,c);
    		txtResult.setText("Lo studente è stato iscritto correttamente al corso.");
    	}else {
    		txtResult.setText("Lo studente è già iscritto al corso.");
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
		tendina.getSelectionModel().clearSelection(); // sempre così x resettare la tendina
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tendina != null : "fx:id=\"tendina\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert imgVerde != null : "fx:id=\"imgVerde\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		tendina.getItems().addAll(model.getCorsi());
	}
}
