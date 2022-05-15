package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.exception.EtudiantDejaExisteException;
import projet.group2.gestionEmargement.exception.MotDePasseObligatoireException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

import java.util.*;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant inscription(Etudiant etudiant) throws EtudiantDejaExisteException, MotDePasseObligatoireException {
        if(this.etudiantRepository.findEtudiantByNumeroEtudiant(etudiant.getNumeroEtudiant())!=null) {
            throw new EtudiantDejaExisteException();
        }
        if (etudiant.getMotDePasse() == null || etudiant.getMotDePasse().isBlank()) {
            throw new MotDePasseObligatoireException();
        }
        return this.etudiantRepository.insert(etudiant);
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiantRepository.findAll();
    }

    public Etudiant getEtudiantbyNumeroEtudiant(String numeroEtudiant){
        return this.etudiantRepository.findEtudiantByNumeroEtudiant(numeroEtudiant);
    }
    public Etudiant getEtudiantbyEmail(String email){
        return this.etudiantRepository.getEtudiantByEmail(email);
    }

    public List<Etudiant> getEtudiantsByPromo(Promotion promotion){
        return this.etudiantRepository.getEtudiantsByPromo(promotion);
    }



}
