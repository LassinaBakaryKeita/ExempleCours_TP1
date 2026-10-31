package ma.ensaf.pharmacie.controller.rest;

import ma.ensaf.pharmacie.model.Categorie;
import ma.ensaf.pharmacie.model.DetailProduit;
import ma.ensaf.pharmacie.model.Fournisseur;
import ma.ensaf.pharmacie.model.Produit;
import ma.ensaf.pharmacie.model.Tag;
import ma.ensaf.pharmacie.repository.CategorieRepository;
import ma.ensaf.pharmacie.repository.FournisseurRepository;
import ma.ensaf.pharmacie.repository.ProduitRepository;
import ma.ensaf.pharmacie.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
@SuppressWarnings("null")
public class ProduitRestController {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;
    private final TagRepository tagRepository;

    public ProduitRestController(ProduitRepository produitRepository,
                                 CategorieRepository categorieRepository,
                                 FournisseurRepository fournisseurRepository,
                                 TagRepository tagRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.tagRepository = tagRepository;
    }

    // GET /api/produits -> lister les produits
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // GET /api/produits/{id} -> afficher un produit
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/produits -> ajouter un produit
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        // Associer Categorie existante si ID fourni
        if (produit.getCategorie() != null && produit.getCategorie().getId() != null) {
            Categorie cat = categorieRepository.findById(produit.getCategorie().getId()).orElse(null);
            produit.setCategorie(cat);
        }

        // Associer Fournisseur existant si ID fourni
        if (produit.getFournisseur() != null && produit.getFournisseur().getId() != null) {
            Fournisseur four = fournisseurRepository.findById(produit.getFournisseur().getId()).orElse(null);
            produit.setFournisseur(four);
        }

        // Traitement des Tags
        if (produit.getTags() != null && !produit.getTags().isEmpty()) {
            List<Tag> attachedTags = new ArrayList<>();
            for (Tag t : produit.getTags()) {
                if (t.getId() != null) {
                    tagRepository.findById(t.getId()).ifPresent(attachedTags::add);
                }
            }
            produit.setTags(attachedTags);
        }

        Produit saved = produitRepository.save(produit);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PUT /api/produits/{id} -> modifier un produit
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        return produitRepository.findById(id).map(produit -> {
            produit.setNom(produitDetails.getNom());
            produit.setDescription(produitDetails.getDescription());
            produit.setPrix(produitDetails.getPrix());
            produit.setCodeBarre(produitDetails.getCodeBarre());
            produit.setDateExpiration(produitDetails.getDateExpiration());

            if (produitDetails.getCategorie() != null && produitDetails.getCategorie().getId() != null) {
                categorieRepository.findById(produitDetails.getCategorie().getId()).ifPresent(produit::setCategorie);
            }

            if (produitDetails.getFournisseur() != null && produitDetails.getFournisseur().getId() != null) {
                fournisseurRepository.findById(produitDetails.getFournisseur().getId())
                        .ifPresent(produit::setFournisseur);
            }

            if (produitDetails.getDetailProduit() != null) {
                if (produit.getDetailProduit() != null) {
                    DetailProduit dp = produit.getDetailProduit();
                    dp.setFabricant(produitDetails.getDetailProduit().getFabricant());
                    dp.setPaysOrigine(produitDetails.getDetailProduit().getPaysOrigine());
                    dp.setComposition(produitDetails.getDetailProduit().getComposition());
                    dp.setDosage(produitDetails.getDetailProduit().getDosage());
                    dp.setIndications(produitDetails.getDetailProduit().getIndications());
                } else {
                    produit.setDetailProduit(produitDetails.getDetailProduit());
                }
            }

            if (produitDetails.getTags() != null) {
                List<Tag> attachedTags = new ArrayList<>();
                for (Tag t : produitDetails.getTags()) {
                    if (t.getId() != null) {
                        tagRepository.findById(t.getId()).ifPresent(attachedTags::add);
                    }
                }
                produit.setTags(attachedTags);
            }

            return ResponseEntity.ok(produitRepository.save(produit));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/produits/{id} -> supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
