package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.SecretaireDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
import projet.group2.gestionEmargement.exception.enseignantException.ErrorCodes;
import projet.group2.gestionEmargement.exception.enseignantException.SecretaireException;
import projet.group2.gestionEmargement.exception.enseignantException.UtilisateurException;
import projet.group2.gestionEmargement.repository.SecretaireRepository;
import projet.group2.gestionEmargement.validator.IdValidator;
import projet.group2.gestionEmargement.validator.UtilisateurValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecretaireService {

    @Autowired
    private SecretaireRepository secretaireRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecretaireDTO inscription(UtilisateurDTO utilisateurDTO) throws SecretaireException, UtilisateurException {
        List<String> errors = UtilisateurValidator.validate(utilisateurDTO);
        if (!errors.isEmpty()) {
            throw new SecretaireException("Le sécrétaire n'est pas valide", ErrorCodes.SECRETAIRE_NOT_VALID, errors);
        }
        Secretaire secretaire=this.secretaireRepository.getSecretaireByEmail(utilisateurDTO.getEmail());
        if (!Objects.isNull(secretaire)) {
            errors.add("La sécrétaire existe déjà dans la base");
            throw new UtilisateurException("Le sécrétaire existe déjà dans base", ErrorCodes.UTILISATEUR_ALREADY_IN_USE, errors);
        }
        utilisateurDTO.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        return SecretaireDTO.fromEntity(
                secretaireRepository.insert(
                        SecretaireDTO.toEntity(utilisateurDTO)
                )
        );
    }

    public List<SecretaireDTO> getSecretaires() throws SecretaireException {
        List<Secretaire> secretaires = this.secretaireRepository.findAll();
        List<String> errors = new ArrayList<>();
        if (secretaires.size() == 0) {
            errors.add("Il n'y a pas de secretaires");
            throw new SecretaireException("Il n'y a pas de Secretaire", ErrorCodes.SECRETAIRE_NOT_FOUND, errors);
        }
        return secretaires.stream().map(e -> SecretaireDTO.fromEntity(e)).collect(Collectors.toList());
    }

    public SecretaireDTO getSecretaireByEmail(String id) throws SecretaireException {
        List<String> errors= IdValidator.validate(id);
        if (!errors.isEmpty()) {
            throw new SecretaireException("L'ID de l'enseignant n'est pas valide",ErrorCodes.ID_SECRETAIRE_NOT_VALID,errors);
        }
        Secretaire secretaire=this.secretaireRepository.getSecretaireByEmail(id);
        if (Objects.isNull(secretaire)){
            throw new SecretaireException("Sécretaire inexistante",ErrorCodes.SECRETAIRE_NOT_FOUND,errors);
        }
        return SecretaireDTO.fromEntity(this.secretaireRepository.getSecretaireByEmail(id));
    }
}





