package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.repository.CategoríaRepository;
import pe.edu.upc.puffleshop.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoríaRepository categoriaRepository;

	@Override
	public List<Categoria> findAll() throws Exception {
		return categoriaRepository.findAll();
	}

	@Override
	public Optional<Categoria> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id);
	}

	@Override
	public Categoria save(Categoria t) throws Exception {
		return categoriaRepository.save(t);
	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		return categoriaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		categoriaRepository.deleteById(id);
		
	}

	@Override
	public void deleteAll() throws Exception {
		categoriaRepository.deleteAll();
	}

	@Override
	public Optional<Categoria> findByNombre(String nombre) throws Exception {
		return categoriaRepository.findByNombre(nombre);
	}
	
}

