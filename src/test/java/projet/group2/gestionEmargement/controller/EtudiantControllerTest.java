package projet.group2.gestionEmargement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.service.EtudiantService;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EtudiantControllerTest {

    @Autowired
    private DataEtudiantTest dataEtudiantTest;
    @MockBean
    private EtudiantService etudiantService;

    @MockBean
    private EtudiantRepository repository;
    @Autowired
    private MockMvc mockMvc;

   @BeforeEach
    public  void init() {
       //this.etudiantService = new EtudiantService();
       MockitoAnnotations.initMocks(this);
   }

    /**
     * test inscription d'un etudiant avec son numéro d'étudiant et un mot de passe
     * @throws Exception
     * 201
     */
   @Test
    public void testInscriptionEtudiantOk() throws Exception {

       given(etudiantService.inscription(any(Etudiant.class))).willReturn(EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()));

       ObjectMapper objectMapper = new ObjectMapper();
       this.mockMvc.perform(post("/api/emargement/etudiants")
               .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()))))
               .andExpectAll(status().isCreated())
               .andExpect(header().exists("Location"));
   }


   // @Test
//    public void testInscriptionEtudiantKO() throws Exception {
//       ObjectMapper objectMapper = new ObjectMapper();
//
//       Etudiant etudiant = EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk());
//       given(etudiantService.inscription(any(Etudiant.class))).willReturn(etudiant);
//
//        this.mockMvc.perform(post("/api/emargement/etudiants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(etudiant)))
//                .andExpect(status().isCreated()).andExpect(header().exists("Location"));
//
//        //given(etudiantService.inscription(any(Etudiant.class))).willReturn(etudiant);
//        this.mockMvc.perform(post("/api/emargement/etudiants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(etudiant)))
//                .andExpect(status().isConflict());
//    }

   @Test
    public void testGetEtudiantsOK() throws Exception {

       EtudiantDTO etudiantDTO = new EtudiantDTO("fati.bardi1995@gmail.com","fatimaE","fatima","mdpss",
               "1987654",new Groupe("TP1","TD1"),
               new Promotion("MASTER 1","2021-2022"));
       EtudiantDTO etudiantDTO2 = new EtudiantDTO("Bfati.bardi1995@gmail.com","fatimaE","fatima","mdpss",
               "1987654",new Groupe("TP1","TD1"),
               new Promotion("MASTER 1","2021-2022"));
       List<Etudiant> etudiants = new ArrayList<>();
       etudiants.add(EtudiantDTO.toEntity(etudiantDTO));
       etudiants.add(EtudiantDTO.toEntity(etudiantDTO2));
       ObjectMapper objectMapper = new ObjectMapper();

       given(etudiantService.getEtudiants()).willReturn(etudiants);

       this.mockMvc.perform(get("/api/emargement/etudiants")
               .content(objectMapper.writeValueAsString(etudiants)))
               .andExpect(status().isOk())
               ;
   }

    @Test
    public void testGetEtudiantsKO() throws Exception {

        List<Etudiant> etudiants = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        given(etudiantService.getEtudiants()).willReturn(etudiants);

        this.mockMvc.perform(get("/api/emargement/etudiants")
                        .content(objectMapper.writeValueAsString(etudiants)))
                .andExpect(status().isNotFound())
        ;
    }

//    @Test
//    public void testGetEtudiantByEmailOK() throws Exception {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        given(etudiantService.getEtudiantbyEmail(anyString())).willReturn(EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()));
//
//        this.mockMvc.perform(get("/api/emargement/etudiants/"+EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()).getEmail())
//                        .content(objectMapper.writeValueAsString(EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()))))
//                .andExpect(status().isOk())
//        ;
//    }




}
