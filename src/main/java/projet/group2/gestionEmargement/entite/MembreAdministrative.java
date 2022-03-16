package projet.group2.gestionEmargement.entite;

public class MembreAdministrative {

    private String idMembre;
    private String nomMembre;
    private String prenomMenbre;

    public MembreAdministrative(String idMembre, String nomMembre, String prenomMenbre) {
        this.idMembre = idMembre;
        this.nomMembre = nomMembre;
        this.prenomMenbre = prenomMenbre;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public String getPrenomMenbre() {
        return prenomMenbre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public void setPrenomMenbre(String prenomMenbre) {
        this.prenomMenbre = prenomMenbre;
    }
}
