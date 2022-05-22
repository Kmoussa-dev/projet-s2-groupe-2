package projet.group2.gestionEmargement.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection="enseignants")
public class Enseignant extends Utilisateur {

    public Enseignant() {
    }

    public Enseignant(String email, String nom, String prenom, String motDePasse, List<String> roles) {
        super(email, nom, prenom, motDePasse, roles);
    }

}
