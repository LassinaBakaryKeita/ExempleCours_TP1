package ma.ensaf.springbootrestdemo.controller;

import ma.ensaf.springbootrestdemo.dao.AdresseRepository;
import ma.ensaf.springbootrestdemo.dao.PersonneRepository;
import ma.ensaf.springbootrestdemo.model.Adresse;
import ma.ensaf.springbootrestdemo.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/personnes")
public class PersonneRestController {

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    // 1. GET - Récupérer toutes les personnes (JSON ou XML selon l'en-tête Accept)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Personne> getPersonnes() {
        return personneRepository.findAll();
    }

    // 2. GET par ID - Récupérer une personne spécifique
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Personne getPersonne(@PathVariable("id") long id) {
        return personneRepository.findById(id).orElse(null);
    }

    // 3. POST - Ajouter une personne (gestion des adresses existantes et nouvelles)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Personne addPersonne(@RequestBody Personne personne) {
        System.out.println("Ajout de la personne : " + personne);
        List<Adresse> adresses = personne.getAdresses();
        if (adresses != null) {
            for (int i = 0; i < adresses.size(); i++) {
                Adresse adresse = adresses.get(i);
                Adresse adr;
                if (adresse.getId() != null) {
                    adr = adresseRepository.findById(adresse.getId()).orElse(null);
                    if (adr != null) {
                        adresses.set(i, adr);
                    }
                } else {
                    adr = adresseRepository.save(adresse);
                    adresses.set(i, adr);
                }
            }
        }
        return personneRepository.saveAndFlush(personne);
    }

    // 4. PUT - Modifier une personne existante (Exercice du cours)
    @PutMapping(value = "/{id}",
                consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Personne modifierPersonnes(@PathVariable("id") long id, @RequestBody Personne personne1) {
        return personneRepository.findById(id).map(personne -> {
            personne.setNom(personne1.getNom());
            personne.setPrenom(personne1.getPrenom());
            if (personne1.getAdresses() != null && !personne1.getAdresses().isEmpty()) {
                personne.setAdresses(personne1.getAdresses());
            }
            return personneRepository.save(personne);
        }).orElseGet(() -> {
            personne1.setNum(id);
            return personneRepository.save(personne1);
        });
    }

    // 5. DELETE - Supprimer une personne (Exercice du cours)
    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable("id") long id) {
        personneRepository.deleteById(id);
    }
}
