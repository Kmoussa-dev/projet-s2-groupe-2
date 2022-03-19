package projet.group2.gestionEmargement.entite;

import org.springframework.data.mongodb.core.mapping.Field;

public enum StatutSeance {

    V("VALIDEE"),
    EV("EN COURS DE VALIDATION"),
    NV("NON VALIDEE");

    @Field(name = "statutSeance")
    private String abreviation ;

    private StatutSeance(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getAbreviation() {
        return  this.abreviation ;
    }
}
