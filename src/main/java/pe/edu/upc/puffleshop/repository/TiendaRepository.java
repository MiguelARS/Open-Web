package pe.edu.upc.puffleshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.puffleshop.model.Tienda;

@Repository
public interface TiendaRepository
extends JpaRepository<Tienda, Integer> {

}
