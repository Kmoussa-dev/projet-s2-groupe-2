package projet.group2.gestionEmargement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.service.UtilisateurService;


import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtililisateurControleur {

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping("/creerUtilisateur")
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.creerUtilisateur(utilisateur);
    }

    @GetMapping("/etudiant/{id}")
    public Utilisateur getEtudiantById(@PathVariable String id){
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/all")
    public List<Utilisateur> getAllUtilisateurs(){
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/etudiants")
    public List<Utilisateur> getAllEtudiants(){
        return utilisateurService.getEtudiants();
    }

    @GetMapping("/membreAdministratifs")
    public List<Utilisateur> getAllMembreAdministratifs(){
        return utilisateurService.getMembreAdministratifs();
    }

    @GetMapping("/enseignants")
    public List<Utilisateur> getAllEnseignants(){
        return utilisateurService.getEnseignants();
    }

//    @GetMapping("/groupe")
//    public List<Utilisateur> getEtudiantByGroupe(Groupe groupe) {
//        return utilisateurService.getUtilisateurByGroupe(groupe);
//    }



    @GetMapping("/promotion/groupe/td/{td}")
    public List<Utilisateur> getEtudiantsByGroupeTd(@PathVariable("td") String td) {
        return utilisateurService.getEtudiantsByGroupeTD(td);
    }

    @GetMapping("/promotion/groupe/tp/{tp}")
    public List<Utilisateur> getEtudiantsByGroupeTP(@PathVariable("tp") String tp) {
        return utilisateurService.getEtudiantsByGroupeTP(tp);
    }

    @GetMapping("/promotion/{niveau}")
    public List<Utilisateur> getUtilisateurByPromoNiveau(@PathVariable("niveau") String niveau) {
        return utilisateurService.getUtilisateurByPromoNiveau(niveau);
    }

    @GetMapping("/promotion/{niveau}/{annee}")
    public List<Utilisateur> getUtilisateurByPromoNiveauAndAnnee(@PathVariable("niveau") String niveau,@PathVariable("annee") String annee) {
        return utilisateurService.getUtilisateurByPromoNiveauAndAnnee(niveau,annee);
    }

    @PutMapping("/update")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.majUtilisateur(utilisateur);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUtilisateur(@PathVariable String id){
        return utilisateurService.supprimerUtilisateur(id);
    }
}

