package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Comprador;
import pe.edu.upc.puffleshop.repository.CompradorRepository;
import pe.edu.upc.puffleshop.service.CompradorService;
@Service
public class CompradorServiceImpl implements CompradorService{

	@Autowired
	private CompradorRepository compradorRepository;
	
	@Override
	public List<Comprador> findAll() throws Exception {
		// TODO Auto-generated method stub
		return compradorRepository.findAll();
	}

	@Override
	public Optional<Comprador> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return compradorRepository.findById(id);
	}

	@Override
	public Comprador save(Comprador t) throws Exception {
		// TODO Auto-generated method stub
		return compradorRepository.save(t);
	}

	@Override
	public Comprador update(Comprador t) throws Exception {
		// TODO Auto-generated method stub
		return compradorRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		compradorRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		compradorRepository.deleteAll();
	}

}
