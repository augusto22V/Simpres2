package py.edu.facitec.Simpres2.entidades;

import java.util.List; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Habitacion {
	@Id
	@GeneratedValue
	@Column(name = "Id_hab")
	private int id;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private double precio;
	@Column(nullable = false)
	private String observaciones;
	@Column(nullable = false)	
	private String estado;
	
	@OneToMany(mappedBy = "habitacion")
	private List<Reserva_detalle> detalles;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Reserva_detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Reserva_detalle> detalles) {
		this.detalles = detalles;
	}

	
}
