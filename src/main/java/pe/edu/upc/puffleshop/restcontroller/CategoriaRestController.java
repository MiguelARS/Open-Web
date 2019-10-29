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
import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.service.CategoriaService;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;

@Api(value = "EndPoint de Categorias")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Endpoint que permite listar las categorias asociadas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> Listar(){
		ResponseEntity<List<Categoria>> response;
		try {
			List<Categoria> categorias = categoriaService.findAll();
			response = new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
			return response;
		} catch (Exception e) {
			
		}
		return null;
	}
	@ApiOperation(value = "EndPoint que permite obtener las categorias asociadas mediante su identificador")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> GetCategoria(@PathVariable("id") int id) {
		try {
			Optional<Categoria> categorias = categoriaService.findById(id);
			if( categorias.isPresent() ) {
				return new ResponseEntity<Categoria>(categorias.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite agregar una nueva categoria")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Categoria categoria, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Categoria nuevaCategoria = categoriaService.save(categoria);
			return new ResponseEntity<Categoria>(nuevaCategoria, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite actualizar las categorias de los productos")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Categoria categoria) {
		try {
			if(id.equals(categoria.getId())) {
				Optional<Categoria> cat = categoriaService.findById(id);
				if(cat.isPresent()) {
					Categoria categoriaUpdate = categoriaService.save(categoria);
					return new ResponseEntity<Categoria>(categoriaUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value = "EndPoint que permite eliminar una categoria")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				categoriaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
