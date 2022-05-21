package projet.group2.gestionEmargement.dataTest;

import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;

public interface DataEtudiantTest {
    EtudiantDTO inscriptionOk();
    String numeroEtudiant();
    String emailEtudiant();
    String motDePasseEtudiant();
}
