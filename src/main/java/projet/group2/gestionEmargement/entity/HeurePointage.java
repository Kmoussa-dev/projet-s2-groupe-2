package projet.group2.gestionEmargement.entity;

import java.time.LocalDateTime;

public class HeurePointage {
    private LocalDateTime pointage;
    private String numEtudiant;

    public HeurePointage(LocalDateTime pointage, String numEtudiant) {
        this.pointage = pointage;
        this.numEtudiant = numEtudiant;
    }

    public LocalDateTime getPointage() {
        return pointage;
    }

    public void setPointage(LocalDateTime pointage) {
        this.pointage = pointage;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }
}
