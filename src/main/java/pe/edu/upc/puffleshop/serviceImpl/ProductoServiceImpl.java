package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.model.Producto;
import pe.edu.upc.puffleshop.repository.ProductoRepository;
import pe.edu.upc.puffleshop.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> findAll() throws Exception {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> findById(Integer id) throws Exception {
		return productoRepository.findById(id);
	}

	@Override
	public Producto save(Producto t) throws Exception {
		return productoRepository.save(t);
	}

	@Override
	public Producto update(Producto t) throws Exception {
		return productoRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		productoRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		productoRepository.deleteAll();
	}

//	@Override
//	public Optional<Categoria> fetchByProductoCategoria(int id, String nombre) throws Exception {
//		return productoRepository.fetchByProductoCategoria(id, nombre);
//	}
	
	
}
