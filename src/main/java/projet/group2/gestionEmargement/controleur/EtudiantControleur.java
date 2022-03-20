package projet.group2.gestionEmargement.controleur;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import projet.group2.gestionEmargement.entite.Etudiant;
import projet.group2.gestionEmargement.service.EtudiantService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtudiantControleur {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/creerEtudiant")
    public Etudiant creerEtudiant(@RequestBody Etudiant etudiant){
        return etudiantService.creerEtudiant(etudiant);
    }

    @GetMapping("/getById/{id}")
    public Etudiant getEtudiantById(@PathVariable String id){
        return etudiantService.getEtudiantById(id);
    }

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiants(){
        return etudiantService.getAllEtudiants();
    }

    @PutMapping("/update")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant){
        return etudiantService.majEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable String id){
        return etudiantService.supprimerEtudiant(id);
    }
}

