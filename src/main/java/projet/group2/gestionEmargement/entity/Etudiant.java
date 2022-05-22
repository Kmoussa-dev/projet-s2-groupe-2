package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

//@Document(collection="etudiants")
public class Etudiant extends Utilisateur {

    @Field(name = "numeroEtudiant")
    private String numeroEtudiant;

    private Groupe groupe;

    @Field(name = "promo")
    private Promotion promo;

    @Field(name = "otp")
    private boolean otp; // one time pad => autoristion unique
    @Field(name = "adresseMAC")
    private String adresseMAC;


    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public boolean isOtp() {
        return otp;
    }

    public void setOtp(boolean otp) {
        this.otp = otp;
    }

    public String getAdresseMAC() {
        return adresseMAC;
    }

    public void setAdresseMAC(String adresseMAC) {
        this.adresseMAC = adresseMAC;
    }
}
