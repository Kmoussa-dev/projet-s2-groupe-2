package projet.group2.gestionEmargement.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.SecretaireDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
import projet.group2.gestionEmargement.exception.enseignantException.SecretaireException;
import projet.group2.gestionEmargement.service.SecretaireService;
import projet.group2.gestionEmargement.validator.UtilisateurValidator;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/emargement")
public class SecretaireController {

    private final SecretaireService secretaireService;
    private final PasswordEncoder passwordEncoder;

    public SecretaireController(SecretaireService secretaireService, PasswordEncoder passwordEncoder) {
        this.secretaireService = secretaireService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/secretaires")
    public ResponseEntity<SecretaireDTO> inscription(@RequestBody UtilisateurDTO utilisateurDTO) {
        try{
            SecretaireDTO secre=this.secretaireService.inscription(utilisateurDTO);
            URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(secre.getEmail()).toUri();
            return ResponseEntity.created(location).body(secre);
        }catch (SecretaireException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("/secretaires/{email}")
    public ResponseEntity<Secretaire> getSecretaireById(@PathVariable String email){
        Secretaire secretaire =  this.secretaireService.getSecretaireByEmail(email);
        if(Objects.nonNull(secretaire)){
            return ResponseEntity.ok(secretaire);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/secretaires")
    public ResponseEntity<List<Secretaire>> getSecretaires(){
        List<Secretaire> secretaires =  this.secretaireService.getSecretaires();
        if(secretaires.size() >0 ){
            return ResponseEntity.ok(secretaires);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
