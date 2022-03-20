package projet.group2.gestionEmargement.entity;

import org.springframework.data.mongodb.core.mapping.Field;


public class Promotion {

    @Field(name = "niveau")
    private String niveau;

    @Field(name = "annee")
    private String annee;

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
