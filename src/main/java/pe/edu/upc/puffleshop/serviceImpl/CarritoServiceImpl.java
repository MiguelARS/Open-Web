package pe.edu.upc.puffleshop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.puffleshop.model.Carrito;
import pe.edu.upc.puffleshop.repository.CarritoRepository;
import pe.edu.upc.puffleshop.service.CarritoService;
@Service
public class CarritoServiceImpl implements CarritoService{

	@Autowired
	private CarritoRepository carritoRepository;
	
	@Override
	public List<Carrito> findAll() throws Exception {
		// TODO Auto-generated method stub
		return carritoRepository.findAll();
	}

	@Override
	public Optional<Carrito> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return carritoRepository.findById(id);
	}

	@Override
	public Carrito save(Carrito t) throws Exception {
		// TODO Auto-generated method stub
		return carritoRepository.save(t);
	}

	@Override
	public Carrito update(Carrito t) throws Exception {
		// TODO Auto-generated method stub
		return carritoRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		carritoRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		carritoRepository.deleteAll();
	}

}
