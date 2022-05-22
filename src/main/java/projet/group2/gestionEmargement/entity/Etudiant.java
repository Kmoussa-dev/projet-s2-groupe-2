package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
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


    public Etudiant() {
    }

    public Etudiant(String email, String nom, String prenom, String motDePasse,
                    List<String> roles, String numeroEtudiant, Groupe groupe, Promotion promo) {
        super(email, nom, prenom, motDePasse, roles);
        this.numeroEtudiant = numeroEtudiant;
        this.groupe = groupe;
        this.promo = promo;
    }


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
