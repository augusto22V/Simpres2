	package py.edu.facitec.Simpres2.entidades;
	
	import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
import javax.persistence.OneToMany;
	
	@Entity
	public class Servicios {
		@Id
		@GeneratedValue
		private int id;
	
		@Column(nullable = false)
		private String descripcion;
	
		@Column(nullable = false)
		private double precio;
		 @OneToMany(mappedBy = "servicios", cascade = CascadeType.ALL)
		    private List<Reservas> reservas;
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getDescripcion() {
			return descripcion;
		}
	
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
	
		public double getPrecio() {
			return precio;
		}
	
		public void setPrecio(double precio) {
			this.precio = precio;
		}
	
	}
