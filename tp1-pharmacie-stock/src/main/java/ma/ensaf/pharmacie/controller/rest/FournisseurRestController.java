
import ma.ensaf.pharmacie.model.Fournisseur;
import ma.ensaf.pharmacie.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@CrossOrigin(origins = "*")
public class FournisseurRestController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        return fournisseurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurRepository.save(fournisseur), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur details) {
        return fournisseurRepository.findById(id).map(four -> {
            four.setNom(details.getNom());
            four.setContact(details.getContact());
            four.setAdresse(details.getAdresse());
            return ResponseEntity.ok(fournisseurRepository.save(four));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        if (fournisseurRepository.existsById(id)) {
            fournisseurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
