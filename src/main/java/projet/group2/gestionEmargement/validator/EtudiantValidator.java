package projet.group2.gestionEmargement.validator;

import org.springframework.util.StringUtils;
import projet.group2.gestionEmargement.dto.EtudiantDTO;


import java.util.ArrayList;
import java.util.List;

public class EtudiantValidator {
    public static List<String> validate(EtudiantDTO etudiantDTO) {
        List<String> errors = new ArrayList<>();

        if (etudiantDTO == null) {
            errors.add("Veuillez renseigner le numero Etudiant");
            errors.add("Veuillez renseigner le groupe");
            errors.add("Veuillez renseigner la promotion");
            return errors;
        }
        if (!StringUtils.hasLength(etudiantDTO.getNumeroEtudiant())) {
            errors.add("Veuillez renseigner le numero étudiant");
        }
        if (!StringUtils.hasLength(etudiantDTO.getGroupe().getGroupeDeTD())) {
            errors.add("Veuillez renseigner le groupe de td");
        }
        if (!StringUtils.hasLength(etudiantDTO.getGroupe().getGroupeDeTP())) {
            errors.add("Veuillez renseigner le groupe de tp");
        }
        if (!StringUtils.hasLength(etudiantDTO.getPromo().getNiveau())) {
            errors.add("Veuillez renseigner le niveau");
        }
        if (!StringUtils.hasLength(etudiantDTO.getPromo().getAnnee())) {
            errors.add("Veuillez renseigner l'année");
        }
        return errors;
    }
}
