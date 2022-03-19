package projet.group2.gestionEmargement.entite;

import org.springframework.data.mongodb.core.mapping.Field;

public class MembreAdministrative {

    @Field(name = "idMembre")
    private String idMembre;
    @Field(name = "nomMembre")
    private String nomMembre;
    @Field(name = "prenomMembre")
    private String prenomMembre;

    public MembreAdministrative(String idMembre, String nomMembre, String prenomMembre) {
        this.idMembre = idMembre;
        this.nomMembre = nomMembre;
        this.prenomMembre = prenomMembre;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public void setPrenomMembre(String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }
}
