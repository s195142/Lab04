package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Studente> getTuttiGliStudenti() {
		// TODO Auto-generated method stub
		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");


				// Crea un nuovo JAVA Bean Corso
				Studente stu = new Studente(matricola, cognome, nome, cds);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.add(stu);
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

	public List<Corso> getCorsiAssegnatiAStudente(Studente s) {
		final String sql = "SELECT codins FROM iscrizione WHERE matricola = ?";
		List<Corso> corsiFrequentati = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola()); // quando c è "?"
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String codins = rs.getString("codins");
				Corso c = new Corso(codins);
				corsiFrequentati.add(c);
			}
			return corsiFrequentati;
		}catch (SQLException e){
			throw new RuntimeException("Errore DB");
		}
		
	}

	
	// COSI LO INSERISCE NEL DATABASE - FUNZIONA
//	public boolean isIscrittoACorso(Studente s, Corso c) {
//		final String sql = "SELECT * FROM iscrizione WHERE codins = ? AND matricola = ?";
//		boolean trovato = false;
//		
//		try {
//			Connection conn = ConnectDB.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setString(1, c.getCodins()); // quando c è "?"
//			st.setInt(2, s.getMatricola()); // quando c è "?"
//			
//			ResultSet rs = st.executeQuery();
//			
//			if (rs.next()) { // ??????
//				trovato = true;
//			}
//			
//			return trovato;
//		}catch (SQLException e){
//			throw new RuntimeException("Errore DB");
//		}
//	}

}
