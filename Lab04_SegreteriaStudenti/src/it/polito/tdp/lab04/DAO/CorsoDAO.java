package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
		final String sql = "SELECT matricola FROM iscrizione WHERE codins = ?";
		List<Studente> studentiIscritti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins()); // quando c è "?"

			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				int matricola = rs.getInt("matricola");

				// Crea un nuovo JAVA Bean Corso
				Studente s = new Studente(matricola);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studentiIscritti.add(s);
			}
			return studentiIscritti;
		}catch (SQLException e){
			throw new RuntimeException("Errore DB");
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
//	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
//		String sql = "INSERT IGNORE INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES(?,?)";
//		boolean iscr = false;
//		
//		try {
//			Connection conn = ConnectDB.getConnection();
//			PreparedStatement st = conn.prepareStatement(sql);
//			st.setInt(1, studente.getMatricola());
//			st.setString(2, corso.getCodins());
//			
//			int res = st.executeUpdate();	// ?? cosa cambia da execute query
//
//			if (res == 1) // cos'è
//				iscr = true;
//
//			return iscr;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Errore Db");
//		}
//		
//	}
}
