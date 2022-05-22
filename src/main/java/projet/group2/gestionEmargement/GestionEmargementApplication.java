package projet.group2.gestionEmargement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import projet.group2.gestionEmargement.entity.*;
import projet.group2.gestionEmargement.repository.*;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class GestionEmargementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmargementApplication.class, args);
	}

	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private EnseignantRepository enseignantRepository;
	@Autowired
	private SecretaireRepository secretaireRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public void run(String... args) throws Exception {

		//Pour les tests fonctionnels seance
//		seanceRepository.deleteAll();
//		utilisateurRepository.deleteAll();
//
//		//Enregistrement des utilisateurs
//		etudiantRepository.save(new Etudiant("alice.dupont@etu.univ-orleans.fr", "dupont", "alice",
//				"mdp", List.of("ETUDIANT"), "000000001", new Groupe("TD1", "TP1"),
//				new Promotion("MASTER 1 MIAGE", "2021-2022")));
//		etudiantRepository.save(new Etudiant("bob.marley@etu.univ-orleans.fr", "marley", "bob",
//				"mdp", List.of("ETUDIANT"), "000000002", new Groupe("TD2", "TP2"),
//				new Promotion("MASTER 1 MIAGE", "2021-2022")));
//		enseignantRepository.save(new Enseignant("martin.delacourt@univ-orleans.fr", "delacourt", "martin",
//				"mdp", Arrays.asList("ENSEIGNANT", "MEMBRE_ADMINISTRATIF")));
//		enseignantRepository.save(new Enseignant("frederic.moalt@univ-orleans.fr", "moal", "frederic",
//				"mdp", List.of("ENSEIGNANT")));
//		secretaireRepository.save(new Secretaire("brigitte.dupuy@univ-orleans.fr", "dupuy", "brigitte",
//				"mdp", List.of("MEMBRE_ADMINISTRATIF")));

	}

}
