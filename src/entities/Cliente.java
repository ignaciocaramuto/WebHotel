package entities;

import java.util.Date;

public class Cliente {
	private int idCliente;
	private String tipoDoc;
	private String numDoc;
	private String apellido;
	private String nombre;
	private String sexo;
	private java.sql.Date fechaNacimiento;
	private String mail;
	private String telefono;
	private String usuario;
	private String contraseña;
	private String tipoTarjetaCredito;
	private String numTarjetaCredito;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.sql.Date date) {
		this.fechaNacimiento = date;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String string) {
		this.telefono = string;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getTipoTarjetaCredito() {
		return tipoTarjetaCredito;
	}
	public void setTipoTarjetaCredito(String tipoTarjetaCredito) {
		this.tipoTarjetaCredito = tipoTarjetaCredito;
	}
	public String getNumTarjetaCredito() {
		return numTarjetaCredito;
	}
	public void setNumTarjetaCredito(String numTarjetaCredito) {
		this.numTarjetaCredito = numTarjetaCredito;
	}
	
	public String toString() {
		return "\nPersona [id=" + idCliente + ", tipoDoc=" + tipoDoc + ", nroDoc=" + numDoc + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + mail + ", tel=" + telefono + "]";
	}


}