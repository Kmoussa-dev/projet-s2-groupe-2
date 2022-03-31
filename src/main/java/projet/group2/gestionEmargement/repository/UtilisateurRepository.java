package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestParam;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

    List<Utilisateur> findByFonction(String fonction);
    Utilisateur findByEmail(String email);

    //recupère les etudiants d'un groupe
    // 1 - repository -> fait ci-dessous
    // 2 - ServiceUtilisateur
    // 3 - controlleur
    // 4 - test avec requette http get (POST creer plusieur etudiant d'un groupe + GET)

    /*
     * @param groupe
     * @return liste des étudiants d'un groupe de TD ou TP
     */
    List<Utilisateur> findByGroupeTd(String td);
    List<Utilisateur> findByGroupeTp(String tp);
    List<Utilisateur> findByGroupeTdAndGroupeTp(String td, String tp);

    List<Utilisateur> findByPromoNiveau(String niveau);
    List<Utilisateur> findByPromoNiveauAndPromoAnnee(String niveau,String annee);


}
