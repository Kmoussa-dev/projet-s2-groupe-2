package projet.group2.gestionEmargement.dto;

import projet.group2.gestionEmargement.entity.Secretaire;

import java.util.List;
import java.util.Objects;

public class SecretaireDTO extends UtilisateurDTO {

    public static Secretaire toEntity(SecretaireDTO secretaireDTO){
        if(Objects.isNull(secretaireDTO))
            return null;
        else {
            Secretaire secretaire = new Secretaire();
            secretaire.setEmail(secretaireDTO.getEmail());
            secretaire.setNom(secretaireDTO.getNom());
            secretaire.setPrenom(secretaireDTO.getPrenom());
            secretaire.setMotDePasse(secretaireDTO.getMotDePasse());
            secretaire.setRoles(List.of("SECRETAIRE"));
            return secretaire;
        }
    }

}
