package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Utilisateur majUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public String supprimerUtilisateur(String id) {
        utilisateurRepository.deleteById(id);
        return "Student has been deleted.";
    }
}
