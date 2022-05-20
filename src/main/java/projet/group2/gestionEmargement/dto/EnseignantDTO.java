package projet.group2.gestionEmargement.dto;

import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;
import java.util.Objects;

public class EnseignantDTO extends UtilisateurDTO {


    public EnseignantDTO(String email, String nom, String prenom, String motDePasse) {
        super(email, nom, prenom, motDePasse);
    }

    public static Enseignant toEntity(EnseignantDTO enseignantDTO){
        if(Objects.isNull(enseignantDTO))
            return null;
        else {
            Enseignant enseignant = new Enseignant();
            enseignant.setEmail(enseignant.getEmail());
            enseignant.setNom(enseignant.getNom());
            enseignant.setPrenom(enseignant.getPrenom());
            enseignant.setMotDePasse(enseignant.getMotDePasse());
            enseignant.setRoles(List.of("PROFESSEUR"));
            return enseignant;
        }
    }
}
