package projet.group2.gestionEmargement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.service.EnseignantService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/emargement")
public class EnseignantController {

    private final EnseignantService enseignantService;
    private final PasswordEncoder passwordEncoder;

    public EnseignantController(EnseignantService enseignantService, PasswordEncoder passwordEncoder) {
        this.enseignantService = enseignantService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/enseignants")
    public ResponseEntity<Enseignant> inscription(@RequestBody Enseignant enseignant) {
        Enseignant ens = this.enseignantService.getEnseignantByEmail(enseignant.getEmail());
        if(Objects.isNull(ens)){
            enseignant.setMotDePasse(this.passwordEncoder.encode(enseignant.getMotDePasse()));
            ens = this.enseignantService.inscription(enseignant);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(ens.getEmail()).toUri();
            return ResponseEntity.created(location).body(ens);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/enseignants/{id}")
    public ResponseEntity<Enseignant> getEnseignantByEmail(@PathVariable String id){
        Enseignant enseignant =  this.enseignantService.getEnseignantByEmail(id);
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
