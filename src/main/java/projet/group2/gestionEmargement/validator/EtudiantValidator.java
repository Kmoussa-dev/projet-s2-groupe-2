package projet.group2.gestionEmargement.validator;

import org.springframework.util.StringUtils;
import projet.group2.gestionEmargement.dto.EtudiantDTO;


import java.util.ArrayList;
import java.util.List;

public class EtudiantValidator {
    public static List<String> validate(EtudiantDTO etudiantDTO) {
        List<String> errors = new ArrayList<>();

        if (etudiantDTO == null) {
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le prenom");
            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le mot de passe");
            errors.add("Veuillez renseigner le numero Etudiant");
            errors.add("Veuillez renseigner le groupe");
            errors.add("Veuillez renseigner la promotion");
            return errors;
        }
        if (!StringUtils.hasLength(etudiantDTO.getNom())) {
            errors.add("Veuillez renseigner le nom");
        }
        if (!StringUtils.hasLength(etudiantDTO.getPrenom())) {
            errors.add("Veuillez renseigner le prenom");
        }
        if (!StringUtils.hasLength(etudiantDTO.getEmail())) {
            errors.add("Veuillez renseigner l'email");
        }
        if (!StringUtils.hasLength(etudiantDTO.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe");
        }
        if (!StringUtils.hasLength(etudiantDTO.getNumeroEtudiant())) {
            errors.add("Veuillez renseigner le numero étudiant");
        }
        return errors;
    }
}
