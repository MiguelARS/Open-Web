package pe.edu.upc.puffleshop.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;
import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.model.Comprador;
import pe.edu.upc.puffleshop.model.Producto;
import pe.edu.upc.puffleshop.model.Tienda;
import pe.edu.upc.puffleshop.service.CategoriaService;
import pe.edu.upc.puffleshop.service.CompradorService;
import pe.edu.upc.puffleshop.service.ProductoService;


@Api(value = "EndPoint de Compradores")
@RestController
@RequestMapping("/api/compradores")
public class CompradorRestController {

	@Autowired 
	private CompradorService compradorService;
	
	@ApiOperation(value = "Endpoint que permite listar los clientes")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comprador>> Listar(){
		ResponseEntity<List<Comprador>> response;
		try {
			List<Comprador> compradores = compradorService.findAll();
			response = new ResponseEntity<List<Comprador>>(compradores,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@ApiOperation(value = "EndPoint que permite obtener los clientes mediante su identificador")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comprador> GetComprador(@PathVariable("id") int id) {
		try {
			Optional<Comprador> compradores = compradorService.findById(id);
			if( compradores.isPresent() ) {
				return new ResponseEntity<Comprador>(compradores.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Comprador>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Comprador>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite agregar un nuevo cliente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Comprador comprador, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Comprador nuevoComprador = compradorService.save(comprador);
			return new ResponseEntity<Comprador>(nuevoComprador, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Comprador>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite actualizar los datos de los clientes mediante su identificador")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comprador> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Comprador comprador) {
		try {
			if(id.equals(comprador.getId())) {
				Optional<Comprador> comp = compradorService.findById(id);
				if(comp.isPresent()) {
					Comprador compradorUpdate = compradorService.update(comprador);
					return new ResponseEntity<Comprador>(compradorUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Comprador>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Comprador>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Comprador>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value = "EndPoint que permite eliminar el registro de un cliente mediante su identificador")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Comprador> comprador = compradorService.findById(id);
			if(comprador.isPresent()) {
				compradorService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
