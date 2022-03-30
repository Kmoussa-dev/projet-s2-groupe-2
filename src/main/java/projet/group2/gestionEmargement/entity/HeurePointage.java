package projet.group2.gestionEmargement.entity;

import java.time.LocalDateTime;

public class HeurePointage {
    private LocalDateTime pointage;
    private Utilisateur etudiant;


    public LocalDateTime getPointage() {
        return pointage;
    }

    public void setPointage(LocalDateTime pointage) {
        this.pointage = pointage;
    }

    public Utilisateur getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Utilisateur etudiant) {
        this.etudiant = etudiant;
    }
}
