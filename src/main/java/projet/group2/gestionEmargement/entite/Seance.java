package projet.group2.gestionEmargement.entite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// doit etre un document
public class Seance {

    private long idSeance;
    private String discipline;
    private Enseignant enseignant;
    private TypeSeance typeSeance;
    private List<Etudiant> etudiants;
    private List<Etudiant> etudiantsPresent;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private Promotion promotion;
    private StatutSeance statutSeance;

    public Seance(long idSeance, String discipline, Enseignant enseignant, TypeSeance typeSeance, List<Etudiant> etudiants,
                  LocalDateTime heureDebut, LocalDateTime heureFin, Promotion promotion, StatutSeance statutSeance) {
        this.idSeance = idSeance;
        this.discipline = discipline;
        this.enseignant = enseignant;
        this.typeSeance = typeSeance;
        this.etudiants = etudiants;
        this.etudiantsPresent = new ArrayList<>();
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.promotion = promotion;
        this.statutSeance = StatutSeance.NonValidee;
    }

    public long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(long idSeance) {
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
}
