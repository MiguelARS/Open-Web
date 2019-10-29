package pe.edu.upc.puffleshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.puffleshop.model.Carrito;

@Repository
public interface CarritoRepository 
extends JpaRepository<Carrito, Integer>{

}
