package projet.group2.gestionEmargement.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.group2.gestionEmargement.dto.EtudiantDto;
import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.service.EtudiantService;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class EtudiantControleur {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/creerEtudiant")
    public Etudiant createStudent(@RequestBody Etudiant etudiant){
        return etudiantService.creerEtudiant(etudiant);
    }

    @GetMapping("/getById/{id}")
    public Etudiant getStudentById(@PathVariable String id){
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/all")
    public List<Etudiant> getAllStudents(){
        return etudiantService.getAllEtudiants();
    }

    @PutMapping("/update")
    public Etudiant updateStudent(@RequestBody Etudiant etudiant){
        return etudiantService.majEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        return etudiantService.supprimerEtudiant(id);
    }
}

