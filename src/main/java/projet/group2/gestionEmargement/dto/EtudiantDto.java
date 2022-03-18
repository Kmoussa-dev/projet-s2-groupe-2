package projet.group2.gestionEmargement.dto;

public class EtudiantDto {

    private String numeroEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;

    public EtudiantDto(String numeroEtudiant, String nomEtudiant, String prenomEtudiant) {

        this.numeroEtudiant = numeroEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }
}
