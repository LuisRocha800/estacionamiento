package DaosApp.dao.dao.derby;

import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.adminusers_DAO;
import DaosApp.dao.app.dto.Personal;
import DaosApp.dao.app.dto.adminusers;
import DaosApp.dao.app.error.PersistenciaException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adminusersDAODerbyImp extends Conexion implements adminusers_DAO{
    
    public adminusersDAODerbyImp(){
        
    }
    
    public List<adminusers> getAll(){
        return null;
    }
    
    public adminusers create(adminusers adm ){
        return null;
    }
    
    public void delete(adminusers adm){
        
    }
    
    public adminusers update(adminusers adm){
        return null;
    }
    
    public adminusers get(adminusers adm){
       		Statement stmt;
		String sql, cad;
		ResultSet rs;
		Map<String, Object> atributos;
		adminusers consultado;
		
		try {
			stmt = this.conexion.createStatement();
			sql = "SELECT * FROM ADMINUSERS WHERE ";
			atributos = this.getAtributos(adm);
			
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
				consultado = new adminusers();
				while(rs.next()) {
					
					consultado.setUsuario(rs.getString("usuario"));
					consultado.setPassword(rs.getString("password"));
				
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
    
    private Map<String, Object> getAtributos(adminusers adm){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
		
		val = adm.getUsuario();
		if(val != null){
			resul.put("usuario", val);
		}
		
		val = adm.getPassword();
		if(val != null) {
			resul.put("password",val);
		}
			
		return resul;
	}
    
}
