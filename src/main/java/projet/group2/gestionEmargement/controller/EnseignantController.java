package projet.group2.gestionEmargement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
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
    public ResponseEntity<EnseignantDTO> inscription(@RequestBody UtilisateurDTO utilisateurDTO) {
        try{
            EnseignantDTO ens=this.enseignantService.inscription(utilisateurDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(ens.getEmail()).toUri();
            return ResponseEntity.created(location).body(ens);
        }catch (EnseignantException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/enseignants/{id}")
    public ResponseEntity<EnseignantDTO> getEnseignantByEmail(@PathVariable String id){
        try{
        EnseignantDTO enseignant =  this.enseignantService.getEnseignantByEmail(id);
        return ResponseEntity.ok(enseignant);
        }catch(EnseignantException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/enseignants")
    public ResponseEntity<List<EnseignantDTO>> getEnseignants(){
       try{
           List<EnseignantDTO> enseignants =  this.enseignantService.getEnseignants();
           return ResponseEntity.ok(enseignants);
       }catch (EnseignantException e){
           return ResponseEntity.notFound().build();
       }

    }
}
