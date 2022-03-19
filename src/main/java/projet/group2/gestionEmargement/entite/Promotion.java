package projet.group2.gestionEmargement.entite;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


public class Promotion {
//    @Id
//    private String idPromotion;

    private Niveau niveau;

    @Field(name = "annee")
    private String annee;

//    public String getIdPromotion() {
//        return idPromotion;
//    }
//
//    public void setIdPromotion(String idPromotion) {
//        this.idPromotion = idPromotion;
//    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
