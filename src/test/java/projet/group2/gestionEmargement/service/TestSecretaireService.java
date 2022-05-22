package projet.group2.gestionEmargement.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projet.group2.gestionEmargement.dataTest.DataSecretaireTest;
import projet.group2.gestionEmargement.dto.SecretaireDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.exception.generalException.ErrorCodes;
import projet.group2.gestionEmargement.exception.generalException.SecretaireException;
import projet.group2.gestionEmargement.exception.generalException.UtilisateurException;

import java.util.List;

@SpringBootTest
public class TestSecretaireService {

    @Autowired
    private SecretaireService secretaireService;

    @Autowired
    private DataSecretaireTest dataSecretaireTest;

    @Test
    public void enregistrementSecretaireOK() throws SecretaireException, UtilisateurException {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email(dataSecretaireTest.emailSecretaire())
                .nom(dataSecretaireTest.nomSecretaire())
                .prenom(dataSecretaireTest.prenomSecretaire())
                .motDePasse(dataSecretaireTest.motDePasseSecretaire())
                .build();
        SecretaireDTO savedSecretaire = secretaireService.inscription(expectedSecretaire);
        Assert.assertNotNull(savedSecretaire);
        Assert.assertNotNull(savedSecretaire.getEmail());
        Assert.assertEquals(expectedSecretaire.getEmail(), savedSecretaire.getEmail());
        Assert.assertEquals(expectedSecretaire.getNom(), savedSecretaire.getNom());
        Assert.assertEquals(expectedSecretaire.getPrenom(), savedSecretaire.getPrenom());
    }

    @Test
    public void enregistrementSecretaireKOSecretaireVide() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(2));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(3));
    }

    @Test
    public void enregistrementSecretaireKONomSecretaireInexistant() {
        UtilisateurDTO expectedSecretaire= UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));

    }

    @Test
    public void enregistrementSecretaireKOPrenomEnseignantInexistant() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test1")
                .motDePasse("test1")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementSecretaireKOEmailInexistant() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .nom("test1")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementSecretaireKOMotDePasseInexistant() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test1@univ-orleans.fr")
                .nom("test1")
                .prenom("test1")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementSecretaireKONomEtPrenomInexistant() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test1@univ-orleans.fr")
                .motDePasse("test")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementSecretaireKONomEtEmailInexistant() {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .prenom("test1")
                .motDePasse("test")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementSecretaireKONomEtMotDePasseInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .build();
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementSecretaireKOPrenomEtEmailInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .nom("test")
                .motDePasse("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }


    @Test
    public void enregistrementSecretaireKOPrenomEtMotDePasseInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementSecretaireKOEmailEtMotDePasseInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .nom("test")
                .prenom("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementSecretaireKONomEtTousLesAutresInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .nom("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementSecretaireKOPrenomEtTousLesAutresInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .prenom("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementSEcretaireKOEmailEtTousLesAutresInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementSecretaireKOMotDePasseEtTousLesAutresInexistant(){
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .motDePasse("test")
                .build();

        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.inscription(expectedSecretaire));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(6001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementSecretaireKOEmailDansLaBase() throws SecretaireException, UtilisateurException {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email(dataSecretaireTest.emailSecretaire2())
                .nom(dataSecretaireTest.nomSecretaire2())
                .prenom(dataSecretaireTest.prenomSecretaire2())
                .motDePasse(dataSecretaireTest.motDePasseSecretaire2())
                .build();

        SecretaireDTO savedSecretaire = secretaireService.inscription(expectedSecretaire);

        UtilisateurDTO expectedSecretaire2 = UtilisateurDTO.builder()
                .email(dataSecretaireTest.emailSecretaire2())
                .nom(dataSecretaireTest.nomSecretaire2())
                .prenom(dataSecretaireTest.prenomSecretaire2())
                .motDePasse(dataSecretaireTest.motDePasseSecretaire2())
                .build();

        UtilisateurException expectedException = Assert.assertThrows(UtilisateurException.class, () -> secretaireService.inscription(expectedSecretaire2));
        Assert.assertEquals(ErrorCodes.UTILISATEUR_ALREADY_IN_USE, expectedException.getErrorCode());
        Assert.assertEquals(7000, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
    }

    @Test
    public void recuperationSecretaireOK() throws SecretaireException, UtilisateurException {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email(dataSecretaireTest.emailSecretaire3())
                .nom(dataSecretaireTest.nomSecretaire3())
                .prenom(dataSecretaireTest.prenomSecretaire3())
                .motDePasse(dataSecretaireTest.motDePasseSecretaire3())
                .build();
        SecretaireDTO savedSecretaire = secretaireService.inscription(expectedSecretaire);

        SecretaireDTO returnedSecretaire= secretaireService.getSecretaireByEmail(dataSecretaireTest.emailSecretaire3());

        Assert.assertNotNull(returnedSecretaire.getEmail());
        Assert.assertNotNull(returnedSecretaire.getPrenom());
        Assert.assertNotNull(returnedSecretaire.getNom());
    }

    @Test
    public void recuperationSecretaireKOIDnullInvalid() throws SecretaireException {
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.getSecretaireByEmail(null));
        Assert.assertEquals(ErrorCodes.ID_SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("l'Id n'est pas valable", expectedException.getErrors().get(0));
    }
    @Test
    public void recuperationSecretaireKOIDblankInvalid() throws SecretaireException {
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.getSecretaireByEmail("   "));
        Assert.assertEquals(ErrorCodes.ID_SECRETAIRE_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("L'Id contient que des espaces", expectedException.getErrors().get(0));
    }

    @Test
    public void recuperationSecretaireKOSecretaireInvalid() throws SecretaireException {
        SecretaireException expectedException = Assert.assertThrows(SecretaireException.class, () -> secretaireService.getSecretaireByEmail("mathiesqiof.chapeau@univ-orleans.fr"));
        Assert.assertEquals(ErrorCodes.SECRETAIRE_NOT_FOUND, expectedException.getErrorCode());
        Assert.assertEquals(0, expectedException.getErrors().size());
        Assert.assertEquals("Sécretaire inexistante", expectedException.getMessage());
    }

    @Test
    public void recuperationToutesLesSecretairesOk() throws SecretaireException, UtilisateurException {
        UtilisateurDTO expectedSecretaire = UtilisateurDTO.builder()
                .email(dataSecretaireTest.emailSecretaire4())
                .nom(dataSecretaireTest.nomSecretaire4())
                .prenom(dataSecretaireTest.prenomSecretaire4())
                .motDePasse(dataSecretaireTest.motDePasseSecretaire4())
                .build();
        SecretaireDTO savedSecretaire = secretaireService.inscription(expectedSecretaire);
        List<SecretaireDTO> returnedSecretaire= secretaireService.getSecretaires();
        Assert.assertNotNull(returnedSecretaire.size());
    }



}
