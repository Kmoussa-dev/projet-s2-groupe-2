package projet.group2.gestionEmargement.entity;

import org.springframework.data.mongodb.core.mapping.Field;


public class Groupe {

    @Field(name = "td")
    private String td;

    @Field(name = "tp")
    private String tp;

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
}
