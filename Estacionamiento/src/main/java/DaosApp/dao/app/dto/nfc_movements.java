package DaosApp.dao.app.dto;

public class nfc_movements {

	private String id_tag;
	private String date_mov;
	private String time_mov;
	private String status;
	private String tipe_movement;
	
	public String getId_tag() {
		return id_tag;
	}
	public void setId_tag(String id_tag) {
		this.id_tag = id_tag;
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
		return "nfc_movements [id_tag=" + id_tag + ", date_mov=" + date_mov + ", time_mov=" + time_mov + ", status="
				+ status + ", tipe_movement=" + tipe_movement + "]";
	}

	
}
