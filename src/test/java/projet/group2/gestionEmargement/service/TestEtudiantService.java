package projet.group2.gestionEmargement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import projet.group2.gestionEmargement.dataTest.DataTest;
import projet.group2.gestionEmargement.dataTest.DataTestImpl;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.exception.NuneroEtudiantDejaExistException;
import projet.group2.gestionEmargement.repository.EtudiantRepository;

public class TestEtudiantService {

    EtudiantService instance ;
    DataTest dataTest;
    EtudiantRepository etudiantRepositoryMock;

    @Autowired
    EtudiantRepository dao ;
    public  TestEtudiantService(){
        this.dataTest = new DataTestImpl();
    }
    /**
     * initialisation du facade
     */
    @BeforeEach
    public void initialiseInstance() {
        instance = new EtudiantService();
    }

    /**
     * inscription d'un etudiant avec un numero d'etudiant déja existant
     */
    @Test void testInscriptionEtudiant1() {
        String numeroEtudiant = dataTest.numeroEtudiant();
        String motDePasse = dataTest.motDePasseEtudiant();

        String numeroEtudiant1 = dataTest.numeroEtudiant();
        String motDePasse1 = dataTest.motDePasseEtudiant();

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
