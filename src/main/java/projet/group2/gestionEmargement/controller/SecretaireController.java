package projet.group2.gestionEmargement.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import projet.group2.gestionEmargement.dto.SecretaireDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;

import projet.group2.gestionEmargement.exception.enseignantException.SecretaireException;
import projet.group2.gestionEmargement.exception.enseignantException.UtilisateurException;
import projet.group2.gestionEmargement.service.SecretaireService;


import java.net.URI;
import java.util.List;


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
        } catch (UtilisateurException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/secretaires/{email}")
    public ResponseEntity<SecretaireDTO> getSecretaireById(@PathVariable String email){
        try{
            SecretaireDTO secretaire =  this.secretaireService.getSecretaireByEmail(email);
            return ResponseEntity.ok(secretaire);
        }catch(SecretaireException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/secretaires")
    public ResponseEntity<List<SecretaireDTO>> getSecretaires(){
        try{
            List<SecretaireDTO> secretaires =  this.secretaireService.getSecretaires();
            return ResponseEntity.ok(secretaires);
        }catch (SecretaireException e){
            return ResponseEntity.notFound().build();
        }

    }



}
