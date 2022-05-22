package projet.group2.gestionEmargement.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.exception.generalException.ErrorCodes;
import projet.group2.gestionEmargement.exception.generalException.EtudiantException;
import projet.group2.gestionEmargement.exception.generalException.UtilisateurException;

import java.util.List;
@SpringBootTest
public class TestEtudiantService {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private DataEtudiantTest dataEtudiantTest;
    @Test
    public void enregistrementEtudiantOK() throws UtilisateurException, EtudiantException {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email(dataEtudiantTest.emailEtudiant())
                .nom(dataEtudiantTest.nomEtudiant())
                .prenom(dataEtudiantTest.prenomEtudiant())
                .motDePasse(dataEtudiantTest.motDePasseEtudiant())
                .numeroEtudiant(dataEtudiantTest.numeroEtudiant())
                .groupe(dataEtudiantTest.groupeEtudiant())
                .promo(dataEtudiantTest.promoEtudiant())
                .build();

        EtudiantDTO savedEtudiant = etudiantService.inscription(expectedEtudiant);

        Assert.assertEquals(expectedEtudiant.getNumeroEtudiant(),savedEtudiant.getNumeroEtudiant());
        Assert.assertEquals(expectedEtudiant.getGroupe().getGroupeDeTD(),savedEtudiant.getGroupe().getGroupeDeTD());
        Assert.assertEquals(expectedEtudiant.getGroupe().getGroupeDeTP(),savedEtudiant.getGroupe().getGroupeDeTP());
    }

    @Test
    public void enregistrementEtudiantKOEnseignantVide() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(5, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(2));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(3));
    }

    @Test
    public void enregistrementEtudiantKONomEnseignantInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));

    }

    @Test
    public void enregistrementEtudiantKOPrenomEnseignantInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test1")
                .motDePasse("test1")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEtudiantKOEmailInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .nom("test1")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEtudiantKOMotDePasseInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test1@univ-orleans.fr")
                .nom("test1")
                .prenom("test1")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEtudiantKONomEtPrenomInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test1@univ-orleans.fr")
                .motDePasse("test")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEtudiantKONomEtEmailInexistant() {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .prenom("test1")
                .motDePasse("test")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEtudiantKONomEtMotDePasseInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .build();
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEtudiantKOPrenomEtEmailInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .nom("test")
                .motDePasse("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }


    @Test
    public void enregistrementEtudiantKOPrenomEtMotDePasseInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEtudiantKOEmailEtMotDePasseInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .nom("test")
                .prenom("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEtudiantKONomEtTousLesAutresInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .nom("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEtudiantKOPrenomEtTousLesAutresInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .prenom("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEtudiantKOEmailEtTousLesAutresInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email("test@univ-orleans.fr")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEtudiantKOMotDePasseEtTousLesAutresInexistant(){
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .motDePasse("test")
                .build();

        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.inscription(expectedEtudiant));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(8000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
    }

    @Test
    public void enregistrementEtudiantKOEmailDansLaBase() throws UtilisateurException, EtudiantException {
        EtudiantDTO expectedEtudiant = EtudiantDTO.builder()
                .email(dataEtudiantTest.emailEtudiant2())
                .nom(dataEtudiantTest.nomEtudiant2())
                .prenom(dataEtudiantTest.prenomEtudiant2())
                .motDePasse(dataEtudiantTest.motDePasseEtudiant2())
                .numeroEtudiant(dataEtudiantTest.numeroEtudiant2())
                .groupe(dataEtudiantTest.groupeEtudiant2())
                .promo(dataEtudiantTest.promoEtudiant2())
                .build();
        EtudiantDTO savedEtudiant = etudiantService.inscription(expectedEtudiant);

        EtudiantDTO expectedEtudiant2 = EtudiantDTO.builder()
                .email(dataEtudiantTest.emailEtudiant2())
                .nom(dataEtudiantTest.nomEtudiant2())
                .prenom(dataEtudiantTest.prenomEtudiant2())
                .motDePasse(dataEtudiantTest.motDePasseEtudiant2())
                .numeroEtudiant(dataEtudiantTest.numeroEtudiant2())
                .groupe(dataEtudiantTest.groupeEtudiant2())
                .promo(dataEtudiantTest.promoEtudiant2())
                .build();
        UtilisateurException expectedException = Assert.assertThrows(UtilisateurException.class, () -> etudiantService.inscription(expectedEtudiant2));
        Assert.assertEquals(ErrorCodes.UTILISATEUR_ALREADY_IN_USE, expectedException.getErrorCode());
        Assert.assertEquals(7000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
    }

    @Test
    public void recuperationEtudiantOK() throws EtudiantException {

        EtudiantDTO returnedEtudiant= etudiantService.getEtudiantbyEmail("moussa.bakayoko@etu.univ-orleans.fr");

        Assert.assertNotNull(returnedEtudiant.getNumeroEtudiant());
        Assert.assertNotNull(returnedEtudiant.getGroupe().getGroupeDeTD());
        Assert.assertNotNull(returnedEtudiant.getGroupe().getGroupeDeTP());
        Assert.assertNotNull(returnedEtudiant.getPromo().getNiveau());
        Assert.assertNotNull(returnedEtudiant.getPromo().getAnnee());
    }

    @Test
    public void recuperationEtudiantKOIDnullInvalid() {
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () ->etudiantService.getEtudiantbyEmail(null));
        Assert.assertEquals(ErrorCodes.ID_ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("l'Id n'est pas valable", expectedException.getErrors().get(0));
    }
    @Test
    public void recuperationEtudiantKOIDblankInvalid() {
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.getEtudiantbyEmail("   "));
        Assert.assertEquals(ErrorCodes.ID_ETUDIANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("L'Id contient que des espaces", expectedException.getErrors().get(0));
    }

    @Test
    public void recuperationEtudiantKOEtudiantInvalid() {
        EtudiantException expectedException = Assert.assertThrows(EtudiantException.class, () -> etudiantService.getEtudiantbyEmail("mathieuxx.chapeau@univ-orleans.fr"));
        Assert.assertEquals(ErrorCodes.ETUDIANT_NOT_FOUND, expectedException.getErrorCode());
        Assert.assertEquals(0, expectedException.getErrors().size());
        Assert.assertEquals("Etudiant inexistant", expectedException.getMessage());
    }

    @Test
    public void recuperationTousLesEtudiantsOk() throws  EtudiantException {
        List<EtudiantDTO> returnedEtudiant= etudiantService.getEtudiants();
        Assert.assertNotNull(returnedEtudiant.size());
    }



}
