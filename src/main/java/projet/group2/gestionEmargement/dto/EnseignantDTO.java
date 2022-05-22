package projet.group2.gestionEmargement.dto;

import lombok.Builder;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;
import java.util.Objects;
@Builder
public class EnseignantDTO {

    private String email;

    private  String nom;

    private  String prenom;

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

    public static Enseignant toEntity(UtilisateurDTO utilisateurDTO){
        if(Objects.isNull(utilisateurDTO))
            return null;
        else {
            Enseignant enseignant = new Enseignant();
            enseignant.setEmail(utilisateurDTO.getEmail());
            enseignant.setNom(utilisateurDTO.getNom());
            enseignant.setPrenom(utilisateurDTO.getPrenom());
            enseignant.setMotDePasse(utilisateurDTO.getMotDePasse());
            enseignant.setRoles(List.of("ENSEIGNANT"));
            return enseignant;
        }
    }
    public static EnseignantDTO fromEntity(Enseignant enseignant){
        if(Objects.isNull(enseignant))
            return null;
        else {
            return EnseignantDTO.builder()
                    .email(enseignant.getEmail())
                    .nom(enseignant.getNom())
                    .prenom(enseignant.getPrenom())
                    .build();
        }
    }
}
