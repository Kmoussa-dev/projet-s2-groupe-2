package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projet.group2.gestionEmargement.entity.Utilisateur;

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {
}
