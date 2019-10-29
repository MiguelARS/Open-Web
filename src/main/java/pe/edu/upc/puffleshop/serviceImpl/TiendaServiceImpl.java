package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Tienda;
import pe.edu.upc.puffleshop.repository.TiendaRepository;
import pe.edu.upc.puffleshop.service.TiendaService;

@Service
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	private TiendaRepository tiendaRepository;
	
	@Override
	public List<Tienda> findAll() throws Exception {
		// TODO Auto-generated method stub
		return tiendaRepository.findAll();
	}

	@Override
	public Optional<Tienda> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return tiendaRepository.findById(id);
	}

	@Override
	public Tienda save(Tienda t) throws Exception {
		// TODO Auto-generated method stub
		return tiendaRepository.save(t);
	}

	@Override
	public Tienda update(Tienda t) throws Exception {
		// TODO Auto-generated method stub
		return tiendaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		tiendaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		tiendaRepository.deleteAll();
	}

}
