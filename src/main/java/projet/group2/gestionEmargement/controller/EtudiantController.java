package projet.group2.gestionEmargement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.exception.EtudiantDejaExisteException;
import projet.group2.gestionEmargement.exception.MotDePasseObligatoireException;
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
    public ResponseEntity<Etudiant> inscription(@RequestBody EtudiantDTO etu) {
            Etudiant etudiant = this.etudiantService.getEtudiantbyNumeroEtudiant(etu.getNumeroEtudiant());
            try {

                    etu.setMotDePasse(this.passwordEncoder.encode(etu.getMotDePasse()));
                    etudiant = this.etudiantService.inscription(EtudiantDTO.toEntity(etu));
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{numeroEtudiant}")
                            .buildAndExpand(etudiant.getEmail()).toUri();
                    return ResponseEntity.created(location).body(etudiant);

            } catch (EtudiantDejaExisteException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } catch (MotDePasseObligatoireException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }

    }

    @GetMapping("/etudiants/{numeroEtudiant}")
    public ResponseEntity<Etudiant> getEtudiantbyNumeroEtudiant(@PathVariable String numeroEtudiant){
        Etudiant etudiant =  this.etudiantService.getEtudiantbyNumeroEtudiant(numeroEtudiant);
        if(Objects.nonNull(etudiant)){
            return ResponseEntity.ok(etudiant);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiants/{email}")
    public ResponseEntity<Etudiant> getEtudiantbyEmail(@PathVariable String email){
        Etudiant etudiant =  this.etudiantService.getEtudiantbyEmail(email);
        if(Objects.nonNull(etudiant)){
            return ResponseEntity.ok(etudiant);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<Etudiant>> getEtudiants(){
        List<Etudiant> etudiant =  this.etudiantService.getEtudiants();
        if(etudiant.size() > 0){
            return ResponseEntity.ok(etudiant);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
