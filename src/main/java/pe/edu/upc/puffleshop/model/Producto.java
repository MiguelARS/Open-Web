package pe.edu.upc.puffleshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	@ManyToMany
	@JoinTable(name = "producto_caracteristica",
			joinColumns = {@JoinColumn(name = "producto_id")}, 
			inverseJoinColumns = {@JoinColumn(name = "caracteristica_id")})
	private List<Caracteristica> caracteristicas; 
	
	@ManyToOne
	@JoinColumn(name = "producto_id")//carrito_id
	private Carrito carrito; 
	
	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;
	
	@ManyToMany
	@JoinTable(name = "producto_categoria",
	joinColumns = {@JoinColumn(name = "producto_id")}, 
	inverseJoinColumns = {@JoinColumn(name = "categoria_id")})
	private List<Categoria> categorias;
	
	
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<Categoria> getCategoria() {
		return categorias;
	}

	public void setCategoria(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarritos(Carrito carrito) {
		this.carrito = carrito;
	}
}
