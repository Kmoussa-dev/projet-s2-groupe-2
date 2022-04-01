package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "seance")
public class Seance {

    @Id
    private String id;

    @Field(name = "createurSeance")
    private String userID;

    @Field(name = "discipline")
    private String discipline;

    private String enseignantID;

    @Field(name = "typeDeSeance")
    private String typeSeance;

    private Groupe groupe;

    private List<String> numEtudiants;

    private List<HeurePointage> numEtudiantsPresent;

    @Field(name = "heureDebut")
    private LocalDateTime heureDebut;

    @Field(name = "heureFin")
    private LocalDateTime heureFin;

    private Promotion promotion;

    @Field(name = "statutSeance")
    private String statutSeance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getNumEtudiants() {
        return numEtudiants;
    }

    public void setNumEtudiants(List<String> numEtudiants) {
        this.numEtudiants = numEtudiants;
    }

    public List<HeurePointage> getNumEtudiantsPresent() {
        return numEtudiantsPresent;
    }

    public void setNumEtudiantsPresent(List<HeurePointage> numEtudiantsPresent) {
        this.numEtudiantsPresent = numEtudiantsPresent;
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
}
