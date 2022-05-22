package projet.group2.gestionEmargement.dataTest.impl;

import org.springframework.stereotype.Component;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

@Component
public class DataEtudiantTestImpl implements DataEtudiantTest {
    @Override
    public EtudiantDTO inscriptionOk() {
        EtudiantDTO etudiantDTO = new EtudiantDTO("fatima.elbardi@etu.univ-orleans.fr","fatimaE","fatima","mdpss",
                "1987654",new Groupe("TP1","TD1"),
                new Promotion("MASTER 1","2021-2022"),false,"");
        return etudiantDTO;
    }
    @Override
    public String emailEtudiant() {
        return "fatima.elbardi@etu.univ-orleans.fr";
    }

    @Override
    public String nomEtudiant() {
        return "elbardi";
    }

    @Override
    public String prenomEtudiant() {
        return "fatima";
    }
    @Override
    public String motDePasseEtudiant() {
        return "123";
    }

    @Override
    public String numeroEtudiant() {
        return "o22010238";
    }

    @Override
    public Groupe groupeEtudiant() {
        return new Groupe("TD2","TP2");
    }

    @Override
    public Promotion promoEtudiant() {
        return new Promotion("MASTER 1","2021-2022");
    }

    @Override
    public String emailEtudiant2() {
        return "solomon.lourdessamy@etu.univ-orleans.fr";
    }

    @Override
    public String nomEtudiant2() {
        return "lourdessamy";
    }

    @Override
    public String prenomEtudiant2() {
        return "solomon";
    }

    @Override
    public String motDePasseEtudiant2() {
        return "solomon";
    }

    @Override
    public String numeroEtudiant2() {
        return "o22009067";
    }

    @Override
    public Groupe groupeEtudiant2() {
        return new Groupe("TD2","TP2");
    }

    @Override
    public Promotion promoEtudiant2() {
        return new Promotion("MASTER 1","2020-2021");
    }
}

