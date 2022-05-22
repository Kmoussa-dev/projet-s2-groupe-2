package projet.group2.gestionEmargement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.service.EtudiantService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
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

       given(etudiantService.inscription(any(EtudiantDTO.class))).willReturn(any(EtudiantDTO.class));

       ObjectMapper objectMapper = new ObjectMapper();
       this.mockMvc.perform(post("/api/emargement/etudiants")
               .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(EtudiantDTO.toEntity(dataEtudiantTest.inscriptionOk()))))
               .andExpectAll(status().isCreated())
               .andExpect(header().exists("Location"))
               .andDo(document("Creer-etudiants-201",
                       preprocessRequest(prettyPrint()),
                       preprocessResponse(prettyPrint())))
       ;
   }

//    @Test
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
//

    /**
     * get tout les étudiants
     * 200
     * @throws Exception
     */
  @Test
    public void testGetEtudiantsOK() throws Exception {

       EtudiantDTO etudiantDTO = new EtudiantDTO("fati.bardi1995@gmail.com","fatimaE","fatima","mdpss",
               "1987654",new Groupe("TP1","TD1"),
               new Promotion("MASTER 1","2021-2022"),false,"");
       EtudiantDTO etudiantDTO2 = new EtudiantDTO("Bfati.bardi1995@gmail.com","fatimaE","fatima","mdpss",
               "1987654",new Groupe("TP1","TD1"),
               new Promotion("MASTER 1","2021-2022"),false,"");
       List<EtudiantDTO> etudiants = new ArrayList<>();
       etudiants.add(etudiantDTO);
       etudiants.add(etudiantDTO2);
       ObjectMapper objectMapper = new ObjectMapper();

       given(etudiantService.getEtudiants()).willReturn(etudiants);

       this.mockMvc.perform(get("/api/emargement/etudiants")
               .content(objectMapper.writeValueAsString(etudiants)))
               .andExpect(status().isOk())
               .andDo(document("Get-etudiants-200",
                       preprocessRequest(prettyPrint()),
                       preprocessResponse(prettyPrint())))
               ;
   }

    /**
     * get tout les étudiants depuis une liste vide
     * 404 not found
     * @throws Exception
     */
    @Test
    public void testGetEtudiantsKO() throws Exception {

        List<EtudiantDTO> etudiants = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        given(etudiantService.getEtudiants()).willReturn(etudiants);

        this.mockMvc.perform(get("/api/emargement/etudiants")
                        .content(objectMapper.writeValueAsString(etudiants)))
                .andExpect(status().isNotFound())
                .andDo(document("Get-etudiants-404",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));

    }

    /**
     * test get etudiant par son email
     * 200
     * @throws Exception
     */
    @Test
    public void testGetEtudiantByEmailOK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();


        given(etudiantService.getEtudiantbyEmail(anyString())).willReturn(dataEtudiantTest.inscriptionOk());

        this.mockMvc.perform(get("/api/emargement/etudiants/"+dataEtudiantTest.emailEtudiant())
                        .content(objectMapper.writeValueAsString(dataEtudiantTest.inscriptionOk())))
                .andExpect(status().isOk())
                .andDo(document("Get-etudiantByEmail-200",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
        ;
    }

    /**
     * test get étudiant par son email KO
     * 404 étudiant not found
     * @throws Exception
     */
    @Test
    public void testGetEtudiantByEmailKO() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Etudiant etudiant = new Etudiant();
        this.mockMvc.perform(get("/api/emargement/etudiants/"+etudiant.getEmail())
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isNotFound())
                .andDo(document("Get-etudiantByEmail-404",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
        ;
    }

    /**
     * test get etudiant par son numeroEtudiant
     * 200
     * @throws Exception
     */
    @Test
    public void testGetEtudiantByNumeroEtudiantOK() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();


        given(etudiantService.getEtudiantbyNumeroEtudiant(anyString())).willReturn(dataEtudiantTest.inscriptionOk());

        this.mockMvc.perform(get("/api/emargement/etudiant/"+dataEtudiantTest.numeroEtudiant())
                        .content(objectMapper.writeValueAsString(dataEtudiantTest.inscriptionOk())))
                .andExpect(status().isOk())
                .andDo(document("Get-etudiantByNumeroEtudiant-200",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
        ;
    }

    /**
     * test get étudiant par son numeroEtudiant KO
     * 404 étudiant not found
     * @throws Exception
     */
    @Test
    public void testGetEtudiantByNumeroEtudiantKO() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Etudiant etudiant = new Etudiant();
        this.mockMvc.perform(get("/api/emargement/etudiant/"+etudiant.getNumeroEtudiant())
                        .content(objectMapper.writeValueAsString(etudiant)))
                .andExpect(status().isNotFound())
                .andDo(document("Get-etudiantByNumeroEtudiant-404",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
        ;
    }
}
