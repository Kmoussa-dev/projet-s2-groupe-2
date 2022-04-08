package projet.group2.gestionEmargement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.service.EtudiantService;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/emergement")
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final PasswordEncoder passwordEncoder;

    public EtudiantController(EtudiantService etudiantService, PasswordEncoder passwordEncoder) {
        this.etudiantService = etudiantService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/etudiant")
    public ResponseEntity<Etudiant> inscription(@RequestBody Etudiant etu) {
        Etudiant etudiant = this.etudiantService.getEtudiantbyNumeroEtudiant(etu.getNumeroEtudiant());
        if(Objects.isNull(etudiant)){
            etu.setMotDePasse(this.passwordEncoder.encode(etu.getMotDePasse()));
            etudiant = this.etudiantService.inscription(etu);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{numeroEtudiant}")
                    .buildAndExpand(etudiant.getEmail()).toUri();
            return ResponseEntity.created(location).body(etudiant);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/etudiant/{numeroEtudiant}")
    public ResponseEntity<Etudiant> getEtudiantbyNumeroEtudiant(@PathVariable String numeroEtudiant){
        Etudiant etudiant =  this.etudiantService.getEtudiantbyNumeroEtudiant(numeroEtudiant);
        if(Objects.nonNull(etudiant)){
            return ResponseEntity.ok(etudiant);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiant/{email}")
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
