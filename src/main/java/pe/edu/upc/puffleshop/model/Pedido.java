package pe.edu.upc.puffleshop.model;

import java.util.Date;
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
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fecha_pedido", length = 50, nullable = false)
	private Date fecha_pedido;
	
	@Column(name = "fecha_entrega", length = 50, nullable = false)
	private Date fecha_entrega;
	
	@Column(name="estado")
	private Boolean estado; 
	
	@Column(name = "subtotal")
	private int subtotal; 
	
	@Column(name = "precio_envio")
	private int precio_envio; 
	
	@Column(name = "tipo_pago")
	private int tipo_pago; 
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<Carrito> carrito;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getPrecio_envio() {
		return precio_envio;
	}

	public void setPrecio_envio(int precio_envio) {
		this.precio_envio = precio_envio;
	}

	public int getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(int tipo_pago) {
		this.tipo_pago = tipo_pago;
	}

	public List<Carrito> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Carrito> carrito) {
		this.carrito = carrito;
	}
	
	
}
