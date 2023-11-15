package DaosApp.dao.dao.derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.Personal_DAO;
import DaosApp.dao.app.dto.Personal;
import DaosApp.dao.app.error.PersistenciaException;

public class PersonalDAODerbyImp extends Conexion implements Personal_DAO {

	public PersonalDAODerbyImp() {
		
	}
	
	public Personal create(Personal per) {
		PreparedStatement stmt;
		int ins;
		String sql;
		Personal insertado;
		
		try{
		
			sql = "INSERT INTO PERSONAL(id_tag, nombre, apellido) VALUES(?,?,?)";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, per.getId_tag());
			stmt.setString(2, per.getNombre());
			stmt.setString(3, per.getApellido());

            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new Personal();
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
	
	public Personal update(Personal per) {
		return null;
	}
	
	public void delete(Personal per) {
		
	}
	
	public Personal get(Personal per) {
		Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		Personal consultado;
		
		try {
			stmt = this.conexion.createStatement();
			sql = "SELECT * FROM PERSONAL WHERE ";
			atributos = this.getAtributos(per);
			
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
				consultado = new Personal();
				while(rs.next()) {
					
					consultado.setId_tag(rs.getString("id_tag"));
					consultado.setNombre(rs.getString("nombre"));
					consultado.setApellido(rs.getString("apellido"));
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
	
	public List<Personal> getAll(){
		return null;
	}
	
	private Map<String, Object> getAtributos(Personal per){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
		
		val = per.getId_tag();
		if(val != null){
			resul.put("id_tag", val);
		}
		
		val = per.getNombre();
		if(val != null) {
			resul.put("nombre",val);
		}
		
		val = per.getApellido();
	    if(val != null) {
			resul.put("apellido",val);
		}
			
		return resul;
	}
	
}
