package entities;

public class Tipo_Habitacion {
	private int id_Tipo_Habitacion;
	private String denominacion;
	private String descripcion;
	private int capacidad_Personas;
	private float precio_Por_Dia;
	
	public int getId_Tipo_Habitacion() {
		return id_Tipo_Habitacion;
	}
	public void setId_Tipo_Habitacion(int id_Tipo_Habitacion) {
		this.id_Tipo_Habitacion = id_Tipo_Habitacion;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCapacidad_Personas() {
		return capacidad_Personas;
	}
	public void setCapacidad_Personas(int capacidad_Personas) {
		this.capacidad_Personas = capacidad_Personas;
	}
	public float getPrecio_Por_Dia() {
		return precio_Por_Dia;
	}
	public void setPrecio_Por_Dia(float precio_Por_Dia) {
		this.precio_Por_Dia = precio_Por_Dia;
	}
	
	
	
	
}
