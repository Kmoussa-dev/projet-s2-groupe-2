package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection="utilisateurs")
public class Utilisateur {

    @Id
    @Field(name = "email")
    protected String email;

    @Field(name = "nom")
    protected String nom;

    @Field(name = "prenom")
    protected String prenom;

    @Field(name = "motDePasse")
    protected String motDePasse;

    @Field(name = "roles")
    protected List<String> roles;

    public Utilisateur() {
    }

    public Utilisateur(String email, String nom, String prenom, String motDePasse, List<String> roles) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.roles = roles;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

//    public void setGroupe(Groupe groupe) {
//        this.groupe = groupe;
//    }
//
//    public void setPromo(Promotion promo) {
//        this.promo = promo;
//    }
//
//    public Groupe getGroupe() {
//        return groupe;
//    }
//
//    public Promotion getPromo() {
//        return promo;
//    }
}


