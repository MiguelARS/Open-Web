package pe.edu.upc.puffleshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.model.Producto;

@Repository
public interface ProductoRepository
extends JpaRepository<Producto, Integer>{
//   @Query("Select prod.categorias from Producto prod where prod.id = :id and prod.categorias.nombre like %:nombre%")
//   Optional<Categoria> fetchByProductoCategoria(int id, String nombre) throws Exception; 

}
