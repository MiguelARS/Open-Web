package pe.edu.upc.puffleshop.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")	
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Producto_Categoria> producto_categoria;

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

	public List<Producto_Categoria> getProducto_categoria() {
		return producto_categoria;
	}

	public void setProducto_categoria(List<Producto_Categoria> producto_categoria) {
		this.producto_categoria = producto_categoria;
	}
	
	
}

