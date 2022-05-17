package projet.group2.gestionEmargement.validator;

import org.springframework.util.StringUtils;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceValidator {
    public static List<String> validate(SeanceDTO seanceDTO) {
        List<String> errors = new ArrayList<>();

        if (seanceDTO == null) {
            errors.add("Veuillez renseigner l'ID de la séance'");
            errors.add("Veuillez renseigner la discipline");
            errors.add("Veuillez renseigner l'ID de l'enseignant");
            errors.add("Veuillez renseigner le type de séance");
            errors.add("Veuillez renseigner le groupe de la séance");
            errors.add("Veuillez renseigner l'heure de début de la séance");
            errors.add("Veuillez renseigner l'heure de fin de la séance");
            errors.add("Veuillez renseigner la promotion de la séance");
            errors.add("Veuillez renseigner le statut de la séance");
            return errors;
        }
        if (!StringUtils.hasLength(seanceDTO.getUserID())) {
            errors.add("Veuillez renseigner l'ID de l'utilisateur");
        }
        if (!StringUtils.hasLength(seanceDTO.getDiscipline())) {
            errors.add("Veuillez renseigner la discipline ");
        }
        if (!StringUtils.hasLength(seanceDTO.getTypeSeance())){
            errors.add("Veuillez renseigner le type de séance");
        }
        if (!StringUtils.hasLength(seanceDTO.getTypeSeance())){
            errors.add("Veuillez renseigner le type de séance");
        }
        if (!StringUtils.hasLength(seanceDTO.getTypeSeance())){
            errors.add("Veuillez renseigner le type de séance");
        }
        if (!StringUtils.hasLength(seanceDTO.getGroupe().getGroupeDeTD())){
            errors.add("Veuillez renseigner le groupe de TD");
        }
        if (!StringUtils.hasLength(seanceDTO.getGroupe().getGroupeDeTP())){
            errors.add("Veuillez renseigner le groupe de TP");
        }
        if (!StringUtils.hasLength(seanceDTO.getStatutSeance())){
            errors.add("Veuillez renseigner le statut de la seance");
        }
        if (seanceDTO.getHeureDebut()==null){
            errors.add("Veuillez renseigner l'heure de début");
        }
        if(seanceDTO.getHeureFin()==null){
            errors.add("Veuillez renseigner l'heure de la fin");
        }
        if(!StringUtils.hasLength(seanceDTO.getPromotion().getAnnee())){
            errors.add("Veuillez renseigner l'annnée");
        }
        if (!StringUtils.hasLength(seanceDTO.getPromotion().getNiveau())){
            errors.add("Veuillez renseigner le niveau de la promotion");
        }
        return errors;
    }





    private Promotion promotion;


    private String statutSeance;


}
