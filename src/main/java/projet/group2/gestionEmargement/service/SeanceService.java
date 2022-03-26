package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.exception.CreationImpossibleDeSeanceException;
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
    SeanceRepository seanceRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;


    //Question pour le prof
    //Est ce que dans le mongodb on vérifie s'il existe le meme Utilisateur dans la base de données
    //En gros peut-on dupliquer des données
    public Seance creerSeance(Seance seance) throws CreationImpossibleDeSeanceException {
        //Récupération de la liste des etudiants de la promo
        //Penser à gérer avec les roles
        seance.setEtudiants(utilisateurRepository.findByFonction("ETUDIANT"));
        //Problème:Création d'une séance avec la contrainte du créneau
        List<Seance> seances=seanceRepository.findAll();
        //Vérifier s'il existe dans le Mongo
        Utilisateur enseignant=utilisateurRepository.findByEmail(seance.getEnseignant().getEmail());
        Utilisateur createurSeance= utilisateurRepository.findByEmail(seance.getCreateurSeance().getEmail());
        if (Objects.isNull(enseignant)){
            utilisateurRepository.save(seance.getEnseignant());
        }
        if (Objects.isNull(createurSeance)){
            utilisateurRepository.save(seance.getCreateurSeance());
        }

        //les creneaux ne peuvent se chevaucher
        LocalDate date=seance.getHeureFin().toLocalDate();
        LocalTime timeFin=seance.getHeureFin().toLocalTime();
        LocalTime timeDebut=seance.getHeureDebut().toLocalTime();

        boolean possibiliteDeCreerSurUneDate=false;
        boolean possibiliteDeCreerSurUneAutreDate=false;

        for (Seance iSeance:seances){
            LocalDate iSeanceDate=iSeance.getHeureDebut().toLocalDate();
            LocalTime iSeanceTimeDebut=iSeance.getHeureDebut().toLocalTime();
            LocalTime iSeanceTimeFin=iSeance.getHeureFin().toLocalTime();
            if(iSeanceDate.equals(date)){
                if(iSeanceTimeDebut.isBefore(timeFin)&&iSeanceTimeDebut.isAfter(timeDebut)){
                    possibiliteDeCreerSurUneDate = false;
                }
                else if (iSeanceTimeFin.isBefore(timeFin)&&iSeanceTimeFin.isAfter(timeDebut)){
                    possibiliteDeCreerSurUneDate=false;
                }
                else if (iSeanceTimeDebut.isBefore(timeDebut)&&iSeanceTimeFin.isAfter(timeFin)){
                    possibiliteDeCreerSurUneDate=false;
                }
                else {
                    possibiliteDeCreerSurUneDate=true;
                }
            }
            else {
                possibiliteDeCreerSurUneAutreDate=true;
            }
        }

        if (possibiliteDeCreerSurUneDate||possibiliteDeCreerSurUneAutreDate) return seanceRepository.save(seance);
        return seance;
    }

//    public int comprareLocalDateTime(LocalDateTime ldt1,LocalDateTime ldt2){
////        LocalDateTime ldt1 = LocalDateTime.parse("2019-04-28T22:32:38.536");
////        LocalDateTime ldt2 = LocalDateTime.parse("2017-01-14T15:32:56.000");
//
//        int diff = ldt1.compareTo(ldt2);
//        if (diff < 0) {
//            // return "ldt1 est supérieur à ldt2";
//                return 1;
//        } else if (diff > 0) {
//           //  return "ldt1 est supérieur à ldt2";
//            return 2;
//        } else {
//            return 0;
//        }
//    }
//



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
