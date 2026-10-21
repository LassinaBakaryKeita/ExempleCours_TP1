
import ma.ensaf.pharmacie.model.*;
import ma.ensaf.pharmacie.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class PharmacieApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacieApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedData(
            CategorieRepository categorieRepository,
            FournisseurRepository fournisseurRepository,
            DetailProduitRepository detailProduitRepository,
            TagRepository tagRepository,
            ProduitRepository produitRepository) {
        return args -> {
            if (categorieRepository.count() == 0) {
                // 1. Catégories
                Categorie c1 = categorieRepository.save(new Categorie("Antibiotiques",
                        "Médicaments utilisés pour traiter les infections bactériennes."));
                Categorie c2 = categorieRepository
                        .save(new Categorie("Antalgiques", "Médicaments pour soulager la douleur."));
                Categorie c3 = categorieRepository
                        .save(new Categorie("Vitamines", "Compléments nutritionnels et fortifiants."));
                Categorie c4 = categorieRepository
                        .save(new Categorie("Antiseptiques", "Produits désinfectants pour usage externe."));

                // 2. Fournisseurs
                Fournisseur f1 = fournisseurRepository
                        .save(new Fournisseur("PharmaMaroc", "contact@pharmamaroc.ma", "Casablanca, Maroc"));
                Fournisseur f2 = fournisseurRepository
                        .save(new Fournisseur("Medisup France", "info@medisup.fr", "Paris, France"));
                Fournisseur f3 = fournisseurRepository
                        .save(new Fournisseur("Sandoz", "export@sandoz.com", "Bâle, Suisse"));
                Fournisseur f4 = fournisseurRepository
                        .save(new Fournisseur("Cooper Pharma", "support@cooperpharma.ma", "Rabat, Maroc"));

                // 3. Details Produits (laissés transitoires, persistés en cascade avec Produit)
                DetailProduit dp1 = new DetailProduit("Sanofi", "France", "Amoxicilline trihydratée 500 mg", "500 mg",
                        "Infections ORL et respiratoires");
                DetailProduit dp2 = new DetailProduit("Bayer", "Allemagne", "Paracétamol 500mg", "500 mg",
                        "Douleurs légères à modérées, fièvre");
                DetailProduit dp3 = new DetailProduit("Pfizer", "États-Unis", "Vitamine C 1000 mg", "1000 mg",
                        "Renforcement immunitaire");
                DetailProduit dp4 = new DetailProduit("Johnson & Johnson", "Maroc", "Povidone iodée 10%", "10%",
                        "Désinfection des plaies");
                DetailProduit dp5 = new DetailProduit("GSK", "Royaume-Uni", "Ibuprofène 400 mg", "400 mg",
                        "Inflammations et douleurs musculaires");

                // 4. Tags
                Tag t1 = tagRepository.save(new Tag("Sans ordonnance"));
                Tag t2 = tagRepository.save(new Tag("Prescription obligatoire"));
                Tag t3 = tagRepository.save(new Tag("En promotion"));
                Tag t4 = tagRepository.save(new Tag("Stock limité"));
                Tag t5 = tagRepository.save(new Tag("Importé"));

                // 5. Produits
                Produit p1 = new Produit();
                p1.setNom("Amoxicilline 500mg");
                p1.setDescription("Antibiotique à large spectre.");
                p1.setPrix(new BigDecimal("45.00"));
                p1.setCodeBarre("AMX500-2025");
                p1.setDateExpiration(LocalDate.of(2026, 3, 10));
                p1.setCategorie(c1);
                p1.setFournisseur(f1);
                p1.setDetailProduit(dp1);
                p1.getTags().add(t2);
                produitRepository.save(p1);

                Produit p2 = new Produit();
                p2.setNom("Doliprane 500mg");
                p2.setDescription("Antalgique et antipyrétique.");
                p2.setPrix(new BigDecimal("25.50"));
                p2.setCodeBarre("DLP500-2025");
                p2.setDateExpiration(LocalDate.of(2025, 11, 1));
                p2.setCategorie(c2);
                p2.setFournisseur(f2);
                p2.setDetailProduit(dp2);
                p2.getTags().addAll(Arrays.asList(t1, t3));
                produitRepository.save(p2);

                Produit p3 = new Produit();
                p3.setNom("Vitamine C 1000mg");
                p3.setDescription("Complément pour renforcer les défenses.");
                p3.setPrix(new BigDecimal("30.00"));
                p3.setCodeBarre("VITC1000-2025");
                p3.setDateExpiration(LocalDate.of(2026, 6, 15));
                p3.setCategorie(c3);
                p3.setFournisseur(f3);
                p3.setDetailProduit(dp3);
                p3.getTags().addAll(Arrays.asList(t1, t5));
                produitRepository.save(p3);

                Produit p4 = new Produit();
                p4.setNom("Bétadine Solution 10%");
                p4.setDescription("Désinfectant pour usage externe.");
                p4.setPrix(new BigDecimal("18.00"));
                p4.setCodeBarre("BETADINE10-2025");
                p4.setDateExpiration(LocalDate.of(2027, 1, 30));
                p4.setCategorie(c4);
                p4.setFournisseur(f4);
                p4.setDetailProduit(dp4);
                p4.getTags().addAll(Arrays.asList(t1, t4));
                produitRepository.save(p4);

                Produit p5 = new Produit();
                p5.setNom("Nurofen 400mg");
                p5.setDescription("Soulage la douleur et la fièvre.");
                p5.setPrix(new BigDecimal("42.00"));
                p5.setCodeBarre("NURO400-2025");
                p5.setDateExpiration(LocalDate.of(2026, 2, 20));
                p5.setCategorie(c2);
                p5.setFournisseur(f3);
                p5.setDetailProduit(dp5);
                p5.getTags().addAll(Arrays.asList(t1, t5));
                produitRepository.save(p5);

                System.out.println(">>> Données de test du TP1 insérées avec succès !");
            }
        };
    }
}
