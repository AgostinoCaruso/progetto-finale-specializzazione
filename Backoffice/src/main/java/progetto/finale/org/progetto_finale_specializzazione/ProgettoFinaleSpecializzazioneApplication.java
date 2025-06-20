package progetto.finale.org.progetto_finale_specializzazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProgettoFinaleSpecializzazioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoFinaleSpecializzazioneApplication.class, args);
	}

}
