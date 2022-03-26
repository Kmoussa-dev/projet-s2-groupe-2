package projet.group2.gestionEmargement.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Groupe {

    @Field(name = "groupeDeTD")
    private String groupeDeTD;

    @Field(name = "groupeDeTP")
    private String groupeDeTP;

    public String getGroupeDeTD() {
        return groupeDeTD;
    }

    public void setGroupeDeTD(String groupeDeTD) {
        this.groupeDeTD = groupeDeTD;
    }

    public String getGroupeDeTP() {
        return groupeDeTP;
    }

    public void setGroupeDeTP(String groupeDeTP) {
        this.groupeDeTP = groupeDeTP;
    }
}
