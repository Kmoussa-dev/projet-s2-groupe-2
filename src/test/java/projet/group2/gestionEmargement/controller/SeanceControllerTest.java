package projet.group2.gestionEmargement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projet.group2.gestionEmargement.dataTest.DataSeanceTest;
import projet.group2.gestionEmargement.service.SeanceService;

import java.net.URI;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SeanceControllerTest {

    @Autowired
    private SeanceService seanceService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    DataSeanceTest dataSeanceTest;

    @BeforeEach
    public void init() {
        this.seanceService = new SeanceService();
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
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seance1())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
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
                .content(objectMapper.writeValueAsString(dataSeanceTest.seance1())))
                .andExpect(status().isForbidden());
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
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seance1())))
                .andExpect(status().isCreated());

        //recréer la même séance
        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataSeanceTest.emailMembreAdministratif(),dataSeanceTest.mdpGeneral()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataSeanceTest.seance1())))
                .andExpect(status().isConflict());
    }


}