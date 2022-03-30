package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection="utilisateur")
public class Utilisateur {

    @Id
    private String id;

    @Field(name = "numeroEtudiant")
    private String numeroEtudiant;

    @Field(name = "fonction")
    private String fonction;

    @Field(name = "nom")
    private String nom;

    @Field(name = "prenom")
    private String prenom;

    private Groupe groupe;

    @Field(name = "promo")
    private Promotion promo;

    @Field(name = "email")
    private String email;

    @Field(name = "motDePasse")
    private String motDePasse;

    @Field(name = "roles")
    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
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
}


