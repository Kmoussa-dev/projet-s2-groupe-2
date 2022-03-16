package projet.group2.gestionEmargement.entite;


public class Etudiant {

    private String numeroEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String adresseMail;
    private Promotion promo;
    private Groupe groupe;


    public Etudiant(String numeroEtudiant, String nomEtudiant, String prenomEtudiant, String adresseMail, Promotion promo, Groupe groupe) {
        this.numeroEtudiant = numeroEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.adresseMail = adresseMail;
        this.promo = promo;
        this.groupe = groupe;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public Promotion getPromo() {
        return promo;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
