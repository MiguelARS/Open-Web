package pe.edu.upc.puffleshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "opciones")
public class Opcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id; 
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@ManyToMany (mappedBy = "opciones" )
	@Column(name = "caracteristicas", length = 50, nullable = false)
	public List<Caracteristica> caracteristicas;
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
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

}
