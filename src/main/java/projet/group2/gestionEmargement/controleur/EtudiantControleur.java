package projet.group2.gestionEmargement.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.group2.gestionEmargement.dto.EtudiantDto;
import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.service.EtudiantService;

@RestController
@RequestMapping(name = "/api")
public class EtudiantControleur {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping ("/etudiant")
    ResponseEntity<EtudiantDto> inscriptionEtudiant(@RequestBody EtudiantDto etudiantDto) {
        return etudiantService.inscriptionEtudiant();

    }


}
