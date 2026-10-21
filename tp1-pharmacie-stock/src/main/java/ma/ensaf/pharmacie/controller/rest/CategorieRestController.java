

import ma.ensaf.pharmacie.model.Categorie;
import ma.ensaf.pharmacie.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategorieRestController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        return categorieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        return new ResponseEntity<>(categorieRepository.save(categorie), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie details) {
        return categorieRepository.findById(id).map(cat -> {
            cat.setNom(details.getNom());
            cat.setDescription(details.getDescription());
            return ResponseEntity.ok(categorieRepository.save(cat));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        if (categorieRepository.existsById(id)) {
            categorieRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
