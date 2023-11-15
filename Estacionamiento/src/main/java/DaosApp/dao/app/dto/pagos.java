package DaosApp.dao.app.dto;

public class pagos {
	
	private String id_card;
	private String date_payment;
	private String time_payment;
	private int time_service;
	private double amount;
	private String status;
	
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getDate_payment() {
		return date_payment;
	}
	public void setDate_payment(String date_payment) {
		this.date_payment = date_payment;
	}
	public String getTime_payment() {
		return time_payment;
	}
	public void setTime_payment(String time_payment) {
		this.time_payment = time_payment;
	}
	public int getTime_service() {
		return time_service;
	}
	public void setTime_service(int time_service) {
		this.time_service = time_service;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "pagos [id_card=" + id_card + ", date_payment=" + date_payment + ", time_payment=" + time_payment
				+ ", time_service=" + time_service + ", amount=" + amount + ", status=" + status + "]";
	}
	

}
