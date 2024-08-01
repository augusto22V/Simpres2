package py.edu.facitec.Simpres2.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reserva_detalle {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private double precio;
	@Column
	private int cantidad_dias;
	
	@ManyToOne
	private Habitacion habitacion;
	
	@ManyToOne
	private Reservas reservas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad_dias() {
		return cantidad_dias;
	}

	public void setCantidad_dias(int cantidad_dias) {
		this.cantidad_dias = cantidad_dias;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Reservas getReservas() {
		return reservas;
	}

	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}

}
