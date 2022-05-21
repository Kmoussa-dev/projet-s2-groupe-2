package projet.group2.gestionEmargement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projet.group2.gestionEmargement.config.TokenGenerator;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.exception.*;
import projet.group2.gestionEmargement.exception.enseignantException.EtudiantException;
import projet.group2.gestionEmargement.service.EtudiantService;
import projet.group2.gestionEmargement.service.SeanceService;
import projet.group2.gestionEmargement.validator.SeanceValidator;
import projet.group2.gestionEmargement.validator.UtilisateurValidator;

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

import static java.rmi.server.LogStream.log;

@Slf4j
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
     * @param seance : seance DTO
     * @return la séance créée
     */
    @PostMapping("/seances")
    public ResponseEntity<Seance> nouvelleSeance(@RequestBody SeanceDTO seance){
//        List<String> errors= SeanceValidator.validate(seance);
//        if(!errors.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        else {
            try {
                Seance s = this.seanceService.creerSeance(SeanceDTO.toEntity(seance));
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(s.getId()).toUri();
                return ResponseEntity.created(location).body(s);
            } catch (SeanceDejaExistanteException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
//        }

    }

    /**
     * Récupère une séance par son identifiant
     * @param id de la séance
     * @return la séance
     */
    @GetMapping("/seances/{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable String id){
        try {
           Seance seance = this.seanceService.getSeanceById(id);
            return ResponseEntity.ok(seance);
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Récupère la liste des étudiants présents pour une séance donnée
     * On récupère les numéros d'étudiants qui ont émargés
     * @param id de la séance
     * @return
     */
    @GetMapping("/seances/{id}/etudiants-presents")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsPresents(@PathVariable String id){
        try {
            Seance seance = this.seanceService.getSeanceById(id);
            List<String> lesNumEtudiantPresent = new ArrayList<>();
            if (Objects.nonNull(seance)){
                seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
                if(lesNumEtudiantPresent.size() > 0){
                    List<EtudiantDTO> lesEtuPresent =  this.etudiantService.getEtudiants().stream()
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
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.notFound().build();
        } catch (EtudiantException e) {
            log.error("Il n'y a pas d'etudiants présents");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Récupère la liste des étudiants absents pour une séance donnée
     * On récupère les numéros d'étudiants
     * @param id de la séance
     * @return
     */
    @GetMapping("/seances/{id}/etudiants-absents")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsAbsents(@PathVariable String id){
        try {
            Seance seance = this.seanceService.getSeanceById(id);
            List<String> lesNumEtudiantPresent = new ArrayList<>();
            if (Objects.nonNull(seance) && Objects.nonNull(seance.getNumEtudiantsPresent())){
                seance.getNumEtudiantsPresent().forEach(h -> lesNumEtudiantPresent.add(h.getNumEtudiant()));
                List<String> numEtudiantsAbsents = seance.getNumEtudiants().stream()
                        .filter(e -> !lesNumEtudiantPresent.contains(e))
                        .collect(Collectors.toList());
                List<EtudiantDTO> lesEtuAbsent =  this.etudiantService.getEtudiants().stream()
                        .filter(e -> numEtudiantsAbsents.contains(e.getNumeroEtudiant()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(lesEtuAbsent);
            }
            else {
                List<EtudiantDTO> lesEtuAbsent = this.etudiantService.getEtudiants().stream()
                        .filter(e -> seance.getNumEtudiants().contains(e.getNumeroEtudiant()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(lesEtuAbsent);
            }
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EtudiantException e) {
            log.error("Il n'y a pas d'etudiants présents");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *  Fonctionnalité émarger - étape 1
     *  Génère le token pour permettre à l'étudiant d'émarger
     * @param idSeance id de la séance
     * @param numEtudiant le numéro étudiant de l'élève qui va émarger
     * @return une image png contenant le QR Code généré
     */
    @GetMapping (value = "/seances/token", produces = MediaType.IMAGE_PNG_VALUE)
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
     * Fonctionnalité émarger - étape 2
     * La validation de l'émargement d'un étudiant pour une séance donnée
     * Par l'enseignant
     * @param id de la séance
     * @param numEtudiant numéro de l'étudiant
     * @param dateExpire date d'expiration pour émarger
     * @return
     */
    @PutMapping("/seances/{id}/pointage/{numEtudiant}/{dateExpire}")
    public ResponseEntity<Seance> emarger(@AuthenticationPrincipal User user, @RequestParam(required = false) String idSalle, @PathVariable String id, @PathVariable String numEtudiant,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateExpire)
    {
        try {
            Seance seance = this.seanceService.emarger(id,numEtudiant,user.getUsername(),dateExpire, idSalle);
            return ResponseEntity.ok(seance);
        } catch (EtudiantInexistantException | SeanceInexistanteException e) {
            return ResponseEntity.notFound().build();
        } catch (AppelNonPrisEnCompteException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (MauvaisScannerException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (TokenInValidException e) {
            return ResponseEntity.status(498).build();
        }

    }


    /**
     * Récupère toutes les séances enregistrées dans la bdd
     * @return la liste des séances
     */
    @GetMapping("/seances")
    public ResponseEntity<List<Seance>> getSeances() {
        try {
            List<Seance> seances = this.seanceService.getSeances();
            return ResponseEntity.ok(seances);
        } catch (ListeSeanceVideException e) {
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

        try {
            List<Seance>  listeSeances = this.seanceService.getSeancesByPeriode(dateDebut,dateFin);
            return ResponseEntity.ok(listeSeances);
        } catch (ListeSeanceVideException e) {
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
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * Modifier les informations d'une séance par une secrétaire
     * @param id identifiant de la seance
     * @param seanceDTO : la séance à éditer
     * @return la séance éditée
     * @throws SeanceInexistanteException : si l'id est inexistant dans la bdd
     * @throws MauvaisIdentifiantException : si le format de l'id est incorrect ou null ou vide
     */
    @PutMapping("/seances/{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable String id, SeanceDTO seanceDTO) {
        try {
            this.seanceService.updateSeance(id,seanceDTO);
            return ResponseEntity.status(202).build();
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(404).build();
         } catch (MauvaisIdentifiantException e) {
            return ResponseEntity.status(400).build();
        }
    }

}
