package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.exception.EtudiantInexistantException;
import projet.group2.gestionEmargement.exception.PermissionDejaAccordeeException;
import projet.group2.gestionEmargement.exception.generalException.ErrorCodes;
import projet.group2.gestionEmargement.exception.generalException.EtudiantException;
import projet.group2.gestionEmargement.exception.generalException.UtilisateurException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.validator.EtudiantValidator;
import projet.group2.gestionEmargement.validator.IdValidator;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EtudiantDTO inscription(EtudiantDTO etudiantDTO) throws UtilisateurException, EtudiantException {
        List<String> errors = EtudiantValidator.validate(etudiantDTO);
        if (!errors.isEmpty()) {
            throw new EtudiantException("L'etudiant n'est pas valide", ErrorCodes.ETUDIANT_NOT_VALID, errors);
        }
        Etudiant etudiant=this.etudiantRepository.getEtudiantByNumeroEtudiant(etudiantDTO.getNumeroEtudiant());
        if (!Objects.isNull(etudiant)) {
            errors.add("L'etudiant existe déjà dans base");
            throw new UtilisateurException("L'etudiant existe déjà dans base",ErrorCodes.UTILISATEUR_ALREADY_IN_USE,errors);
        }
        etudiantDTO.setMotDePasse(passwordEncoder.encode(etudiantDTO.getMotDePasse()));
        return EtudiantDTO.fromEntity(
                etudiantRepository.insert(
                        EtudiantDTO.toEntity(etudiantDTO)
                )
        );
        }

    public List<EtudiantDTO> getEtudiants() throws EtudiantException {
        List<Etudiant> etudiants=this.etudiantRepository.findAll();
        List<String> errors=new ArrayList<>();
        if (etudiants.size()==0){
            errors.add("Il n'y a pas d'etudiants");
            throw new EtudiantException("Il n'y a pas d'etudiants", ErrorCodes.ETUDIANT_NOT_FOUND, errors);
        }
        return etudiants.stream().map(e->EtudiantDTO.fromEntity(e)).collect(Collectors.toList());
    }

    public EtudiantDTO getEtudiantbyId(String id) throws EtudiantException {
        if (this.etudiantRepository.existsEtudiantByNumeroEtudiant(id)){
            return this.getEtudiantbyNumeroEtudiant(id);
        }
        else if (this.etudiantRepository.existsEtudiantByEmail(id)){
            return this.getEtudiantbyEmail(id);
        }
        else {
            throw new EtudiantException("Etudiant inexistant",ErrorCodes.ETUDIANT_NOT_FOUND,List.of("Etudiant inexistant"));
        }
    }

    public EtudiantDTO getEtudiantbyNumeroEtudiant(String numeroEtudiant) throws EtudiantException {
        List<String> errors= IdValidator.validate(numeroEtudiant);
        if (!errors.isEmpty()) {
            throw new EtudiantException("Le numero etudiant n'est pas valide",ErrorCodes.ID_ETUDIANT_NOT_VALID,errors);
        }
        Etudiant etudiant=this.etudiantRepository.getEtudiantByNumeroEtudiant(numeroEtudiant);
        if (etudiant==null){
            throw new EtudiantException("Etudiant inexistant",ErrorCodes.ETUDIANT_NOT_FOUND,errors);
        }
        return EtudiantDTO.fromEntity(etudiant);

    }
    public EtudiantDTO getEtudiantbyEmail(String email) throws EtudiantException {
        List<String> errors= IdValidator.validate(email);
        if (!errors.isEmpty()) {
            throw new EtudiantException("L'email de l'etudiant n'est pas valide",ErrorCodes.ID_ETUDIANT_NOT_VALID,errors);
        }
        Etudiant etudiant=this.etudiantRepository.getEtudiantByEmail(email);
        if (etudiant==null){
            throw new EtudiantException("Etudiant inexistant",ErrorCodes.ETUDIANT_NOT_FOUND,errors);
        }
        return EtudiantDTO.fromEntity(etudiant);
    }

    public boolean permissionTOGenerateQrCode(String adresseMAC, String numEtudiant) throws EtudiantInexistantException, EtudiantException {
        if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant)){
            Etudiant etu = this.etudiantRepository.getEtudiantByNumeroEtudiant(numEtudiant);
            if(etu.getAdresseMAC().equals(adresseMAC)){
                return true;
            }
            else if (etu.isOtp()){
                etu.setAdresseMAC(adresseMAC);
                etu.setOtp(false);
                this.etudiantRepository.save(etu);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new EtudiantInexistantException();
        }

    }

    public void givePermissonNewDevice(String numEtudiant) throws EtudiantInexistantException, PermissionDejaAccordeeException, EtudiantException {
        if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant)) {
            Etudiant etu = this.etudiantRepository.getEtudiantByNumeroEtudiant(numEtudiant);
            if (!etu.isOtp()){
                etu.setOtp(true);
                this.etudiantRepository.save(etu);
            }
            else {
                throw new PermissionDejaAccordeeException();
            }
        }
        else {
            throw new EtudiantInexistantException();
        }
    }

}
