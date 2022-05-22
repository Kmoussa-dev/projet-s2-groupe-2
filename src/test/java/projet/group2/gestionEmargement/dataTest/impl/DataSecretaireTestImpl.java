package projet.group2.gestionEmargement.dataTest.impl;

import org.springframework.stereotype.Component;
import projet.group2.gestionEmargement.dataTest.DataSecretaireTest;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.SecretaireDTO;
@Component
public class DataSecretaireTestImpl implements DataSecretaireTest {
    @Override
    public SecretaireDTO inscriptionOk() {
        SecretaireDTO secretaireDTO = new SecretaireDTO("brigitte.dupuis@univ-orleans.fr","Dupuis","Brigitte");
        return secretaireDTO;
    }

    @Override
    public String emailSecretaire() {
        return "brigitte.dupuis@univ-orleans.fr";
    }

    @Override
    public String nomSecretaire() {
        return "Dupuis";
    }

    @Override
    public String prenomSecretaire() {
        return "Brigitte";
    }

    @Override
    public String motDePasseSecretaire() {
        return "dupuis";
    }

    @Override
    public String emailSecretaire2() {
        return "florence.maubert@univ-orleans.fr";
    }

    @Override
    public String nomSecretaire2() {
        return "Maubert";
    }

    @Override
    public String prenomSecretaire2() {
        return "Florence";
    }

    @Override
    public String motDePasseSecretaire2() {
        return "florence";
    }
}
