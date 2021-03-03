package entities;

import java.util.Date;

public class Estadia {

	private int idEstadia;
	private java.sql.Date fechaIngreso;
	private java.sql.Date fechaEgreso;
	private String estado;
	private int id_cliente;
	private int nro_habitacion;
	
	public int getIdEstadia() {
		return idEstadia;
	}
	public void setIdEstadia(int idEstadia) {
		this.idEstadia = idEstadia;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = (java.sql.Date) fechaIngreso;
	}
	public Date getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = (java.sql.Date) fechaEgreso;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getNro_habitacion() {
		return nro_habitacion;
	}
	public void setNro_habitacion(int nro_habitacion) {
		this.nro_habitacion = nro_habitacion;
	}
	
	
	
}
