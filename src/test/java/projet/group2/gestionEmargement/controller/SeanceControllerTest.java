package projet.group2.gestionEmargement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projet.group2.gestionEmargement.dataTest.DataSeanceTest;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.repository.SeanceRepository;
import projet.group2.gestionEmargement.service.SeanceService;

import java.net.URI;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class SeanceControllerTest {

    @Autowired
    private SeanceService seanceService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    DataSeanceTest dataSeanceTest;

    @Autowired
    private SeanceRepository seanceRepository;

    @BeforeEach
    public void init() {
        this.seanceService = new SeanceService();
//        this.seanceRepository.deleteAll();
    }

    /**
     * Création d'une séance avec un utilisateur Membre Administratif
     * @throws Exception
     * Code 201 retourné + Location
     */
    @Test
    public void testCreationSeance_OK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //pour résoudre le format date
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seanceOK())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                //lignes à ajouter pour générer la doc tech
                .andDo(document("creer-seance",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                        ));
    }


    /**
     * Création d'une séance avec utilisateur non Membre Administratif
     * @throws Exception
     * Code 403 retourné
     */
    @Test
    public void testCreationSeance_KO_RoleNonAuthorise() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                .with(httpBasic(dataSeanceTest.emailEnseignant(),dataSeanceTest.mdpGeneral()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataSeanceTest.seanceOK())))
                .andExpect(status().isForbidden())
                .andDo(document("creer-seance-KO-403",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())
                ));
    }

    /**
     * Création d'une séance avec mêmes horaires, même discipline, même groupe qu'une séance déjà existante
     * @throws Exception
     * Code 409 retourné
     */
    @Test
    public void testCreationSeance_KO_SeanceDejaExistant() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //créer une séance
        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seanceDejaExistante())))
                .andExpect(status().isCreated());

        //recréer la même séance
        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seanceDejaExistante())))
                .andExpect(status().isConflict())
                .andDo(document("creer-seance-KO-409",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }


    /**
     * Un utilisateur authentifié récupère une séance existante
     * @throws Exception
     * Code attendu : 200
     */
    @Test
    public void testGetSeanceById_OK() throws Exception {

        AtomicReference<String> identifiantSeance = new AtomicReference<>("");
        AtomicReference<Seance> seanceRecuperee= new AtomicReference<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //création de la séance
        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seanceOK())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo((v) -> {
                    identifiantSeance.set(v.getResponse().getHeader("Location"));
                });
        String[] idDecompose = identifiantSeance.get().split("/");
        String idSeance = idDecompose[idDecompose.length-1];

        this.mockMvc.perform(get(URI.create("/api/emargement/seances/"+ idSeance))
                .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo((x) -> {seanceRecuperee.set(objectMapper.readValue(x.getResponse().getContentAsString(), Seance.class));});

        Seance res = seanceRecuperee.get();

        if (Objects.nonNull(res)) {
            Assertions.assertEquals(idSeance, res.getId());
        }
        else {
            Assertions.fail("Séance non récupérée");
        }
    }


    /**
     * Un utilisateur non authentifié essaie de récupérer une séance
     * @throws Exception
     * Code attendu : 401
     */
    @Test
    public void testGetSeanceById_KO_UtilisateurNonAuthentifie() throws Exception {

        AtomicReference<String> identifiantSeance = new AtomicReference<>("");
        AtomicReference<Seance> seanceRecuperee= new AtomicReference<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //création de la séance
        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seanceOK())))
                .andExpect(status().isCreated())
                .andDo((v) -> {
                    identifiantSeance.set(v.getResponse().getHeader("Location"));
                });
        String[] idDecompose = identifiantSeance.get().split("/");
        String idSeance = idDecompose[idDecompose.length-1];

        this.mockMvc.perform(get(URI.create("/api/emargement/seances/"+ idSeance))
                        .with(httpBasic(dataSeanceTest.emailUtilisateurNonAuthentifie(),dataSeanceTest.mdpGeneral()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());

    }



    /**
     * Un utilisateur essaie de récupérer une séance inexistante
     * @throws Exception
     * Code attendu : 404
     */
    @Test
    public void testGetSeanceById_KO_SeanceInexistante() throws Exception {

        this.mockMvc.perform(get(URI.create("/api/emargement/seances/"+dataSeanceTest.idSeanceInexistant()))
                        .with(httpBasic(dataSeanceTest.emailEtudiant(),dataSeanceTest.mdpGeneral()))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }



}
