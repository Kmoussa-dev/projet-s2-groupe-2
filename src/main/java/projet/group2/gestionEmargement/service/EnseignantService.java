package projet.group2.gestionEmargement.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
import projet.group2.gestionEmargement.exception.enseignantException.ErrorCodes;
import projet.group2.gestionEmargement.repository.EnseignantRepository;
import projet.group2.gestionEmargement.validator.IdValidator;
import projet.group2.gestionEmargement.validator.UtilisateurValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EnseignantDTO inscription(UtilisateurDTO utilisateurDTO) throws EnseignantException {
        List<String> errors = UtilisateurValidator.validate(utilisateurDTO);
        if (!errors.isEmpty()) {
            throw new EnseignantException("L'enseignant n'est pas valide", ErrorCodes.ENSEIGNANT_NOT_VALID, errors);
        }
        utilisateurDTO.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        return EnseignantDTO.fromEntity(
                enseignantRepository.insert(
                        EnseignantDTO.toEntity(utilisateurDTO)
                )
        );
    }

    public List<EnseignantDTO> getEnseignants() throws EnseignantException {
        List<Enseignant> enseignants=this.enseignantRepository.findAll();
        List<String> errors=new ArrayList<>();
        if (enseignants.size()==0){
            errors.add("Il n'y a pas d'enseignants");
            throw new EnseignantException("Il n'y a pas d'enseignants", ErrorCodes.ENSEIGNANT_NOT_FOUND, errors);
        }
        return enseignants.stream().map(e->EnseignantDTO.fromEntity(e)).collect(Collectors.toList());
    }

    public EnseignantDTO getEnseignantByEmail(String id) throws EnseignantException {
        List<String> errors= IdValidator.validate(id);
        if (!errors.isEmpty()) {
            throw new EnseignantException("L'ID de l'enseignant n'est pas valide",ErrorCodes.ID_ENSEIGNANT_NOT_VALID,errors);
        }
        Enseignant enseignant=this.enseignantRepository.getEnseignantByEmail(id);
        if (enseignant==null){
            throw new EnseignantException("Enseignant inexistant",ErrorCodes.ENSEIGNANT_NOT_FOUND,errors);
        }
        return EnseignantDTO.fromEntity(this.enseignantRepository.getEnseignantByEmail(id));
    }


}


