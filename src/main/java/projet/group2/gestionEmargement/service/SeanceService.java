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
import java.util.List;
import java.util.Objects;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Seance creer(Seance seance){
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

    public Seance emerger(String seanceid, String numEtudiant) throws EtudiantInexistantException, AppelleNonPrisEnCompteException{
        LocalDateTime now = LocalDateTime.now();
        Seance seance = this.getSeanceById(seanceid);
        if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant) && Objects.nonNull(seance)){
            if(seance.getHeureDebut().isBefore(now) && seance.getHeureFin().isAfter(now) && seance.getNumEtudiants().contains(numEtudiant)){
                seance.getNumEtudiantsPresent().add(new HeurePointage(now, numEtudiant));
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
