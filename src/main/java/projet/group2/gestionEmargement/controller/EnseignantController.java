package projet.group2.gestionEmargement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.service.EnseignantService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/emergement")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @PostMapping("/enseignant")
    public ResponseEntity<Enseignant> inscription(@RequestBody Enseignant enseignant) {
        Enseignant ens = this.enseignantService.getEnseignantByEmail(enseignant.getEmail());
        if(Objects.isNull(ens)){
            ens = this.enseignantService.inscription(enseignant);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(ens.getId()).toUri();
            return ResponseEntity.created(location).body(ens);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/enseignant/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable String id){
        Enseignant enseignant =  this.enseignantService.getEnseignantById(id);
        if(Objects.nonNull(enseignant)){
            return ResponseEntity.ok(enseignant);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/enseignants")
    public ResponseEntity<List<Enseignant>> getEnseignants(){
        List<Enseignant> enseignants =  this.enseignantService.getEnseignants();
        if(enseignants.size() >0 ){
            return ResponseEntity.ok(enseignants);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
