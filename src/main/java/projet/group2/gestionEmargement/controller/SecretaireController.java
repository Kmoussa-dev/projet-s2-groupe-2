package projet.group2.gestionEmargement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.service.SecretaireService;

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
    public ResponseEntity<Secretaire> inscription(@RequestBody Secretaire secretaire) {
        Secretaire se = this.secretaireService.getSecretaireByEmail(secretaire.getEmail());
        if(Objects.isNull(se)){
            secretaire.setMotDePasse(this.passwordEncoder.encode(secretaire.getMotDePasse()));
            se = this.secretaireService.inscription(secretaire);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(se.getEmail()).toUri();
            return ResponseEntity.created(location).body(se);
        }
        else {
            return ResponseEntity.badRequest().build();
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
