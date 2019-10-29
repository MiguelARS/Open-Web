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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import pe.edu.upc.puffleshop.model.Producto;
import pe.edu.upc.puffleshop.model.Tienda;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;
import pe.edu.upc.puffleshop.service.TiendaService;
import pe.edu.upc.puffleshop.service.ProductoService;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "EndPoint de Tiendas")
@RestController
@RequestMapping("/api/tiendas")
public class tiendaRestController {
	
	@Autowired
	private TiendaService tiendaService;
	
	
	@ApiOperation(value = "Endpoint que permite listar las tiendas asociadas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tienda>> Listar(){
		ResponseEntity<List<Tienda>> response;
		try {
			List<Tienda> tiendas = tiendaService.findAll();
			response = new ResponseEntity<List<Tienda>>(tiendas,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			
		}
		return null;
	}
	@ApiOperation(value = "EndPoint que permite obtener las tiendas asociadas mediante su identificador")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tienda> GetTienda(@PathVariable("id") int id) {
		try {
			Optional<Tienda> tiendas = tiendaService.findById(id);
			if( tiendas.isPresent() ) {
				return new ResponseEntity<Tienda>(tiendas.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Tienda>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Tienda>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite agregar una nueva tienda asociada")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Tienda tienda, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Tienda nuevaTienda = tiendaService.save(tienda);
			return new ResponseEntity<Tienda>(nuevaTienda, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Tienda>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar los datos de los asociados")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tienda> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Tienda tienda) {
		try {
			if(id.equals(tienda.getId())) {
				Optional<Tienda> tiend = tiendaService.findById(id);
				if(tiend.isPresent()) {
					Tienda tiendaUpdate = tiendaService.save(tienda);
					return new ResponseEntity<Tienda>(tiendaUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Tienda>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Tienda>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Tienda>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value = "EndPoint que permite eliminar un socio")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Tienda> tienda = tiendaService.findById(id);
			if(tienda.isPresent()) {
				tiendaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
