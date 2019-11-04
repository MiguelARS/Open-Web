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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;


	@OneToMany(mappedBy = "caracteristica",fetch = FetchType.LAZY)
	private List<Opcion> opcion;


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


	public List<Opcion> getOpcion() {
		return opcion;
	}


	public void setOpcion(List<Opcion> opcion) {
		this.opcion = opcion;
	}
	
	
}
