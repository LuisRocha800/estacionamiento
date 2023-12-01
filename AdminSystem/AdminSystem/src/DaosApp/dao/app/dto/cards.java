package DaosApp.dao.app.dto;

public class cards {
	
	private String id_tag;
	private String estatus;
	
	public String getId_tag() {
		return id_tag;
	}
	public void setId_tag(String id_tag) {
		this.id_tag = id_tag;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String toString() {
		return "cards [id_tag=" + id_tag + ", estatus=" + estatus + "]";
	}
	
	
}
