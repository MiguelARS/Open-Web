package pe.edu.upc.puffleshop.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
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
import pe.edu.upc.puffleshop.model.Caracteristica;
import pe.edu.upc.puffleshop.service.CaracteristicaService;
import pe.edu.upc.puffleshop.service.CaracteristicaService;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;
@Api(value = "EndPoint de Caracteristicas")
@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicaRestController {

	@Autowired
	private CaracteristicaService caracteristicaService;
	
	@ApiOperation(value = "Endpoint que permite listar las caracteristicas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Caracteristica>> Listar(){
		ResponseEntity<List<Caracteristica>> response;
		try {
			List<Caracteristica> caracteristicas = caracteristicaService.findAll();
			response = new ResponseEntity<List<Caracteristica>>(caracteristicas,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			
		}
		return null;
	}
	/**/
	@ApiOperation(value = "EndPoint que permite obtener las caracteristicas asociadas mediante su identificador")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Caracteristica> GetCaracteristica(@PathVariable("id") int id) {
		try {
			Optional<Caracteristica> caracteristicas = caracteristicaService.findById(id);
			if( caracteristicas.isPresent() ) {
				return new ResponseEntity<Caracteristica>(caracteristicas.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Caracteristica>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Caracteristica>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite agregar una nueva caracteristica")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Caracteristica caracteristica, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Caracteristica nuevaCaracteristica = caracteristicaService.save(caracteristica);
			return new ResponseEntity<Caracteristica>(nuevaCaracteristica, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Caracteristica>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite actualizar las caracteristicas de los productos")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Caracteristica> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Caracteristica caracteristica) {
		try {
			if(id.equals(caracteristica.getId())) {
				Optional<Caracteristica> carac = caracteristicaService.findById(id);
				if(carac.isPresent()) {
					Caracteristica caracteristicaUpdate = caracteristicaService.save(caracteristica);
					return new ResponseEntity<Caracteristica>(caracteristicaUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Caracteristica>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Caracteristica>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Caracteristica>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value = "EndPoint que permite eliminar una caracteristica")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Caracteristica> caracteristica = caracteristicaService.findById(id);
			if(caracteristica.isPresent()) {
				caracteristicaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
