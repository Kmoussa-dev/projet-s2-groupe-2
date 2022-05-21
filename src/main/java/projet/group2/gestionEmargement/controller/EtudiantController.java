package projet.group2.gestionEmargement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.exception.EtudiantDejaExisteException;
import projet.group2.gestionEmargement.exception.MotDePasseObligatoireException;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
import projet.group2.gestionEmargement.exception.enseignantException.EtudiantException;
import projet.group2.gestionEmargement.exception.enseignantException.UtilisateurException;
import projet.group2.gestionEmargement.service.EtudiantService;
import projet.group2.gestionEmargement.validator.EtudiantValidator;
import projet.group2.gestionEmargement.validator.UtilisateurValidator;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/emargement")
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final PasswordEncoder passwordEncoder;

    public EtudiantController(EtudiantService etudiantService, PasswordEncoder passwordEncoder) {
        this.etudiantService = etudiantService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/etudiants")
    public ResponseEntity<EtudiantDTO> inscription(@RequestBody EtudiantDTO etu) {
            try {
                    EtudiantDTO etudiant = this.etudiantService.inscription(etu);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{numeroEtudiant}")
                            .buildAndExpand(etudiant.getNumeroEtudiant()).toUri();
                    return ResponseEntity.created(location).body(etudiant);
            }catch (EtudiantException e) {
                return ResponseEntity.badRequest().build();
            }catch (UtilisateurException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
    }

    @GetMapping("/etudiants/{numeroEtudiant}")
    public ResponseEntity<EtudiantDTO> getEtudiantbyNumeroEtudiant(@PathVariable String numeroEtudiant){
        try{
            EtudiantDTO etudiant =  this.etudiantService.getEtudiantbyNumeroEtudiant(numeroEtudiant);
            return ResponseEntity.ok(etudiant);
        }catch(EtudiantException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiants/{email}")
    public ResponseEntity<EtudiantDTO> getEtudiantbyEmail(@PathVariable String email){
        try{
            EtudiantDTO etudiant =  this.etudiantService.getEtudiantbyEmail(email);
            return ResponseEntity.ok(etudiant);
        }catch(EtudiantException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<EtudiantDTO>> getEtudiants(){
        try{
            List<EtudiantDTO> etudiants =  this.etudiantService.getEtudiants();
            return ResponseEntity.ok(etudiants);
        }catch (EtudiantException e){
            return ResponseEntity.notFound().build();
        }

    }


}
