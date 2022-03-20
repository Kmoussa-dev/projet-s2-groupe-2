package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "etudiant")
public class Etudiant {

    @Id
    private String id;
    @Field(name = "numeroEtudiant")
    private String numeroEtudiant;
    @Field(name = "nomEtudiant")
    private String nomEtudiant;
    @Field(name = "prenomEtudiant")
    private String prenomEtudiant;
    @Field(name = "adresseMail")
    private String adresseMail;

    private Promotion promo;

    @Field(name = "motDePasse")
    private String motDepasse;

    @Field(name = "roles")
    private List<String> roles;


    public Etudiant() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotDepasse() {
        return motDepasse;
    }

    public void setMotDepasse(String motDepasse) {
        this.motDepasse = motDepasse;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
