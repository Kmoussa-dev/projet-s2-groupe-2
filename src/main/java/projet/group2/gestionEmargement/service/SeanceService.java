package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.repository.SeanceRepository;

import java.util.List;

@Service
public class SeanceService {

    @Autowired
    SeanceRepository seanceRepository;

    public Seance creerSeance(Seance seance){
        return seanceRepository.save(seance);
    }

    public Seance getSeanceById(String id) {
        return seanceRepository.findById(id).get();
    }

    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    public Seance majSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    public String supprimerSeance(String id) {
        seanceRepository.deleteById(id);
        return "Seance a été supprimé.";
    }

}
