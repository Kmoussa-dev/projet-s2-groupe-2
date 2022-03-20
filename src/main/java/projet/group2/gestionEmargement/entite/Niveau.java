package projet.group2.gestionEmargement.entite;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public enum Niveau {

    M1("MASTER 1"), M2("MASTER 2");

//    @Id
//    private String id;
    @Field(name = "niveau")
    private String abreviation;


    private Niveau(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return this.abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    //Exemple d'utilisation d'enum privé
    // Niveau niveau = Niveau.M1 ;
    // System.out.println("Niveau : " + niveau + " [" + niveau.getAbreviation() + "]") ;
    // -----> Niveau : M1 [MASTER 1]
}

