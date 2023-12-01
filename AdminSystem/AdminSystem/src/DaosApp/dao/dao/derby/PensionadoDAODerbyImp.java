package DaosApp.dao.dao.derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.Pensionado_DAO;
import DaosApp.dao.app.dto.Pensionados;
import DaosApp.dao.app.error.PersistenciaException;

public class PensionadoDAODerbyImp extends Conexion implements Pensionado_DAO{

	public PensionadoDAODerbyImp() {
		
	}
	
	public List<Pensionados> getAll() {
		return null;
	}

	public Pensionados create(Pensionados pen) {	
		PreparedStatement stmt;
		int ins;
		String sql;
		Pensionados insertado;
		
		try{
		
			sql = "INSERT INTO PENSIONADOS(id_tag, nombre, apellido, vigencia_tarjeta) VALUES(?,?,?,?)";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, pen.getId_tag());
			stmt.setString(2, pen.getNombre());
			stmt.setString(3, pen.getApellido());
			stmt.setString(4, pen.getVigencia_tarjeta());

            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new Pensionados();
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

	public void delete(Pensionados pen) {
	Statement stmt;
	String sql, cad;
	Map<String, Object> atributos;
	
	try {
		stmt = this.conexion.createStatement();
		sql = "DELETE FROM PENSIONADOS WHERE ";
		atributos = this.getAtributos(pen);
		
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

	public Pensionados update(Pensionados pen) {
		PreparedStatement stmt;
		int ins;
		String sql;
		Pensionados actualizado;
		
		try{
		
			sql = "UPDATE PENSIONADOS SET nombre = ?, apellido = ?, vigencia_tarjeta = ? WHERE id_tag = ?";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, pen.getNombre());
			stmt.setString(2, pen.getApellido());
			stmt.setString(3, pen.getVigencia_tarjeta());
			stmt.setString(4, pen.getId_tag());

            ins = stmt.executeUpdate();

            if(ins > 0) {
            	actualizado = new Pensionados();
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

	public Pensionados get(Pensionados pen) {
		Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		Pensionados consultado;
		
		try {
			stmt = this.conexion.createStatement();
			sql = "SELECT * FROM PENSIONADOS WHERE ";
			atributos = this.getAtributos(pen);
			
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
				consultado = new Pensionados();
				while(rs.next()) {
					consultado.setId_tag(rs.getString("id_tag"));
					consultado.setNombre(rs.getString("nombre"));
					consultado.setApellido(rs.getString("apellido"));
					consultado.setVigencia_tarjeta(rs.getString("vigencia_tarjeta"));
					
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
	
	private Map<String, Object> getAtributos(Pensionados pen){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
		
		val = pen.getId_tag();
		if(val != null) {
			resul.put("id_tag", val);
		}
		
		val = pen.getNombre();
		if(val != null) {
			resul.put("nombre", val);
		}
		
		val = pen.getApellido();
		if(val != null) {
			resul.put("apellido", val);
		}
		
		val = pen.getVigencia_tarjeta();
		if(val != null) {
			resul.put("vigencia_tarjeta", val);
		}	
		
		return resul;
	}
	
}
