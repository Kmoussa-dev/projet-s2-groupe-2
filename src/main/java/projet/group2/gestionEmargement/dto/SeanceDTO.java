package projet.group2.gestionEmargement.dto;

import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.entity.Seance;

import java.time.LocalDateTime;
import java.util.Objects;

public class SeanceDTO {
    private String userID;


    private String discipline;

    private String enseignantID;


    private String typeSeance;

    private Groupe groupe;

    private LocalDateTime heureDebut;


    private LocalDateTime heureFin;

    private Promotion promotion;


    private String statutSeance;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getEnseignantID() {
        return enseignantID;
    }

    public void setEnseignantID(String enseignantID) {
        this.enseignantID = enseignantID;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String getStatutSeance() {
        return statutSeance;
    }

    public void setStatutSeance(String statutSeance) {
        this.statutSeance = statutSeance;
    }

    public static Seance toEntity(SeanceDTO seanceDTO){
        if(Objects.isNull(seanceDTO))
            return null;
        else {
            Seance seance = new Seance();
            seance.setUserID(seanceDTO.getUserID());
            seance.setDiscipline(seanceDTO.getDiscipline());
            seance.setEnseignantID(seanceDTO.getEnseignantID());
            seance.setTypeSeance(seanceDTO.getTypeSeance());
            seance.setGroupe(seanceDTO.getGroupe());
            seance.setHeureDebut(seanceDTO.getHeureDebut());
            seance.setHeureFin(seanceDTO.getHeureFin());
            seance.setPromotion(seanceDTO.getPromotion());
            seance.setStatutSeance(seanceDTO.getStatutSeance());
            return seance;
        }
    }

//    public static SeanceDTO fromEntity(Seance seance){
//
//    }
}
