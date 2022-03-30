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

    private Utilisateur createurSeance;

    @Field(name = "discipline")
    private String discipline;

    private Utilisateur enseignant;

    @Field(name = "typeDeSeance")
    private String typeSeance;

    private Groupe groupe;

    private List<Utilisateur> etudiants;

    private List<HeurePointage> etudiantsPresent;

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

    public Utilisateur getCreateurSeance() {
        return createurSeance;
    }

    public void setCreateurSeance(Utilisateur createurSeance) {
        this.createurSeance = createurSeance;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Utilisateur getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Utilisateur enseignant) {
        this.enseignant = enseignant;
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

    public List<Utilisateur> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Utilisateur> etudiants) {
        this.etudiants = etudiants;
    }

    public List<HeurePointage> getEtudiantsPresent() {
        return etudiantsPresent;
    }

    public void setEtudiantsPresent(List<HeurePointage> etudiantsPresent) {
        this.etudiantsPresent = etudiantsPresent;
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
