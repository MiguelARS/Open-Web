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
import pe.edu.upc.puffleshop.model.Opcion;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;
import pe.edu.upc.puffleshop.service.OpcionService;
import pe.edu.upc.puffleshop.service.ProductoService;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "EndPoint de Opciones")
@RestController
@RequestMapping("/api/opciones")
public class OpcionRestController {

	@Autowired
	private OpcionService opcionService;
	
	@ApiOperation(value = "Endpoint que permite listar las opciones")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Opcion>> Listar(){
		ResponseEntity<List<Opcion>> response;
		try {
			List<Opcion> opciones = opcionService.findAll();
			response = new ResponseEntity<List<Opcion>>(opciones,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			
		}
		return null;
	}
	@ApiOperation(value = "EndPoint que permite obtener las opciones de los productos mediane")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Opcion> GetOpcion(@PathVariable("id") int id) {
		try {
			Optional<Opcion> opciones = opcionService.findById(id);
			if( opciones.isPresent() ) {
				return new ResponseEntity<Opcion>(opciones.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Opcion>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Opcion>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite agregar una nueva opcion")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Opcion opcion, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Opcion nuevaOpcion = opcionService.save(opcion);
			return new ResponseEntity<Opcion>(nuevaOpcion, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Opcion>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite actualizar las opciones mediante un identificador")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Opcion> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Opcion opcion) {
		try {
			if(id.equals(opcion.getId())) {
				Optional<Opcion> opc = opcionService.findById(id);
				if(opc.isPresent()) {
					Opcion OpcionUpdate = opcionService.save(opcion);
					return new ResponseEntity<Opcion>(OpcionUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Opcion>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Opcion>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Opcion>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value = "EndPoint que permite eliminar una opcion mediante un identificador")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Opcion> opcion = opcionService.findById(id);
			if(opcion.isPresent()) {
				opcionService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
