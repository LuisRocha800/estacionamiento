package DaosApp.dao.app.dto;

public class Personal {

	private String id_tag;
	private String nombre;
	private String apellido;
	
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
	
	@Override
	public String toString() {
		return "Personal [id_tag=" + id_tag + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
	
}
