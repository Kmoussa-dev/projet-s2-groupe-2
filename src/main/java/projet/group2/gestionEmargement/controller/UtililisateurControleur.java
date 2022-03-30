package projet.group2.gestionEmargement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.service.UtilisateurService;


import java.util.List;

@RestController
@RequestMapping("/api/utitlisateur")
public class UtililisateurControleur {

//    @Autowired
//    UtilisateurService utilisateurService;
//
//
//    @PostMapping("/creerUtilisateur")
//    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur){
//        return utilisateurService.creerUtilisateur(utilisateur);
//    }
//
//    @GetMapping("/getById/{id}")
//    public Utilisateur getEtudiantById(@PathVariable String id){
//        return utilisateurService.getUtilisateurById(id);
//    }
//
//    @GetMapping("/allUtilisateurs")
//    public List<Utilisateur> getAllUtilisateurs(){
//        return utilisateurService.getAllUtilisateurs();
//    }
//
//    @GetMapping("/allEtudiants")
//    public List<Utilisateur> getAllEtudiants(){
//        return utilisateurService.getEtudiants();
//    }
//
//    @GetMapping("/allMembreAdministratifs")
//    public List<Utilisateur> getAllMembreAdministratifs(){
//        return utilisateurService.getMembreAdministratifs();
//    }
//
//    @GetMapping("/allEnseignants")
//    public List<Utilisateur> getAllEnseignants(){
//        return utilisateurService.getEnseignants();
//    }
//
//    @PutMapping("/update")
//    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur){
//        return utilisateurService.majUtilisateur(utilisateur);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteUtilisateur(@PathVariable String id){
//        return utilisateurService.supprimerUtilisateur(id);
//    }
}

