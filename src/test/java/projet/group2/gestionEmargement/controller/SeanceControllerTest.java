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
import projet.group2.gestionEmargement.dataTest.DataTest;
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
    DataTest dataTest;

    @BeforeEach
    public  void init() {
        this.seanceService = new SeanceService();
    }

    @Test
    public void testCreationSeance_OK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //pour résoudre le format date
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        this.mockMvc.perform(post(URI.create("/api/emargement/seances"))
                        .with(httpBasic(dataTest.emailMembreAdministratif(),dataTest.mdpMembreAdministratif()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataTest.seance1())))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}
