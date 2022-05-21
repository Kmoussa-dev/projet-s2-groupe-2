package projet.group2.gestionEmargement.dataTest;

import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.EtudiantDTO;

public interface DataEnseignantTest {

    EnseignantDTO inscriptionOk();
    String emailEnseignant();
    String motDePasseEnseignant();
}
