package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.*;
import projet.group2.gestionEmargement.exception.*;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.repository.SeanceRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    /**
     * Permet la création d'une nouvelle séance dans la bdd
     * @param seance
     * @return la séance créée
     */
    public Seance creerSeance(Seance seance) throws SeanceDejaExistanteException {

        if (Objects.isNull(this.seanceRepository.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(
                seance.getHeureDebut(),seance.getHeureFin(),seance.getDiscipline(), seance.getGroupe()))) {

            List<String> numEtudiants;
            switch (seance.getTypeSeance()){
                case "TP":
                    numEtudiants = this.etudiantRepository.getEtudiantsByPromoNiveauAndGroupeGroupeDeTP(seance.getPromotion().getNiveau(),seance.getGroupe().getGroupeDeTP())
                            .stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                    break;
                case "TD":
                    numEtudiants = this.etudiantRepository.getEtudiantsByPromoNiveauAndGroupeGroupeDeTD(seance.getPromotion().getNiveau(),seance.getGroupe().getGroupeDeTD())
                            .stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                    break;
                default:
                    numEtudiants = this.etudiantRepository.getEtudiantsByPromo(seance.getPromotion())
                            .stream().map(e -> e.getNumeroEtudiant()).collect(Collectors.toList());
                    break;
            }
            seance.setNumEtudiants(numEtudiants);
            return this.seanceRepository.insert(seance);
        }
        else {
            throw new SeanceDejaExistanteException();
        }

    }

    /**
     * Récupère toutes les séances enregistrées dans la bdd
     * @return la liste des séances
     */
    public List<Seance> getSeances() throws ListeSeanceVideException {
        if (this.seanceRepository.findAll().size() > 0) {
            return this.seanceRepository.findAll();
        }
        else {
            throw new ListeSeanceVideException();
        }
    }


    /**
     * Permet de récupérer la liste des séances pour une période donnée comprise entre un intervalle de deux dates
     * @param dateDebut de l'intervalle
     * @param dateFin de l'intervalle
     * @return la liste des séances de la période sélectionnée
     */
    public List<Seance> getSeancesByPeriode(LocalDateTime dateDebut, LocalDateTime dateFin) throws ListeSeanceVideException  {

        if (this.seanceRepository.findAllByHeureDebutBetween(dateDebut,dateFin).size() > 0 ) {

            return this.seanceRepository.findAllByHeureDebutBetween(dateDebut,dateFin);
        }
         else {
            throw new ListeSeanceVideException();
        }
    }


    /**
     * Récupère une séance par son identifiant
     * @param id de la séance
     * @return la séance
     */
    public Seance getSeanceById(String id) throws SeanceInexistanteException {

        if (seanceRepository.existsSeanceById(id)) {
            return this.seanceRepository.getSeanceById(id);
        }
        else {
            throw new SeanceInexistanteException();
        }

    }

    /**
     * Permet de modifier une séance
     * @param id id de la seance à éditer
     * @param seanceDTO
     * @throws SeanceInexistanteException : la séance n'existe pas
     * @throws MauvaisIdentifiantException : id null ou vide ou incorrect
     * @return la séance modifiée
     */
    public Seance updateSeance(String id, SeanceDTO seanceDTO) throws SeanceInexistanteException, MauvaisIdentifiantException {

        if (Objects.isNull(id) || id.isBlank()) {
            throw new MauvaisIdentifiantException();
        } else {
            Seance seance = getSeanceById(id);
            return this.seanceRepository.save(Objects.requireNonNull(SeanceDTO.toEntityUpdate(seance, seanceDTO)));
        }
    }


    /**
     * Permet de supprimer une séance
     * @param id de la séance à supprimé
     * @throws SeanceInexistanteException : la séance n'existe pas
     * @throws MauvaisIdentifiantException : id null ou vide ou incorrect
     */
    public void deleteSeance(String id) throws SeanceInexistanteException, MauvaisIdentifiantException {

        if (Objects.isNull(id) || id.isBlank()) {
            throw new MauvaisIdentifiantException();
        } else {
            Seance seance = this.getSeanceById(id);
            this.seanceRepository.delete(seance);
        }
    }


    /**
     * Retourne la séance d'une
     * @param heureDebut
     * @param heureFin
     * @param discipline
     * @param groupe
     * @return
     */
    public Seance getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(LocalDateTime heureDebut, LocalDateTime heureFin, String discipline, Groupe groupe){
        return this.seanceRepository.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe( heureDebut, heureFin, discipline, groupe);
    }

    /**
     * Permet la génération de token pour la fonctionnalité émarger
     * @param seanceid id de la séance
     * @param numEtudiant numéro étudiant de l'élève qui va émarger
     * @return
     */
    public Seance emarger(String seanceid, String numEtudiant, String loginProf, LocalDateTime dateExpire, String idSalle) throws EtudiantInexistantException, AppelNonPrisEnCompteException, SeanceInexistanteException, MauvaisScannerException, TokenInValidException {

        LocalDateTime now = LocalDateTime.now();
        if(!Duration.between(now,dateExpire).isNegative()){
            Seance seance = this.getSeanceById(seanceid);
            if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant) && Objects.nonNull(seance)){
                if(seance.getEnseignantID().equals(loginProf) || seance.getIdSalle().equals(idSalle)){
                    if(now.isAfter(seance.getHeureDebut()) && now.isBefore(seance.getHeureFin()) && seance.getNumEtudiants().contains(numEtudiant)){
                        if(Objects.isNull(seance.getNumEtudiantsPresent())){
                            seance.setNumEtudiantsPresent(List.of(new HeurePointage(now, numEtudiant)));
                        }
                        else {
                            seance.getNumEtudiantsPresent().add(new HeurePointage(now, numEtudiant));
                        }

                        return this.seanceRepository.save(seance);
                    }
                    else {
                        throw new AppelNonPrisEnCompteException();
                    }
                }
                else
                {
                    throw new MauvaisScannerException();
                }
            }
            else{
                throw new EtudiantInexistantException();
            }
        }
        else {
            throw new TokenInValidException();
        }

    }


}
