package projet.group2.gestionEmargement.dto;

import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;
import java.util.Objects;

public class UtilisateurDTO {


    private String email;


    private  String nom;


    private  String prenom;


    private String  motDePasse;

    public UtilisateurDTO(String email, String nom, String prenom, String motDePasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public static Utilisateur toEntity(UtilisateurDTO utilisateurDTO){
        if(Objects.isNull(utilisateurDTO))
            return null;
        else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setNom(utilisateurDTO.getNom());
            utilisateur.setPrenom(utilisateurDTO.getPrenom());
            utilisateur.setMotDePasse(utilisateurDTO.getMotDePasse());
            utilisateur.setRoles(List.of("PROFESSEUR","ETUDIANT","SECRETAIRE"));
            return utilisateur;
        }
    }
}
