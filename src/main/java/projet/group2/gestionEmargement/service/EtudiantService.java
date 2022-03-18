package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant inscriptionEtudiant(Etudiant etudiant) {
        return this.etudiantRepository.save(etudiant);
    }

}
