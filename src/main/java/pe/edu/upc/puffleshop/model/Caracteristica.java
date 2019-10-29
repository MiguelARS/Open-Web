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
import javax.persistence.Table;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@ManyToMany(mappedBy = "caracteristicas")
	public List<Producto> productos;

	@ManyToMany
	@JoinTable(name = "caracteristica_opciones", joinColumns = {
			@JoinColumn(name = "caracteristica_id") }, inverseJoinColumns = { @JoinColumn(name = "opcion_id") })
	public List<Opcion> opciones;

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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
