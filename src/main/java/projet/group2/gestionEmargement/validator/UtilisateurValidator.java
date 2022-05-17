package projet.group2.gestionEmargement.validator;

import org.springframework.util.StringUtils;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDTO utilisateurDTO) {
        List<String> errors = new ArrayList<>();

        if (utilisateurDTO == null) {
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le prenom");
            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le mot de passe");
            return errors;
        }
        if (!StringUtils.hasLength(utilisateurDTO.getNom())) {
            errors.add("Veuillez renseigner le nom");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getPrenom())) {
            errors.add("Veuillez renseigner le prenom");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getEmail())) {
            errors.add("Veuillez renseigner l'email");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe");
        }
        return errors;
    }

}
