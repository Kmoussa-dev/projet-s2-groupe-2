package projet.group2.gestionEmargement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projet.group2.gestionEmargement.dataTest.DataEnseignantTest;
import projet.group2.gestionEmargement.dataTest.DataEtudiantTest;
import projet.group2.gestionEmargement.dto.EnseignantDTO;
import projet.group2.gestionEmargement.dto.EtudiantDTO;
import projet.group2.gestionEmargement.entity.Enseignant;
import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.repository.EtudiantRepository;
import projet.group2.gestionEmargement.service.EnseignantService;
import projet.group2.gestionEmargement.service.EtudiantService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
////public class EnseignantControllerTest {
////
////    @Autowired
////    //private DataEnseignantTest dataEnseignantTest ;
////    @MockBean
////    private EnseignantService enseignantService;
////
////    @MockBean
////    private EtudiantRepository repository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public  void init() {
//        //this.etudiantService = new EtudiantService();
//        MockitoAnnotations.initMocks(this);
//    }
//
//    /**
//     * test inscription d'un enseignant
//     * @throws Exception
//     * 201
//     */
//    @Test
//    public void testInscriptionEtudiantOk() throws Exception {
//
//        given(enseignantService.inscription(any(Enseignant.class))).willReturn(EnseignantDTO.toEntity(dataEnseignantTest.inscriptionOk()));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        this.mockMvc.perform(post("/api/emargement/enseignants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(EnseignantDTO.toEntity(dataEnseignantTest.inscriptionOk()))))
//                .andExpectAll(status().isCreated())
//                .andExpect(header().exists("Location"))
//                .andDo(document("Creer-enseignant-201",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())))
//        ;
//    }
//}
