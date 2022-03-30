package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projet.group2.gestionEmargement.entity.Enseignant;

@Repository
public interface EnseignantRepository extends MongoRepository<Enseignant, String> {
}
