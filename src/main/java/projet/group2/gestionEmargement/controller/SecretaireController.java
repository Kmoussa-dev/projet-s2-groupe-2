package projet.group2.gestionEmargement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.service.SeanceService;
import projet.group2.gestionEmargement.service.SecretaireService;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/emergement")
public class SecretaireController {

    private final SecretaireService secretaireService;

    public SecretaireController(SeanceService seanceService, SecretaireService secretaireService) {
        this.secretaireService = secretaireService;
    }

    @PostMapping("/secretaire")
    public ResponseEntity<Secretaire> inscription(@RequestBody Secretaire secretaire) {
        Secretaire se = this.secretaireService.getSecretaireByEmail(secretaire.getEmail());
        if(Objects.isNull(se)){
            se = this.secretaireService.inscription(secretaire);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(se.getId()).toUri();
            return ResponseEntity.created(location).body(se);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/secretaire/{id}")
    public ResponseEntity<Secretaire> getEtudiantbyNumeroEtudiant(@PathVariable String id){
        Secretaire secretaire =  this.secretaireService.getSecretaireById(id);
        if(Objects.nonNull(secretaire)){
            return ResponseEntity.ok(secretaire);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
