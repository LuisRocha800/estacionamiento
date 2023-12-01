package DaosApp.dao.dao.derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.cards_DAO;
import DaosApp.dao.app.dto.cards;
import DaosApp.dao.app.error.PersistenciaException;

public class cardsDaoDerbyImp extends Conexion implements cards_DAO {

	public cardsDaoDerbyImp() {
		
	}
	
	public cards create(cards crds) {
		PreparedStatement stmt;
		int ins;
		String sql;
		cards insertado;
		
		try{
		
			sql = "INSERT INTO TARJETAS(id_tag, estatus) VALUES(?,?)";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, crds.getId_tag());
			stmt.setString(2, crds.getEstatus());
            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new cards();
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
	
	public cards get(cards crds) {
		Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		cards consultado;
		
		try {
			stmt = this.conexion.createStatement();
			sql = "SELECT * FROM TARJETAS WHERE ";
			atributos = this.getAtributos(crds);
			
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
				consultado = new cards();
				while(rs.next()) {
					
					consultado.setId_tag(rs.getString("id_tag"));
					consultado.setEstatus(rs.getString("estatus"));
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
	
	public void delete(cards crds) {
            Statement stmt;
	String sql, cad;
	Map<String, Object> atributos;
	
	try {
		stmt = this.conexion.createStatement();
		sql = "DELETE FROM TARJETAS WHERE ";
		atributos = this.getAtributos(crds);
		
		if(atributos.size()>0) {
			for(Map.Entry<String, Object> coso : atributos.entrySet()){
				String key = coso.getKey();
				Object value = coso.getValue();
				cad = key + "=";
				
				if(value instanceof String) {
					cad += "'"+value.toString()+ "' and ";
				} else {
					cad += value.toString()+ " and ";
				}
				sql += cad;
			}
			sql = sql.substring(0, sql.length()-5);
			System.out.println(sql);
			stmt.executeUpdate(sql);

		    stmt.close();
		    conexion.close();
		}
	} catch (SQLException e) {
            e.printStackTrace();
      throw new PersistenciaException(e);
      
	}
	}
	
	public cards update(cards crds) {
		PreparedStatement stmt;
		int ins;
		String sql;
		cards actualizado;
		
		try{
		
			sql = "UPDATE TARJETAS SET estatus = ? WHERE id_tag = ?";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, crds.getEstatus());
			stmt.setString(2, crds.getId_tag());

            ins = stmt.executeUpdate();

            if(ins > 0) {
            	actualizado = new cards();
            } else {
            	
            	return null;
            }
            
            stmt.close();
            conexion.close();
			return actualizado;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}
	
	public List<cards> getAll(){
		return null;
	}
	
	private Map<String, Object> getAtributos(cards crds){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
		
		val = crds.getId_tag();
		if(val != null){
			resul.put("id_tag", val);
		}
		
		val = crds.getEstatus();
		if(val != null) {
			resul.put("estatus", val);
		}
			
		return resul;
	}

}
