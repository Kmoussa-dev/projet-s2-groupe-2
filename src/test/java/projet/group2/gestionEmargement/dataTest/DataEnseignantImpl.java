package projet.group2.gestionEmargement.dataTest;

import projet.group2.gestionEmargement.dto.EnseignantDTO;

public class DataEnseignantImpl implements DataEnseignantTest{

    @Override
    public EnseignantDTO inscriptionOk() {
        EnseignantDTO enseignantDTO = new EnseignantDTO("yohan@gmail.com","Boichut","Yohan","326541789");
        return enseignantDTO;
    }

    @Override
    public String emailEnseignant() {
        return "yohan@gmail.com";
    }

    @Override
    public String motDePasseEnseignant() {
        return "326541789";
    }
}
