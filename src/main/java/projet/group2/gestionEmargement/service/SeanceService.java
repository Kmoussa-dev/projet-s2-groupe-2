package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.*;
import projet.group2.gestionEmargement.exception.AppelleNonPrisEnCompteException;
import projet.group2.gestionEmargement.exception.CreationImpossibleDeSeanceException;
import projet.group2.gestionEmargement.exception.EtudiantInexistantException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.repository.SeanceRepository;
import projet.group2.gestionEmargement.repository.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Seance creer(Seance seance){
        List<String> numEtudiants;
        switch (seance.getTypeSeance()){
            case "TP":
                numEtudiants = this.etudiantRepository.getEtudiantsByPromoNiveauAndGroupeGroupeDeTP(seance.getPromotion().getNiveau(),seance.getGroupe().getGroupeDeTP()).stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                break;
            case "TD":
                numEtudiants = this.etudiantRepository.getEtudiantsByPromoNiveauAndGroupeGroupeDeTD(seance.getPromotion().getNiveau(),seance.getGroupe().getGroupeDeTD()).stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                break;
            default:
                numEtudiants = this.etudiantRepository.getEtudiantsByPromo(seance.getPromotion()).stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                break;
        }
        seance.setNumEtudiants(numEtudiants);
        return this.seanceRepository.insert(seance);
    }

    public List<Seance> getSeances(){
        return this.seanceRepository.findAll();
    }

    public Seance getSeanceById(String id){
        return this.seanceRepository.getSeanceById(id);
    }

    public Seance update(Seance seance){
        return this.seanceRepository.save(seance);
    }

    public void delete(Seance seance){
        this.seanceRepository.delete(seance);
    }


    public Seance getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(LocalDateTime heureDebut, LocalDateTime heureFin, String discipline, Groupe groupe){
        return this.seanceRepository.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe( heureDebut, heureFin, discipline, groupe);
    }

    public Seance emerger(String seanceid, String numEtudiant) throws EtudiantInexistantException, AppelleNonPrisEnCompteException
    {
        LocalDateTime now = LocalDateTime.now();
        Seance seance = this.getSeanceById(seanceid);
        if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant) && Objects.nonNull(seance)){
            if(now.isAfter(seance.getHeureDebut()) && now.isBefore(seance.getHeureFin()) && seance.getNumEtudiants().contains(numEtudiant)){
              if(Objects.isNull(seance.getNumEtudiantsPresent())){
                  seance.setNumEtudiantsPresent(List.of(new HeurePointage(now, numEtudiant)));
              }
              else {
                  seance.getNumEtudiantsPresent().add(new HeurePointage(now, numEtudiant));
              }

                return this.seanceRepository.save(seance);
            }
            else {
                throw new AppelleNonPrisEnCompteException();
            }
        }
        else{
            throw new EtudiantInexistantException();
        }
    }


}
