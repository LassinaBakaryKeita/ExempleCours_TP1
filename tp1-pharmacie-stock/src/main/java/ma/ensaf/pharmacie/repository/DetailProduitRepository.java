package ma.ensaf.pharmacie.repository;

import ma.ensaf.pharmacie.model.DetailProduit;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {
}
