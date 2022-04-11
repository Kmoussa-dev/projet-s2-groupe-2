package projet.group2.gestionEmargement.dataTest;

import org.springframework.stereotype.Component;

@Component
public class DataEtudiantTestImpl implements DataEtudiantTest{
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
