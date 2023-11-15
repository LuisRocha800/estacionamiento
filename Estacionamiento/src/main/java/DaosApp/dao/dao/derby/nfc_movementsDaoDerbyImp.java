package DaosApp.dao.dao.derby;

import DaosApp.dao.app.nfc_movements_DAO;
import DaosApp.dao.app.dto.nfc_movements;
import DaosApp.dao.app.error.PersistenciaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaosApp.dao.app.Conexion;

public class nfc_movementsDaoDerbyImp extends Conexion implements nfc_movements_DAO{

	public nfc_movementsDaoDerbyImp() {
		
	}
	
	public nfc_movements create(nfc_movements mov) {
		PreparedStatement stmt;
		int ins;
		String sql;
		nfc_movements insertado;
		
		try{
		
			sql = "INSERT INTO NFC_MOVEMENTS_TEST(id_tag_personal, id_tag_pensionado, id_tag_invitado,"
					+ "date_mov, time_mov, status, TIPE_MOVEMENT) VALUES ("
					+ "?, ?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?)";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, mov.getId_tag_personal());
			stmt.setString(2, mov.getId_tag_pensionado());
			stmt.setString(3, mov.getId_tag_invitado());
			stmt.setString(4, mov.getStatus());
			stmt.setString(5, mov.getTipe_movement());

            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new nfc_movements();
            } else {
            	
            	return null;
            }
            
            stmt.close();
            conexion.close();
			
            return insertado;
			
		}catch (SQLException e) {
			 e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}
	
	public List<nfc_movements> getAll(){
		return null;
	}
	
	public nfc_movements update(nfc_movements mov) {
		return null;
	}
	
	public void delete(nfc_movements mov){
	
	}
	
	public nfc_movements get(nfc_movements mov) {
		Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		nfc_movements consultado;
		
		try {
			stmt = this.conexion.createStatement();
			sql = "SELECT * FROM NFC_MOVEMENTS_TEST WHERE ";
			atributos = this.getAtributos(mov);
			
			if(atributos.size()>0) {
				for (Map.Entry<String, Object> coso : atributos.entrySet()) {
				    String key = coso.getKey();
				    Object value = coso.getValue();
				    cad = key+"=";
				    if(value instanceof String) {
				    	cad +="'"+value.toString()+"' and ";
				    } else {
				    	cad += value.toString()+ " and ";
				    }
				    sql+=cad;
				}
				sql = sql.substring(0,sql.length()-5);
				rs = stmt.executeQuery(sql);
				consultado = new nfc_movements();
				while(rs.next()) {
					consultado.setId_tag_personal(rs.getString("id_tag_personal"));
					consultado.setId_tag_pensionado(rs.getString("id_tag_pensionado"));
					consultado.setId_tag_personal(rs.getString("id_tag_invitado"));
					consultado.setDate_mov(rs.getString("date_mov"));
					consultado.setTime_mov(rs.getString("time_mov"));
					consultado.setStatus(rs.getString("status"));
					consultado.setTipe_movement(rs.getString("tipe_movement"));
				}
				return consultado;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
		
		
	}
	
	private Map<String, Object> getAtributos(nfc_movements mov){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
		
		val = mov.getId_tag_personal();
		if(val != null) {
			resul.put("id_tag_personal", val);
		}
		
		val = mov.getId_tag_pensionado();
		if(val != null) {
			resul.put("id_tag_pensionado", val);
		}
		
		val = mov.getId_tag_invitado();
		if(val != null) {
			resul.put("id_tag_invitado", val);
		}
		
		val = mov.getDate_mov();
		if(val != null) {
			resul.put("date_mov", val);
		}
		
		val = mov.getTime_mov();
		if(val != null) {
			resul.put("time_mov", val);
		}
		
		val = mov.getStatus();
		if(val != null) {
			resul.put("status", val);
		}
		
		val = mov.getTipe_movement();
		if(val != null) {
			resul.put("tipe_movement",val);
		}
		
			
		return resul;
	}
	
}
