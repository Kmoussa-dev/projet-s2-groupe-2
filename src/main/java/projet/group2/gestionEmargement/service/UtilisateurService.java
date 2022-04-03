package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.repository.EnseignantRepository;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.repository.SecretaireRepository;
import projet.group2.gestionEmargement.repository.UtilisateurRepository;

import java.util.Collection;
import java.util.Objects;

@Service
public class UtilisateurService {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private SecretaireRepository secretaireRepository;


    public Utilisateur getUtilisateur(String login) {

        Etudiant etudiant = this.etudiantRepository.getEtudiantByEmail(login);
        Enseignant enseignant = this.enseignantRepository.getEnseignantByEmail(login);
        Secretaire secretaire = this.secretaireRepository.getSecretaireByEmail(login);

        if (Objects.nonNull(etudiant)) {
            return etudiant;
        }
        else if (Objects.nonNull(enseignant)) {
            return enseignant;
        }
        else if (Objects.nonNull(secretaire)) {
            return secretaire;
        }
        else {
            return null;
        }

    }


//    @Autowired
//    UtilisateurRepository utilisateurRepository;
//
//    public Utilisateur creerUtilisateur(Utilisateur utilisateur){
//        return utilisateurRepository.save(utilisateur);
//    }
//
//    public Utilisateur getUtilisateurById(String id) {
//        return utilisateurRepository.findById(id).get();
//    }
//
//    public List<Utilisateur> getAllUtilisateurs() {
//        return utilisateurRepository.findAll();
//    }
//
//    public List<Utilisateur> getEtudiants(){return utilisateurRepository.findByFonction("ETUDIANT");}
//
//    public List<Utilisateur> getEnseignants(){return utilisateurRepository.findByFonction("ENSEIGNANT");}
//
//    public List<Utilisateur> getMembreAdministratifs(){return utilisateurRepository.findByFonction("MEMBRE ADMINISTRATIF");}
//
//    public Utilisateur majUtilisateur(Utilisateur utilisateur) {
//        return utilisateurRepository.save(utilisateur);
//    }
//
//    public String supprimerUtilisateur(String id) {
//        utilisateurRepository.deleteById(id);
//        return "Student has been deleted.";
//    }
}
