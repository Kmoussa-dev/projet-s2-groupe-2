package projet.group2.gestionEmargement.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.config.TokenGenerator;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.exception.AppelleNonPrisEnCompteException;
import projet.group2.gestionEmargement.exception.EtudiantInexistantException;
import projet.group2.gestionEmargement.exception.MauvaisIdentifiantException;
import projet.group2.gestionEmargement.exception.SeanceInexistanteException;
import projet.group2.gestionEmargement.service.EtudiantService;
import projet.group2.gestionEmargement.service.SeanceService;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emargement")
public class SeanceController {

    private final SeanceService seanceService;

    private final EtudiantService etudiantService;

    public SeanceController(SeanceService seanceService, EtudiantService etudiantService) {
        this.seanceService = seanceService;
        this.etudiantService = etudiantService;
    }

    /**
     * Permet la création d'une nouvelle séance dans la bdd
     * @param seance
     * @return
     */
    @PostMapping("/seances")
    public ResponseEntity<Seance> nouvelleSeance(@RequestBody SeanceDTO seance){
        if(Objects.isNull(this.seanceService.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(
                seance.getHeureDebut(),seance.getHeureFin(),seance.getDiscipline(), seance.getGroupe()))){
            Seance s = this.seanceService.creerSeance(SeanceDTO.toEntity(seance));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(s.getId()).toUri();
             return ResponseEntity.created(location).body(s);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Récupère une séance par son identifiant
     * @param id de la séance
     * @return la séance
     */
    @GetMapping("/seances/{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        if(Objects.nonNull(seance)){
            return ResponseEntity.ok(seance);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Permet à l'étudiant d'émarger pour une séance donnée
     * @param id de la séance
     * @param numEtudant numéro de l'étudiant
     * @param dateExpire date d'expiration pour émarger
     * @return
     */
    @PutMapping("/seances/{id}/pointage/{numEtudant}/{dateExpire}")
    public ResponseEntity<Seance> emarger(@PathVariable String id, @PathVariable String numEtudant,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateExpire)
    {
        if(!Duration.between(LocalDateTime.now(),dateExpire).isNegative()){
            try {
                Seance seance = this.seanceService.emarger(id,numEtudant);
                return ResponseEntity.ok(seance);
            } catch (EtudiantInexistantException e) {
                return ResponseEntity.notFound().build();
            } catch (AppelleNonPrisEnCompteException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }


    /**
     * Récupère la liste des étudiants présents pour une séance donnée
     * On récupère les numéros d'étudiants qui ont émargés
     * @param id de la séance
     * @return
     */
    @GetMapping("/seances/{id}/etudiants-presents")
    public ResponseEntity<List<Etudiant>> getEtudiantsPresents(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        List<String> lesNumEtudiantPresent = new ArrayList<>();
        if (Objects.nonNull(seance)){
            seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
            if(lesNumEtudiantPresent.size() > 0){
                List<Etudiant> lesEtuPresent =  this.etudiantService.getEtudiants().stream()
                        .filter(e -> lesNumEtudiantPresent.contains(e.getNumeroEtudiant()))
                        .collect(Collectors.toList());
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

    /**
     * Récupère la liste des étudiants absents pour une séance donnée
     * On récupère les numéros d'étudiants
     * @param id de la séance
     * @return
     */
    @GetMapping("/seances/{id}/etudiants-absents")
    public ResponseEntity<List<Etudiant>> getEtudiantsAbsents(@PathVariable String id){
        Seance seance = this.seanceService.getSeanceById(id);
        List<String> lesNumEtudiantPresent = new ArrayList<>();
        if (Objects.nonNull(seance) && Objects.nonNull(seance.getNumEtudiantsPresent())){
            seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
                List<String> numEtudiantsAbsents = seance.getNumEtudiants().stream()
                        .filter(e -> !lesNumEtudiantPresent.contains(e))
                        .collect(Collectors.toList());
                List<Etudiant> lesEtuAbsent =  this.etudiantService.getEtudiants().stream()
                        .filter(e -> numEtudiantsAbsents.contains(e.getNumeroEtudiant()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(lesEtuAbsent);

        }
        else {
            List<Etudiant> lesEtuAbsent = this.etudiantService.getEtudiants().stream()
                    .filter(e -> seance.getNumEtudiants().contains(e.getNumeroEtudiant()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(lesEtuAbsent);
        }
    }

    /**
     * Permet la génération de token pour la fonctionnalité émarger
     * @param idSeance id de la séance
     * @param numEtudiant numéro étudiant de l'élève qui va émarger
     * @return
     */
    @GetMapping(value = "/seances/token", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> genarateToken(@RequestParam String idSeance, @RequestParam String numEtudiant){
        try {
            BufferedImage bufferedImage = TokenGenerator.generateQRCode(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/emergement/seance/{id}/pointage/{numEtudant}/{dateExpire}")
                    .buildAndExpand(idSeance,numEtudiant,LocalDateTime.now().plusSeconds(5).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .toUriString());
            return ResponseEntity.ok(bufferedImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Récupère toutes les séances enregistrées dans la bdd
     * @return la liste des séances
     */
    @GetMapping("/seances")
    public ResponseEntity<List<Seance>> getSeances() {
        List<Seance> seances = this.seanceService.getSeances();

        if (seances.size() > 0) {
            return ResponseEntity.ok(seances);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Permet de récupérer la liste des séances pour une période donnée comprise entre un intervalle de deux dates
     * @param dateDebut de l'intervalle
     * @param dateFin de l'intervalle
     * @return la liste des séances de la période sélectionnée
     */
    @GetMapping("/seances/periode")
        public ResponseEntity<List<Seance>> getSeancesByPeriode(@RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
                                                                @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFin) {
        List<Seance> listeSeances = this.seanceService.getSeancesByPeriode(dateDebut,dateFin);

        if (listeSeances.size() > 0) {
             return ResponseEntity.ok(listeSeances);
        } else{
        return ResponseEntity.notFound().build();
         }
    }

    /**
     * Permet la suppression d'une séance
     * @param id de la séance à supprimer
     * @throws SeanceInexistanteException : si l'id est inexistant dans la bdd
     * @throws MauvaisIdentifiantException : si le format de l'id est incorrect ou null ou vide
     */
    @DeleteMapping("/seances/{id}")
    public ResponseEntity<Seance> deleteSeance(@PathVariable String id) {

        try {
            this.seanceService.deleteSeance(id);
            return ResponseEntity.status(202).build();
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(404).build();
        } catch (MauvaisIdentifiantException e) {
            return ResponseEntity.status(406).build();
        }
    }


    /**
     * Modifier les informations d'une séance par une secrétaire
     * @param id de la séance à éditer
     * @param principal
     * @return
     */
    @PutMapping("/seances/{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable String id, Principal principal) {

        try {
            this.seanceService.updateSeance(id);
            return ResponseEntity.status(202).build(); // code à revérifier
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(404).build();
        } catch (MauvaisIdentifiantException e) {
            return ResponseEntity.status(406).build();
        }
    }
}
