package DaosApp.dao.app.dto;

public class Pensionados {

	private String id_tag;
	private String nombre;
	private String apellido;
	private String vigencia_tarjeta;
	private String cajon;
	
	public String getId_tag() {
		return id_tag;
	}
	public void setId_tag(String id_tag) {
		this.id_tag = id_tag;
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
	public String getVigencia_tarjeta() {
		return vigencia_tarjeta;
	}
	public void setVigencia_tarjeta(String vigencia_tarjeta) {
		this.vigencia_tarjeta = vigencia_tarjeta;
	}
	public String getCajon() {
		return cajon;
	}
	public void setCajon(String cajon) {
		this.cajon = cajon;
	}
	
	public String toString() {
		return "Pensionados [id_tag=" + id_tag + ", nombre=" + nombre + ", apellido=" + apellido + ", vigencia_tarjeta="
				+ vigencia_tarjeta + ", cajon=" + cajon + "]";
	}

	
	
}
