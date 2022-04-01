package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.repository.SecretaireRepository;

import java.util.List;

@Service
public class SecretaireService {

    @Autowired
    private SecretaireRepository secretaireRepository;

    public Secretaire inscription(Secretaire secretaire){
        return this.secretaireRepository.insert(secretaire);
    }

    public List<Secretaire> getSecretaires()
    {
        return this.secretaireRepository.findAll();
    }

    public Secretaire getSecretaireByEmail(String id){
        return this.secretaireRepository.getSecretaireByEmail(id);
    }

    public Secretaire getSecretaireById(String id){
        return this.secretaireRepository.getSecretaireById(id);
    }


}
