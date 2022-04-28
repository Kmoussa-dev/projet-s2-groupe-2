package projet.group2.gestionEmargement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.exception.AppelleNonPrisEnCompteException;
import projet.group2.gestionEmargement.exception.EtudiantInexistantException;
import projet.group2.gestionEmargement.service.EtudiantService;
import projet.group2.gestionEmargement.service.SeanceService;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emergement")
public class SeanceController {

    private final SeanceService seanceService;

    private final EtudiantService etudiantService;

    public SeanceController(SeanceService seanceService, EtudiantService etudiantService) {
        this.seanceService = seanceService;
        this.etudiantService = etudiantService;
    }


    @PostMapping("/seance")
    public ResponseEntity<Seance> nouvelleSeance(@RequestBody SeanceDTO seance){
        if(Objects.isNull(this.seanceService.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(seance.getHeureDebut(),seance.getHeureFin(),seance.getDiscipline(), seance.getGroupe()))){
            Seance s = this.seanceService.creer(SeanceDTO.toEntity(seance));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(s.getId()).toUri();
             return ResponseEntity.created(location).body(s);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/seance/{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        if(Objects.nonNull(seance)){
            return ResponseEntity.ok(seance);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @RolesAllowed("PROF")
    @PutMapping("/seance/{id}/pointage/{numEtudant}")
    public ResponseEntity<Seance> emerger(@PathVariable String id, @PathVariable String numEtudant)
    {
        try {
            Seance seance = this.seanceService.emerger(id,numEtudant);
            return ResponseEntity.ok(seance);
        } catch (EtudiantInexistantException e) {
            return ResponseEntity.notFound().build();
        } catch (AppelleNonPrisEnCompteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/seance/{id}/etudiant-present")
    public ResponseEntity<List<Etudiant>> getEtudiantsPresent(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        List<String> lesNumEtudiantPresent = new ArrayList<>();
        if (Objects.nonNull(seance)){
            seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
            if(lesNumEtudiantPresent.size() > 0){
                List<Etudiant> lesEtuPresent =  this.etudiantService.getEtudiants().stream().filter(e -> lesNumEtudiantPresent.contains(e.getNumeroEtudiant())).collect(Collectors.toList());
                return ResponseEntity.ok(lesEtuPresent);
            }
            else {
                return ResponseEntity.noContent().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/seance/{id}/etudiant-absent")
    public ResponseEntity<List<Etudiant>> getEtudiantsAbsent(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        List<String> lesNumEtudiantPresent = new ArrayList<>();
        if (Objects.nonNull(seance) && Objects.nonNull(seance.getNumEtudiantsPresent())){
            seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
                List<String> numEtudiantsAbsents = seance.getNumEtudiants().stream().filter(e -> !lesNumEtudiantPresent.contains(e)).collect(Collectors.toList());
                List<Etudiant> lesEtuAbsent =  this.etudiantService.getEtudiants().stream().filter(e -> numEtudiantsAbsents.contains(e.getNumeroEtudiant())).collect(Collectors.toList());
                return ResponseEntity.ok(lesEtuAbsent);

        }
        else {
            List<Etudiant> lesEtuAbsent = this.etudiantService.getEtudiants().stream().filter(e -> seance.getNumEtudiants().contains(e.getNumeroEtudiant())).collect(Collectors.toList());
            return ResponseEntity.ok(lesEtuAbsent);
        }
    }


}
