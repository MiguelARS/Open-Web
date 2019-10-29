package pe.edu.upc.puffleshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.puffleshop.model.Categoria;

@Repository
public interface CategoríaRepository extends JpaRepository<Categoria, Integer> {
	Optional<Categoria> findByNombre( String nombre);

}
