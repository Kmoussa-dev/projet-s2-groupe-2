package projet.group2.gestionEmargement.controller;

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

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTest {

    @Autowired
   private EtudiantService etudiantService;
    @Autowired
   private MockMvc mockMvc;
    @Autowired
    //private DataEtudiantTest dataEtudiantTest;

   @BeforeEach
    public  void init() {
       this.etudiantService = new EtudiantService();
   }

    /**
     * test inscription d'un etudiant avec son numéro d'étudiant et un mot de passe
     *
     */
   @Test
    public void testInscriptionEtudiant1() throws Exception {
       this.mockMvc.perform(post("/api/emergement/etudiant")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"numeroEtudiant\": \"8521\", \"motDePasse\": \"526\"}")
               .with(csrf())
       )
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.header().exists("Location"));
   }



}
