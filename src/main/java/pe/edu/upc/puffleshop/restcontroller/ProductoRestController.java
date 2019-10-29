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

import io.swagger.annotations.ApiOperation;
import pe.edu.upc.puffleshop.validator.ErrorMessageBuilder;
import pe.edu.upc.puffleshop.model.Categoria;
import pe.edu.upc.puffleshop.model.Producto;
import pe.edu.upc.puffleshop.model.Tienda;
import pe.edu.upc.puffleshop.service.CategoriaService;
import pe.edu.upc.puffleshop.service.ProductoService;
import pe.edu.upc.puffleshop.service.TiendaService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
	
	@Autowired
	private ProductoService productoService;

	@Autowired
	private TiendaService tiendaService;
	
	@ApiOperation(value = "EndPoint que permite listar todos los productos en PuffleShop")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Producto> > Listar() {
		ResponseEntity< List<Producto> > response;
		
		try {
			List<Producto> productos = productoService.findAll();
			response = new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
			return response;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@ApiOperation(value = "EndPoint que permite obtener los productos mediante su identificador")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> GetProducto(@PathVariable("id") int id) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if( producto.isPresent() ) {
				return new ResponseEntity<Producto>(producto.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "EndPoint que permite agregar un nuevo producto")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevo(@Valid @RequestBody Producto producto, 
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Producto nuevoProducto = productoService.save(producto);
			return new ResponseEntity<Producto>(nuevoProducto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar los datos de los productos mediante su indentificador")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> actualizar(@PathVariable("id") Integer id, 
			@RequestBody Producto producto) {
		try {
			if(id.equals(producto.getId())) {
				Optional<Producto> prod = productoService.findById(id);
				if(prod.isPresent()) {
					Producto productoUpdate = productoService.update(producto);
					return new ResponseEntity<Producto>(productoUpdate, HttpStatus.OK);
				} 
				else {
					return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
				}	
			} else {
				return new ResponseEntity<Producto>(HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	/**/
	@ApiOperation(value = "EndPoint que permite asignar un producto a una tienda, donde id es el identificador de la tienda" )
	@PostMapping(path = "/{id}/producto", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevoProducto(@PathVariable("id") Integer id,
			@Valid @RequestBody Producto producto, Errors errors) {
		
		try {
			Optional<Producto> buscado 
				= productoService.findById(producto.getId());
			if(buscado.isPresent() ) {
				return new ResponseEntity<Producto>(producto,HttpStatus.CREATED);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// Error del Entity
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest()
					.body( ErrorMessageBuilder.build(errors) );
		}
		try {
			Optional<Tienda> tienda = tiendaService.findById(id);
			if(tienda.isPresent()) {
				producto.setTienda(tienda.get());
				Producto nuevo = productoService.save(producto);
				
				return new ResponseEntity<Producto>(nuevo, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**/
	@ApiOperation(value = "EndPoint que permite eliminar productos mediante su identificador")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if(producto.isPresent()) {
				productoService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
