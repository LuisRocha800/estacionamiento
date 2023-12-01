package DaosApp.dao.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion {
	
	protected Connection conexion;
	
	public Conexion() {
		String url = ConexionEstatica.CONEXION_CREDENTIALS;
		try {
			this.conexion = DriverManager.getConnection(url);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Conexion(Connection con) {
		this.conexion = con;
	}
	
	public void setConexion(Connection con) {
		this.conexion = con;
	}	
}