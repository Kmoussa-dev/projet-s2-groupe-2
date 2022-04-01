package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.repository.EnseignantRepository;
import projet.group2.gestionEmargement.repository.SecretaireRepository;

import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;


    public Enseignant inscription(Enseignant enseignant){
        return this.enseignantRepository.insert(enseignant);
    }

    public List<Enseignant> getEnseignants()
    {
        return this.enseignantRepository.findAll();
    }

    public Enseignant getEnseignantByEmail(String id){
        return this.enseignantRepository.getEnseignantByEmail(id);
    }

    public Enseignant getEnseignantById(String id){
        return this.enseignantRepository.getEnseignantById(id);
    }
}


