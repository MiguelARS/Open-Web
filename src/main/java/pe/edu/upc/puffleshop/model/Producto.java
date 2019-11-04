package pe.edu.upc.puffleshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio_envio")
	private Integer precio_envio;

	@Column(name = "precio_producto")
	private String precio_producto;

	@ManyToOne
	@JoinColumn(name = "tienda_id")
	private Tienda tienda;

	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<Producto_Categoria> producto_categoria;

	@OneToMany(mappedBy = "caracteristica", fetch = FetchType.LAZY)
	private List<Producto_Caracteristica> producto_caracteristica;

	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<Carrito> carritos;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio_envio() {
		return precio_envio;
	}

	public void setPrecio_envio(Integer precio_envio) {
		this.precio_envio = precio_envio;
	}

	public String getPrecio_producto() {
		return precio_producto;
	}

	public void setPrecio_producto(String precio_producto) {
		this.precio_producto = precio_producto;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<Producto_Categoria> getProducto_categoria() {
		return producto_categoria;
	}

	public void setProducto_categoria(List<Producto_Categoria> producto_categoria) {
		this.producto_categoria = producto_categoria;
	}

	public List<Producto_Caracteristica> getProducto_caracteristica() {
		return producto_caracteristica;
	}

	public void setProducto_caracteristica(List<Producto_Caracteristica> producto_caracteristica) {
		this.producto_caracteristica = producto_caracteristica;
	}

	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}
	
	
}
