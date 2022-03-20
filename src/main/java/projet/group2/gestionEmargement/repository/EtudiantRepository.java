package projet.group2.gestionEmargement.repository;

import projet.group2.gestionEmargement.entity.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EtudiantRepository extends MongoRepository<Etudiant,String> {
}
