package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Seance;

import java.time.LocalDateTime;

@Repository
public interface SeanceRepository extends MongoRepository<Seance,String> {

    Seance getSeanceById(String id);


    Seance getSeanceByHeureDebutAndHeureFinAndDisciplineAndGroupe(LocalDateTime heureDebut, LocalDateTime heureFin, String discipline, Groupe groupe);

}
