package projet.group2.gestionEmargement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Secretaire;

import java.util.List;
import java.util.Objects;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecretaireDTO  {

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

    public static Secretaire toEntity(UtilisateurDTO utilisateurDTO){
        if(Objects.isNull(utilisateurDTO))
            return null;
        else {
            Secretaire secretaire = new Secretaire();
            secretaire.setEmail(utilisateurDTO.getEmail());
            secretaire.setNom(utilisateurDTO.getNom());
            secretaire.setPrenom(utilisateurDTO.getPrenom());
            secretaire.setMotDePasse(utilisateurDTO.getEmail());
            secretaire.setRoles(List.of("MEMBRE_ADMINISTRATIF"));
            return secretaire;
        }
    }

    public static SecretaireDTO fromEntity(Secretaire secretaire){
        if(Objects.isNull(secretaire))
            return null;
        else {
            return SecretaireDTO.builder()
                    .email(secretaire.getEmail())
                    .nom(secretaire.getNom())
                    .prenom(secretaire.getPrenom())
                    .build();
        }
    }

}
