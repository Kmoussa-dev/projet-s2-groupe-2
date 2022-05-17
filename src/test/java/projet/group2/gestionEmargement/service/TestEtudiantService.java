package projet.group2.gestionEmargement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTestImpl;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.exception.NuneroEtudiantDejaExistException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

public class TestEtudiantService {

    EtudiantService instance ;
    DataEtudiantTest dataEtudiantTest;
    EtudiantRepository etudiantRepositoryMock;

    @Autowired
    EtudiantRepository dao ;
    public  TestEtudiantService(){this.dataEtudiantTest = new DataEtudiantTestImpl();}
    /**
     * initialisation du facade
     */
    @BeforeEach
    public void initialiseInstance() {instance = new EtudiantService();}

    /**
     * inscription d'un etudiant avec un numero d'etudiant déja existant
     */
    @Test void testInscriptionEtudiant1() {
        String numeroEtudiant = dataEtudiantTest.numeroEtudiant();
        String motDePasse = dataEtudiantTest.motDePasseEtudiant();

        String numeroEtudiant1 = dataEtudiantTest.numeroEtudiant();
        String motDePasse1 = dataEtudiantTest.motDePasseEtudiant();

        Etudiant etudiant = new Etudiant();
        etudiant.setNumeroEtudiant(numeroEtudiant);
        etudiant.setMotDePasse(motDePasse);

        Etudiant etudiant1 = new Etudiant();
        etudiant.setNumeroEtudiant(numeroEtudiant1);
        etudiant.setMotDePasse(motDePasse1);
        Assertions.assertDoesNotThrow(()->this.instance.inscription(etudiant));
        Assertions.assertThrows(NuneroEtudiantDejaExistException.class,()->this.instance.inscription(etudiant1));
    }

}
