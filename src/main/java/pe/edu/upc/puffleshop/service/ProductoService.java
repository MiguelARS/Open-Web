package pe.edu.upc.puffleshop.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.model.Producto;

public interface ProductoService 
extends CrudService<Producto,Integer> {
//	Optional<Categoria> fetchByProductoCategoria(int id, String nombre) throws Exception;
}
