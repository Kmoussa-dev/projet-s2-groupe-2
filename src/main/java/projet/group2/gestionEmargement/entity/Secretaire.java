package projet.group2.gestionEmargement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection="secretaires")
public class Secretaire extends Utilisateur {

    public Secretaire() {
    }

    public Secretaire(String email, String nom, String prenom, String motDePasse, List<String> roles) {
        super(email, nom, prenom, motDePasse, roles);
    }

}
