package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.repository.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur creerUtilisateur(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(String id) {
        return utilisateurRepository.findById(id).get();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> getEtudiants(){return utilisateurRepository.findByFonction("ETUDIANT");}

    public List<Utilisateur> getEnseignants(){return utilisateurRepository.findByFonction("ENSEIGNANT");}

    public List<Utilisateur> getMembreAdministratifs(){return utilisateurRepository.findByFonction("MEMBRE ADMINISTRATIF");}

    public Utilisateur majUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

//    public List<Utilisateur> getUtilisateurByGroupe(Groupe groupe) {
//        return utilisateurRepository.findByGroupe(groupe);
//    }

    public List<Utilisateur> getUtilisateurByPromoNiveau(String niveau) {
        return utilisateurRepository.findByPromoNiveau(niveau);
    }

    public List<Utilisateur> getUtilisateurByPromoNiveauAndAnnee(String niveau,String annee) {
        return utilisateurRepository.findByPromoNiveauAndPromoAnnee(niveau,annee);
    }

    public String supprimerUtilisateur(String id) {
        utilisateurRepository.deleteById(id);
        return "Student has been deleted.";
    }
}
