package projet.group2.gestionEmargement.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.service.EtudiantService;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTest {

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
    public void testInscriptionEtudiant1() throws Exception {
       this.mockMvc.perform(post("/api/emergement/etudiant")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"numeroEtudiant\": \"951354265265\", \"motDePasse\": \"951357\"}")
                       .with(csrf())
       )
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(header().exists("Location"));
   }
   
   @Test
    public void testGetEtudiants() throws Exception {

       this.mockMvc.perform(get("/api/emergement/etudiants"))
               .andExpect(status().isOk());
   }




}
