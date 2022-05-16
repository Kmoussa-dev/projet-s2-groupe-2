package projet.group2.gestionEmargement.dataTest;

import projet.group2.gestionEmargement.dto.SeanceDTO;

public interface DataTest {
    String numeroEtudiant();
    String emailEtudiant();
    String motDePasseEtudiant();

    String emailMembreAdministratif();

    String mdpMembreAdministratif();

    SeanceDTO seance1();
}
