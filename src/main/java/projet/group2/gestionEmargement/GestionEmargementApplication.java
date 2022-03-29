package projet.group2.gestionEmargement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GestionEmargementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmargementApplication.class, args);
	}

}
