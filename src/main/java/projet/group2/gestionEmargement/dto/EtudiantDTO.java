package projet.group2.gestionEmargement.dto;


import projet.group2.gestionEmargement.entity.Etudiant;
import projet.group2.gestionEmargement.entity.Groupe;
import projet.group2.gestionEmargement.entity.Promotion;

import java.util.List;
import java.util.Objects;

public class EtudiantDTO extends UtilisateurDTO {


    private String numeroEtudiant;

    private Groupe groupe;

    private Promotion promo;

    public EtudiantDTO(String email, String nom, String prenom, String motDePasse, String numeroEtudiant, Groupe groupe, Promotion promo) {
        super(email, nom, prenom, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.groupe = groupe;
        this.promo = promo;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }


    public static Etudiant toEntity(EtudiantDTO etudiantDTO){
        if(Objects.isNull(etudiantDTO))
            return null;
        else {
            Etudiant etudiant = new Etudiant();
            etudiant.setEmail(etudiantDTO.getEmail());
            etudiant.setNom(etudiantDTO.getNom());
            etudiant.setPrenom(etudiantDTO.getPrenom());
            etudiant.setMotDePasse(etudiantDTO.getMotDePasse());
            etudiant.setRoles(List.of("ETUDIANT"));
            etudiant.setNumeroEtudiant(etudiantDTO.getNumeroEtudiant());
            etudiant.setGroupe(etudiantDTO.getGroupe());
            etudiant.setPromo(etudiant.getPromo());
            return etudiant;
        }
    }
}
