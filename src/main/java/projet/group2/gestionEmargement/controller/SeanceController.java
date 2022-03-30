package projet.group2.gestionEmargement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.group2.gestionEmargement.entity.Seance;
import projet.group2.gestionEmargement.exception.CreationImpossibleDeSeanceException;
import projet.group2.gestionEmargement.service.SeanceService;

import java.util.List;

@RestController
@RequestMapping("/api/seance")
public class SeanceController {
//    @Autowired
//    SeanceService seanceService;

//    @PostMapping("/creerSeance")
//    public Seance creerSeance(@RequestBody Seance seance) throws CreationImpossibleDeSeanceException {
//        return seanceService.creerSeance(seance);
//    }
//
//    @GetMapping("/getById/{id}")
//    public Seance getSeanceById(@PathVariable String id){
//        return seanceService.getSeanceById(id);
//    }
//
//    @GetMapping("/all")
//    public List<Seance> getAllSeances(){
//        return seanceService.getAllSeances();
//    }
//
//    @PutMapping("/update")
//    public Seance updateSeance(@RequestBody Seance seance){
//        return seanceService.majSeance(seance);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteSeance(@PathVariable String id){
//        return seanceService.supprimerSeance(id);
//    }
}
