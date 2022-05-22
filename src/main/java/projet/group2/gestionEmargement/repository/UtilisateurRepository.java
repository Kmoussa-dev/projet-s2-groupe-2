package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projet.group2.gestionEmargement.entity.Secretaire;
import projet.group2.gestionEmargement.entity.Utilisateur;

import java.util.List;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String>   {

//    List<Utilisateur> findByFonction(String fonction);
    Utilisateur findByEmail(String email);


}
