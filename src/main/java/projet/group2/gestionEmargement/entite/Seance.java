package projet.group2.gestionEmargement.entite;

import java.time.LocalDateTime;
import java.util.List;

public class Seance {

    private long id;
    private Enseignant enseignant;
    private List<Etudiant> etudiants;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;

    public Seance(long id, Enseignant enseignant, List<Etudiant> etudiants, LocalDateTime heureDebut, LocalDateTime heureFin) {
        this.id = id;
        this.enseignant = enseignant;
        this.etudiants = etudiants;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
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
}
