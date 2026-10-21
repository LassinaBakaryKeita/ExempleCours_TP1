package ma.ensaf.springbootrestdemo;

import ma.ensaf.springbootrestdemo.dao.AdresseRepository;
import ma.ensaf.springbootrestdemo.dao.PersonneRepository;
import ma.ensaf.springbootrestdemo.model.Adresse;
import ma.ensaf.springbootrestdemo.model.Personne;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringbootRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PersonneRepository personneRepository, AdresseRepository adresseRepository) {
        return args -> {
            // Initialisation de quelques adresses de test
            Adresse a1 = new Adresse();
            a1.setRue("Trik ain chkaf");
            a1.setVille("Fès");
            a1.setCodePostal("30000");

            Adresse a2 = new Adresse();
            a2.setRue("Atlas");
            a2.setVille("Fès");
            a2.setCodePostal("30000");

            adresseRepository.save(a1);
            adresseRepository.save(a2);

            // Initialisation de personnes de test
            Personne p1 = new Personne();
            p1.setNom("Khamlichi");
            p1.setPrenom("Youness");
            p1.getAdresses().add(a1);

            Personne p2 = new Personne();
            p2.setNom("Bennani");
            p2.setPrenom("Saad");

            personneRepository.save(p1);
            personneRepository.save(p2);
        };
    }
}
