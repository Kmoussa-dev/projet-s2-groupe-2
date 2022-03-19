package projet.group2.gestionEmargement.entite;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public enum Emerge {

    PRESENT("PRE"), ABSENT("ABS");

    @Id
    private String id;
    @Field(name = "emargement")
    private String abreviation;

    private Emerge(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return this.abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
}
