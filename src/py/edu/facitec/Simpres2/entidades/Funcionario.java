package py.edu.facitec.Simpres2.entidades;

import java.util.Date; 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funcionario {
@Id
@GeneratedValue
@Column(name="Id_fc")
private int id;
@Column
private String nombre;
@Column(nullable=false)
private String apellido;
@Column(nullable=false, unique=true)
private String documento;

private String telefono;
private String correo;
private String direccion;
private String sexo;
private Date fecha_nacimiento;
private String estado;
@OneToMany(mappedBy="funcionario")
private List<Reservas>reservas;
public List<Reservas> getReservas() {
	return reservas;
}
public void setReservas(List<Reservas> reservas) {
	this.reservas = reservas;
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getDocumento() {
	return documento;
}
public void setDocumento(String documento) {
	this.documento = documento;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getSexo() {
	return sexo;
}
public void setSexo(String sexo) {
	this.sexo = sexo;
}
public Date getFecha_nacimiento() {
	return fecha_nacimiento;
}
public void setFecha_nacimiento(Date fecha_nacimiento) {
	this.fecha_nacimiento = fecha_nacimiento;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}



}

