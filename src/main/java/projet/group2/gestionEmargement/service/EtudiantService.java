package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;

    public Etudiant creerEtudiant(Etudiant etudiant){
        return etudiantRepository.save(etudiant);
    }

    public Etudiant getEtudiantById(String id) {
        return etudiantRepository.findById(id).get();
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant majEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public String supprimerEtudiant(String id) {
        etudiantRepository.deleteById(id);
        return "Student has been deleted.";
    }
}
