package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestParam;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

    List<Utilisateur> findByFonction(String fonction);
    Utilisateur findByEmail(String email);
    //List<Utilisateur> findByGroupe(Groupe groupe);
    List<Utilisateur> findByPromoNiveau(String niveau);
    List<Utilisateur> findByPromoNiveauAndPromoAnnee(String niveau,String annee);

}
