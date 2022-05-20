package projet.group2.gestionEmargement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.service.EtudiantService;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTest {

    @Autowired
    private DataEtudiantTest dataEtudiantTest;
    @Autowired
   private EtudiantService etudiantService;
    @Autowired
   private MockMvc mockMvc;

   @BeforeEach
    public  void init() {
       this.etudiantService = new EtudiantService();
   }

    /**
     * test inscription d'un etudiant avec son numéro d'étudiant et un mot de passe
     * @throws Exception
     * 201
     */
   @Test
    public void testInscriptionEtudiantOk() throws Exception {
       ObjectMapper objectMapper = new ObjectMapper();
       this.mockMvc.perform(post("/api/emargement/etudiants")
               .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(dataEtudiantTest.inscriptionOk())))
               .andExpect(status().isCreated())
               .andExpect(header().exists("Location"));
   }


    @Test
    public void testInscriptionEtudiantKo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/api/emargement/etudiants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataEtudiantTest.inscriptionOk())))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/api/emargement/etudiants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataEtudiantTest.inscriptionOk())))
                .andExpect(status().isConflict());
    }

   
   @Test
    public void testGetEtudiants() throws Exception {

       this.mockMvc.perform(get("/api/emargement/etudiants"))
               .andExpect(status().isOk());
   }




}
