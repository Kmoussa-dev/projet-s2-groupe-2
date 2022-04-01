package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

import java.util.*;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant inscription(Etudiant etudiant){
        return this.etudiantRepository.insert(etudiant);
    }

    public List<Etudiant> getEtudiants()
    {
        return this.etudiantRepository.findAll();
    }

    public Etudiant getEtudiantbyNumeroEtudiant(String numeroEtudiant){
        return this.etudiantRepository.getEtudiantByNumeroEtudiant(numeroEtudiant);
    }

    public List<Etudiant> getEtudiantsByPromo(Promotion promotion){
        return this.etudiantRepository.getEtudiantsByPromo(promotion);
    }



}
