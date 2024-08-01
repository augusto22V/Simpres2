package py.edu.facitec.Simpres2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservas {
	@Id
	@GeneratedValue
	@Column(name = "Id_rs")
	private int id;
	@Column
	private Date fecha_ingreso;
	@Column
	private Date fecha_salida;
	@Column
	private int cantida_personas;
	@Column
	private double costo_total;
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "reservas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Reserva_detalle> detalles;

	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	private Servicios servicios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public int getCantida_personas() {
		return cantida_personas;
	}

	public void setCantida_personas(int cantida_personas) {
		this.cantida_personas = cantida_personas;
	}

	public double getCosto_total() {
		return costo_total;
	}

	public void setCosto_total(double costo_total) {
		this.costo_total = costo_total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Reserva_detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Reserva_detalle> detalles) {
		this.detalles = detalles;
	}

	public Servicios getServicios() {
		return servicios;
	}

	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}

	

}
