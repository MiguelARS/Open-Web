package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Caracteristica;
import pe.edu.upc.puffleshop.repository.CaracteristicaRepository;
import pe.edu.upc.puffleshop.service.CaracteristicaService;
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService {

	@Autowired
	private CaracteristicaRepository caracteristicaRepository;

	@Override
	public List<Caracteristica> findAll() throws Exception {
		// TODO Auto-generated method stub
		return caracteristicaRepository.findAll();
	}

	@Override
	public Optional<Caracteristica> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return caracteristicaRepository.findById(id);
		}

	@Override
	public Caracteristica save(Caracteristica t) throws Exception {
		// TODO Auto-generated method stub
		return caracteristicaRepository.save(t);
	}

	@Override
	public Caracteristica update(Caracteristica t) throws Exception {
		// TODO Auto-generated method stub
		return caracteristicaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		caracteristicaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		caracteristicaRepository.deleteAll();
	}
	
	

}
