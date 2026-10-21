package ma.ensaf.springbootrestdemo.dao;

import ma.ensaf.springbootrestdemo.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
