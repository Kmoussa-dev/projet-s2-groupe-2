package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

    List<Utilisateur> findByFonction(String fonction);
    Utilisateur findByEmail(String email);
}
