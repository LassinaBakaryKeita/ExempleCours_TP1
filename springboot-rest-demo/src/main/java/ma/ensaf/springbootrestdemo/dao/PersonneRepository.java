package ma.ensaf.springbootrestdemo.dao;

import ma.ensaf.springbootrestdemo.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
