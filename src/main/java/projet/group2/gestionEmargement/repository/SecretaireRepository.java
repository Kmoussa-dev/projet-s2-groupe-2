package projet.group2.gestionEmargement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projet.group2.gestionEmargement.entity.Secretaire;

import java.util.Map;

@Repository
public interface SecretaireRepository extends MongoRepository<Secretaire, String> {
    Secretaire getSecretaireByEmail(String email);
    Secretaire getSecretaireById(String id);
}


