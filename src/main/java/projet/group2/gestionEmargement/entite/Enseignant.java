package projet.group2.gestionEmargement.entite;

public class Enseignant {

    private String idEnseignant;
    private String nomEseignant;
    private String prenomEtudiant;

    public Enseignant(String idEnseignant, String nomEseignant, String prenomEtudiant) {
        this.idEnseignant = idEnseignant;
        this.nomEseignant = nomEseignant;
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getIdEnseignant() {
        return idEnseignant;
    }

    public String getNomEseignant() {
        return nomEseignant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setIdEnseignant(String idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public void setNomEseignant(String nomEseignant) {
        this.nomEseignant = nomEseignant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }
}
