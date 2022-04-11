package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Promotion;

import java.util.List;

@Repository
public interface EtudiantRepository extends MongoRepository<Etudiant, String> {


    Etudiant findEtudiantByNumeroEtudiant(String numeroEtudiant);

    Etudiant getEtudiantByEmail(String email);

    List<Etudiant> getEtudiantsByPromo(Promotion promo);

    boolean existsEtudiantByNumeroEtudiant(String numeroEtudiant);


}
