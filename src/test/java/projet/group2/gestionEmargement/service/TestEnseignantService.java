package projet.group2.gestionEmargement.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.UtilisateurDTO;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.exception.enseignantException.EnseignantException;
import projet.group2.gestionEmargement.exception.enseignantException.ErrorCodes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnseignantService {

    @Autowired
    private EnseignantService enseignantService;

    @Test
    public void enregistrementEnseignantOK() throws EnseignantException {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("mos@univ-orleans.fr")
                .nom("ko")
                .prenom("mous")
                .motDePasse("mouss")
                .build();
        EnseignantDTO savedEnseignant = enseignantService.inscription(expectedEnseignant);
        Assert.assertNotNull(savedEnseignant);
        Assert.assertNotNull(savedEnseignant.getEmail());
        Assert.assertEquals(expectedEnseignant.getEmail(), savedEnseignant.getEmail());
        Assert.assertEquals(expectedEnseignant.getNom(), savedEnseignant.getNom());
        Assert.assertEquals(expectedEnseignant.getPrenom(), savedEnseignant.getPrenom());
    }

    @Test
    public void enregistrementEnseignantKOEnseignantVide() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(4, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(2));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(3));
    }

    @Test
    public void enregistrementEnseignantKONomEnseignantInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));

    }

    @Test
    public void enregistrementEnseignantKOPrenomEnseignantInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test1")
                .motDePasse("test1")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEnseignantKOEmailInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .nom("test1")
                .prenom("test1")
                .motDePasse("test1")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEnseignantKOMotDePasseInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test1@univ-orleans.fr")
                .nom("test1")
                .prenom("test1")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(0));
    }

    @Test
    public void enregistrementEnseignantKONomEtPrenomInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test1@univ-orleans.fr")
                .motDePasse("test")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEnseignantKONomEtEmailInexistant() {
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .prenom("test1")
                .motDePasse("test")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEnseignantKONomEtMotDePasseInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .prenom("test1")
                .build();
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEnseignantKOPrenomEtEmailInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .nom("test")
                .motDePasse("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
    }


    @Test
    public void enregistrementEnseignantKOPrenomEtMotDePasseInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .nom("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEnseignantKOEmailEtMotDePasseInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .nom("test")
                .prenom("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(2, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(1));
    }

    @Test
    public void enregistrementEnseignantKONomEtTousLesAutresInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .nom("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEnseignantKOPrenomEtTousLesAutresInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .prenom("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEnseignantKOEmailEtTousLesAutresInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .email("test@univ-orleans.fr")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner le mot de passe", expectedException.getErrors().get(2));
    }

    @Test
    public void enregistrementEnseignantKOMotDePasseEtTousLesAutresInexistant(){
        UtilisateurDTO expectedEnseignant = UtilisateurDTO.builder()
                .motDePasse("test")
                .build();

        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.inscription(expectedEnseignant));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(5001, expectedException.getErrorCode().getCode());
        Assert.assertEquals(3, expectedException.getErrors().size());
        Assert.assertEquals("Veuillez renseigner le nom", expectedException.getErrors().get(0));
        Assert.assertEquals("Veuillez renseigner le prenom", expectedException.getErrors().get(1));
        Assert.assertEquals("Veuillez renseigner l'email", expectedException.getErrors().get(2));
    }

    @Test
    public void recuperationEnseignantOK() throws EnseignantException {

        EnseignantDTO returnedEnseignant= enseignantService.getEnseignantByEmail("secretariat@univ-orleans.fr");

        Assert.assertNotNull(returnedEnseignant.getEmail());
        Assert.assertNotNull(returnedEnseignant.getPrenom());
        Assert.assertNotNull(returnedEnseignant.getNom());
    }

    @Test
    public void recuperationEnseignantKOIDnullInvalid() throws EnseignantException {
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.getEnseignantByEmail(null));
        Assert.assertEquals(ErrorCodes.ID_ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("l'Id n'est pas valable", expectedException.getErrors().get(0));
    }
    @Test
    public void recuperationEnseignantKOIDblankInvalid() throws EnseignantException {
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.getEnseignantByEmail("   "));
        Assert.assertEquals(ErrorCodes.ID_ENSEIGNANT_NOT_VALID, expectedException.getErrorCode());
        Assert.assertEquals(1, expectedException.getErrors().size());
        Assert.assertEquals("L'Id contient que des espaces", expectedException.getErrors().get(0));
    }

    @Test
    public void recuperationEnseignantKOEnseignantInvalid() throws EnseignantException {
        EnseignantException expectedException = Assert.assertThrows(EnseignantException.class, () -> enseignantService.getEnseignantByEmail("mathieu.chapeau@univ-orleans.fr"));
        Assert.assertEquals(ErrorCodes.ENSEIGNANT_NOT_FOUND, expectedException.getErrorCode());
        Assert.assertEquals(0, expectedException.getErrors().size());
        Assert.assertEquals("Enseignant inexistant", expectedException.getMessage());
    }
}
