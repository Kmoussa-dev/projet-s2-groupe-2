package repository;

import models.Etudiant;
import models.Groupe;
import models.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IEtudiantRepository extends MongoRepository<Etudiant,String> {

    Etudiant findByNumeroEtudiant(String numeroEtudiant);
    List<Etudiant>  findAllByPromo(Promotion promotion);
    List<Etudiant> findAllByGroupe(Groupe groupe);

}
