package projet.group2.gestionEmargement.entite;

import org.springframework.data.mongodb.core.mapping.Field;

public class Enseignant {
    @Field(name = "idEnseigant")
    private String idEnseignant;
    @Field(name = "nomEnseignant")
    private String nomEseignant;
    @Field(name = "prenomEnseignant")
    private String prenomEnseignant;

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
