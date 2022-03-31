package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection="utilisateur")
public class Utilisateur {

    @Id
    private String idUtilisateur;

    @Field(name = "numeroEtudiant")
    private String numeroEtudiant;

    @Field(name = "fonction")
    private String fonction;

    @Field(name = "nomUtilisateur")
    private String nomUtilisateur;

    @Field(name = "prenomUtilisateur")
    private String prenomUtilisateur;

    private Groupe groupe;

    private Promotion promo;

    @Field(name = "email")
    private String email;

    @Field(name = "motDePasse")
    private String motDePasse;

    @Field(name = "roles")
    private List<String> roles;

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
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

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Promotion getPromo() {
        return promo;
    }
}
