package projet.group2.gestionEmargement.dataTest.impl;

import org.springframework.stereotype.Component;
import projet.group2.gestionEmargement.dataTest.DataEnseignantTest;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
@Component
public class DataEnseignantTestImpl implements DataEnseignantTest {

    @Override
    public EnseignantDTO inscriptionOk() {
        EnseignantDTO enseignantDTO = new EnseignantDTO("yohan@gmail.com","Boichut","Yohan");
        return enseignantDTO;
    }
    @Override
    public String emailEnseignant() {
        return "yohan@gmail.com";
    }
    @Override
    public String nomEnseignant() {
        return "Boichut";
    }
    @Override
    public String prenomEnseignant() {
        return "Yohan";
    }
    @Override
    public String motDePasseEnseignant() {
        return "326541789";
    }

    @Override
    public String emailEnseignant2() {
        return "fred.moal@univ-orleans.fr";
    }
    @Override
    public String nomEnseignant2(){
        return "moal";
    }
    @Override
    public String prenomEnseignant2(){
        return "fred";
    }
    @Override
    public String motDePasseEnseignant2() {
        return "fred";
    }

    @Override
    public String emailEnseignant3() {
        return "mathieu.chapelle@univ-orleans.fr";
    }

    @Override
    public String nomEnseignant3() {
        return "Chapelle";
    }

    @Override
    public String prenomEnseignant3() {
        return "mathieu";
    }

    @Override
    public String motDePasseEnseignant3() {
        return "mathieu";
    }

    @Override
    public String emailEnseignant4() {
        return "jean-michel.couvreur@univ-orleans.fr";
    }

    @Override
    public String nomEnseignant4() {
        return "Couvreur";
    }

    @Override
    public String prenomEnseignant4() {
        return "Jean-michel";
    }

    @Override
    public String motDePasseEnseignant4() {
        return "Jean-michel";
    }

}
