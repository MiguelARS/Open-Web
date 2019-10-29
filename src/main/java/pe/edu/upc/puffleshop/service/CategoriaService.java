package pe.edu.upc.puffleshop.service;

import java.util.Optional;

import pe.edu.upc.puffleshop.model.Categoria;

public interface CategoriaService 
extends CrudService<Categoria,Integer>  {

	Optional<Categoria> findByNombre( String nombre ) throws Exception;

}
