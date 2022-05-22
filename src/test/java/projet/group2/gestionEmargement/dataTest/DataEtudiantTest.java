package projet.group2.gestionEmargement.dataTest;

import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

public interface DataEtudiantTest {
    EtudiantDTO inscriptionOk();
    String emailEtudiant();
    String nomEtudiant();
    String prenomEtudiant();
    String motDePasseEtudiant();
    String numeroEtudiant();
    Groupe groupeEtudiant();
    Promotion promoEtudiant();

    String emailEtudiant2();
    String nomEtudiant2();
    String prenomEtudiant2();
    String motDePasseEtudiant2();
    String numeroEtudiant2();
    Groupe groupeEtudiant2();
    Promotion promoEtudiant2();

}
