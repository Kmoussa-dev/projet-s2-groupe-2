package projet.group2.gestionEmargement.dataTest;


import projet.group2.gestionEmargement.dto.SeanceDTO;

public interface DataSeanceTest {

    String emailMembreAdministratif();

    String mdpGeneral();

    SeanceDTO seanceOK();

    SeanceDTO seanceDejaExistante();

    String emailEtudiant();

    String emailEnseignant();

    String emailUtilisateurNonAuthentifie();

    String idSeanceInexistant();

}
