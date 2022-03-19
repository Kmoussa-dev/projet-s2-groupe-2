package projet.group2.gestionEmargement.repository;

import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.entite.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EtudiantRepository extends MongoRepository<Etudiant,String> {

}
