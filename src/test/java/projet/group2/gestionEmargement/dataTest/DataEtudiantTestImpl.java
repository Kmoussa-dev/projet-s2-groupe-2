package projet.group2.gestionEmargement.dataTest;

import org.springframework.stereotype.Component;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

@Component
public class DataEtudiantTestImpl implements DataEtudiantTest {
    @Override
    public EtudiantDTO inscriptionOk() {
        EtudiantDTO etudiantDTO = new EtudiantDTO("fatima.elbardi@etu.univ-orleans.fr","fatimaE","fatima","mdpss",
                "1987654",new Groupe("TP1","TD1"),
                new Promotion("MASTER 1","2021-2022"));
        return etudiantDTO;
    }

    @Override
    public String numeroEtudiant() {
        return "o22010238";
    }

    @Override
    public String emailEtudiant() {
        return "fatima.elbardi@etu.univ-orleans.fr";
    }

    @Override
    public String motDePasseEtudiant() {
        return "123";
    }
}
