package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDao = new CorsoDAO();
	private StudenteDAO studenteDao = new StudenteDAO();
	
	public List<Corso> getCorsi() {
		
		return corsoDao.getTuttiICorsi();
	}

	public List<Studente> getStudenti() {
		return studenteDao.getTuttiGliStudenti();
	}

	public List<Studente> getStudentiIscritti(Corso corso) {
		List<Studente> matricoleStudentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(corso));
		List<Studente> allStudents = new LinkedList<Studente>(studenteDao.getTuttiGliStudenti());
		List<Studente> iscritti = new LinkedList<Studente>();
		for(Studente s : allStudents) {
			for(Studente s1 : matricoleStudentiIscritti) {
				if(s.getMatricola()==s1.getMatricola()) {
					iscritti.add(s);
				}
			}
		}
		return iscritti;
	}

	public List<Corso> getCorsiStudente(Studente s) {
		List<Corso> codinsCorsi = new LinkedList<Corso>(studenteDao.getCorsiAssegnatiAStudente(s));
		List<Corso> allCourses = new LinkedList<Corso>(corsoDao.getTuttiICorsi());
		List<Corso> corsiACuiIscritto = new LinkedList<Corso>();
		for(Corso c : allCourses) {
			for(Corso c1 : codinsCorsi) {
				if(c.getCodins().compareTo(c1.getCodins())==0)
					corsiACuiIscritto.add(c);
			}
		}
		return corsiACuiIscritto;
	}

	public boolean isIscritto(Studente s, Corso c) {
		boolean iscritto = false;
		List<Studente> studentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(c));
		for(Studente stu : studentiIscritti) {
			if(stu.getMatricola()==s.getMatricola()) {
				iscritto = true;
			}
		}
//		return studenteDao.isIscrittoACorso(s, c);
		return iscritto;
	}

	// LO INSERISCE NELLA LISTA MA NON NEL DATABASE - NON VA
	public void iscrivi(Studente s, Corso c) {
		List<Studente> studentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(c));
		if(!studentiIscritti.contains(s)) {
			studentiIscritti.add(s);
		}
//		return corsoDao.iscriviStudenteACorso(s, c);
	}


}
