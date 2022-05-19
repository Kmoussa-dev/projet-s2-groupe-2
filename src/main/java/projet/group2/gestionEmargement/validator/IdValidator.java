package projet.group2.gestionEmargement.validator;

import org.springframework.util.StringUtils;
import projet.group2.gestionEmargement.dto.EtudiantDTO;

import java.util.ArrayList;
import java.util.List;

public class IdValidator {

    public static List<String> validate(String id) {
        List<String> errors = new ArrayList<>();
        if(!StringUtils.hasLength(id)){
            errors.add("l'Id n'est pas valable");
        }
        else if (id.isBlank()){
            errors.add("L'Id contient que des espaces");
        }
        return errors;
    }
}
