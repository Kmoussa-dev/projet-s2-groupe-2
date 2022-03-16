package projet.group2.gestionEmargement.entite;

public class Enseignant {

    private String idEnseignant;
    private String nomEseignant;
    private String prenomEnseignant;

    public Enseignant(String idEnseignant, String nomEseignant, String prenomEnseignant) {
        this.idEnseignant = idEnseignant;
        this.nomEseignant = nomEseignant;
        this.prenomEnseignant = prenomEnseignant;
    }

    public String getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(String idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNomEseignant() {
        return nomEseignant;
    }

    public void setNomEseignant(String nomEseignant) {
        this.nomEseignant = nomEseignant;
    }

    public String getPrenomEnseignant() {
        return prenomEnseignant;
    }

    public void setPrenomEnseignant(String prenomEnseignant) {
        this.prenomEnseignant = prenomEnseignant;
    }
}
