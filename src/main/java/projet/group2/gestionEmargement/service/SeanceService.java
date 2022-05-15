package projet.group2.gestionEmargement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.entity.*;
import projet.group2.gestionEmargement.exception.AppelleNonPrisEnCompteException;
import projet.group2.gestionEmargement.exception.EtudiantInexistantException;
import projet.group2.gestionEmargement.exception.MauvaisIdentifiantException;
import projet.group2.gestionEmargement.exception.SeanceInexistanteException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.repository.SeanceRepository;

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
     * @return
     */
    public Seance creerSeance(Seance seance){
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

    /**
     * Récupère toutes les séances enregistrées dans la bdd
     * @return la liste des séances
     */
    public List<Seance> getSeances(){
        return this.seanceRepository.findAll();
    }

    /**
     * Récupère une séance par son identifiant
     * @param id de la séance
     * @return la séance
     */
    public Seance getSeanceById(String id){
        return this.seanceRepository.getSeanceById(id);
    }

    public Seance update(Seance seance){
        return this.seanceRepository.save(seance);
    }

    public void delete(Seance seance){
        this.seanceRepository.delete(seance);
    }


    public Seance getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(LocalDateTime heureDebut, LocalDateTime heureFin, String discipline, Groupe groupe){
        return this.seanceRepository.getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe( heureDebut, heureFin, discipline, groupe);
    }

    /**
     * Permet la génération de token pour la fonctionnalité émarger
     * @param seanceid id de la séance
     * @param numEtudiant numéro étudiant de l'élève qui va émarger
     * @return
     */
    public Seance emarger(String seanceid, String numEtudiant) throws EtudiantInexistantException, AppelleNonPrisEnCompteException
    {
        LocalDateTime now = LocalDateTime.now();
        Seance seance = this.getSeanceById(seanceid);
        if(this.etudiantRepository.existsEtudiantByNumeroEtudiant(numEtudiant) && Objects.nonNull(seance)){
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
                throw new AppelleNonPrisEnCompteException();
            }
        }
        else{
            throw new EtudiantInexistantException();
        }
    }

    /**
     * Permet de récupérer la liste des séances pour une période donnée comprise entre un intervalle de deux dates
     * @param dateDebut de l'intervalle
     * @param dateFin de l'intervalle
     * @return la liste des séances de la période sélectionnée
     */
    public List<Seance> getSeancesByPeriode(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return this.seanceRepository.findAllByHeureDebutBetween(dateDebut,dateFin);
    }

    /**
     * Permet la suppression d'une séance
     * @param id de la séance à supprimer
     * @throws SeanceInexistanteException : si l'id est inexistant dans la bdd
     * @throws MauvaisIdentifiantException : si le format de l'id est incorrect ou null ou vide
     */
    public void deleteSeance(String id) throws SeanceInexistanteException, MauvaisIdentifiantException {

        if (id == null || id.isEmpty() || id.isBlank() || !(id instanceof String) ) {
            throw new MauvaisIdentifiantException();
        }
        if (seanceRepository.existsSeanceById(id)) {
            this.seanceRepository.deleteSeanceById(id);
        } else {
            throw new SeanceInexistanteException();
        }

    }

    /**
     * Modifier les informations d'une séance par une secrétaire
     * @param id de la seéance à éditer
     * @throws SeanceInexistanteException
     * @throws MauvaisIdentifiantException
     */
    public void updateSeance(String id) throws SeanceInexistanteException, MauvaisIdentifiantException {

        if (id == null || id.isEmpty() || id.isBlank() || !(id instanceof String) ) {
            throw new MauvaisIdentifiantException();
        }
        if (seanceRepository.existsSeanceById(id)) {
            this.seanceRepository.save(getSeanceById(id));
        } else {
            throw new SeanceInexistanteException();
        }
    }

}
