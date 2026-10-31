package ma.ensaf.pharmacie.repository;

import ma.ensaf.pharmacie.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
