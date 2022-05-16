package projet.group2.gestionEmargement.dataTest;

import org.springframework.stereotype.Component;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

import java.time.LocalDateTime;

@Component
public class DataTestImpl implements DataTest {

    //
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


    @Override
    public String emailMembreAdministratif() { return "martins.delacourt@univ-orleans.fr"; }

    @Override
    public String mdpMembreAdministratif() { return "martins"; }

    @Override
    public SeanceDTO seance1() {

        SeanceDTO seanceDTO = new SeanceDTO("000000000000","Système et Répartition",
                "martins.delacourt@univ-orleans.fr", "TD", new Groupe("TD2",""),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                new Promotion("MASTER 1","2021-2022"), "A VENIR","E12");

        return seanceDTO ;
    }
}
