package projet.group2.gestionEmargement.entite;

import org.springframework.data.mongodb.core.mapping.Field;

public enum TypeSeance {

        COUR_MAGISTRAL("CM"),
        TRAVAUX_DIRIGES("TD"),
        TRAVAUX_PRATIQUES("TP"),
        TRAVAIL_EN_AUTONOMIE("TA");

        @Field(name = "typeDeSeance")
        private String abreviation ;

        private TypeSeance(String abreviation) {
            this.abreviation = abreviation ;
        }

        public String getAbreviation() {
            return  this.abreviation ;
        }
}
