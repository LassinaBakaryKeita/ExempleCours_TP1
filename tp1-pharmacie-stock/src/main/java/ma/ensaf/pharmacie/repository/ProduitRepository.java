
package ma.ensaf.pharmacie.repository;

import ma.ensaf.pharmacie.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
