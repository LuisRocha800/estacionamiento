package DaosApp.dao.app.dto;

public class nfc_movements {

	private String id_tag_personal;
	private String id_tag_pensionado;
	private String id_tag_invitado;
	private String date_mov;
	private String time_mov;
	private String status;
	private String tipe_movement;
	
	public String getId_tag_personal() {
		return id_tag_personal;
	}
	public void setId_tag_personal(String id_tag_personal) {
		this.id_tag_personal = id_tag_personal;
	}
	public String getId_tag_pensionado() {
		return id_tag_pensionado;
	}
	public void setId_tag_pensionado(String id_tag_pensionado) {
		this.id_tag_pensionado = id_tag_pensionado;
	}
	public String getId_tag_invitado() {
		return id_tag_invitado;
	}
	public void setId_tag_invitado(String id_tag_invitado) {
		this.id_tag_invitado = id_tag_invitado;
	}
	public String getDate_mov() {
		return date_mov;
	}
	public void setDate_mov(String date_mov) {
		this.date_mov = date_mov;
	}
	public String getTime_mov() {
		return time_mov;
	}
	public void setTime_mov(String time_mov) {
		this.time_mov = time_mov;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipe_movement() {
		return tipe_movement;
	}
	public void setTipe_movement(String tipe_movement) {
		this.tipe_movement = tipe_movement;
	}
	
	@Override
	public String toString() {
		return "nfc_movements [id_tag_personal=" + id_tag_personal + ", id_tag_pensionado=" + id_tag_pensionado
				+ ", id_tag_invitado=" + id_tag_invitado + ", date_mov=" + date_mov + ", time_mov=" + time_mov
				+ ", status=" + status + ", tipe_movement=" + tipe_movement + "]";
	}
}
