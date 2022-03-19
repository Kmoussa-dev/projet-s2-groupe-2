package projet.group2.gestionEmargement.entite;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Document(collection = "seance")
public class Seance {

    @Id
    private String idSeance;

    private MembreAdministrative createurSeance;

    @Field(name = "discipline")
    private String discipline;

    private Enseignant enseignant;
    private TypeSeance typeSeance;
    private List<Etudiant> etudiants;
    private List<Etudiant> etudiantsPresent;

    @Field(name = "heureDebut")
    private LocalDateTime heureDebut;
    @Field(name = "heureFin")
    private LocalDateTime heureFin;

    private Promotion promotion;

    private StatutSeance statutSeance;

    public Seance(String idSeance, String discipline, Enseignant enseignant, TypeSeance typeSeance, List<Etudiant> etudiants,
                  LocalDateTime heureDebut, LocalDateTime heureFin, Promotion promotion,MembreAdministrative membre) {
        this.idSeance = idSeance;
        this.discipline = discipline;
        this.enseignant = enseignant;
        this.typeSeance = typeSeance;
        this.etudiants = etudiants;
        this.etudiantsPresent = new ArrayList<>();
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.promotion = promotion;
        this.statutSeance = StatutSeance.NV;
        this.createurSeance=membre;
    }

    public String getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(String idSeance) {
        this.idSeance = idSeance;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public TypeSeance getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(TypeSeance typeSeance) {
        this.typeSeance = typeSeance;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Etudiant> getEtudiantsPresent() {
        return etudiantsPresent;
    }

    public void setEtudiantsPresent(List<Etudiant> etudiantsPresent) {
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

    public StatutSeance getStatutSeance() {
        return statutSeance;
    }

    public void setStatutSeance(StatutSeance statutSeance) {
        this.statutSeance = statutSeance;
    }

    public MembreAdministrative getCreateurSeance() {
        return createurSeance;
    }

    public void setCreateurSeance(MembreAdministrative createurSeance) {
        this.createurSeance = createurSeance;
    }
}
