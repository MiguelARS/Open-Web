package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Opcion;
import pe.edu.upc.puffleshop.repository.OpcionRepository;
import pe.edu.upc.puffleshop.service.OpcionService;
@Service
public class OpcionServiceImpl implements OpcionService{

	@Autowired
	private OpcionRepository opcionRepository;
	@Override
	public List<Opcion> findAll() throws Exception {
		// TODO Auto-generated method stub
		return opcionRepository.findAll();
	}

	@Override
	public Optional<Opcion> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return opcionRepository.findById(id);
	}

	@Override
	public Opcion save(Opcion t) throws Exception {
		// TODO Auto-generated method stub
		return opcionRepository.save(t);
	}

	@Override
	public Opcion update(Opcion t) throws Exception {
		// TODO Auto-generated method stub
		return opcionRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		opcionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		opcionRepository.deleteAll();
	}

}
