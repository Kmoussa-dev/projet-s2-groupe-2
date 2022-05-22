package projet.group2.gestionEmargement.dataTest.impl;

import org.springframework.stereotype.Service;
import projet.group2.gestionEmargement.dataTest.DataSeanceTest;
import projet.group2.gestionEmargement.dto.SeanceDTO;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class DataSeanceTestImpl implements DataSeanceTest {

    @Override
    public String emailMembreAdministratif() { return "yohan.boichut@univ-orleans.fr";}

    @Override
    public String mdpGeneral() { return "mdp"; }

    @Override
    public SeanceDTO seanceOK() {

        SeanceDTO seanceDTO = new SeanceDTO("000000000000","Système et Répartition",
                "00000000001", "TD", new Groupe("TD2",""),
                LocalDateTime.now(),LocalDateTime.now().plusHours(2),
                new Promotion("MASTER 1","2021-2022"), "A VENIR","E18");

        return seanceDTO ;
    }

    @Override
    public SeanceDTO seanceDejaExistante() {
        SeanceDTO seanceDTO = new SeanceDTO("000000000001","Ingénérie des connaissances",
                "00000000001", "TD", new Groupe("TD2",""),
                LocalDateTime.of(LocalDate.parse("2022-01-01"), LocalTime.MIDNIGHT),LocalDateTime.of(LocalDate.parse("2022-01-01"), LocalTime.MIDNIGHT).plusHours(2),
                new Promotion("MASTER 1","2021-2022"), "A VENIR","E18");

        return seanceDTO ;
    }

    @Override
    public String emailEtudiant() {
        return "rachida.el-ouariachi@etu.univ-orleans.fr";
    }

    @Override
    public String emailEnseignant() {
        return "mathieu.chapelle@univ-orleans.fr";
    }

    @Override
    public String emailUtilisateurNonAuthentifie() {return "user-non-auth@univ-orleans.fr";}

    @Override
    public String idSeanceInexistant() { return "xxxxxxx"; }
}
