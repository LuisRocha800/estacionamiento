package DaosApp.dao.dao.derby;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.pagos_DAO;
import DaosApp.dao.app.dto.pagos;
import DaosApp.dao.app.error.PersistenciaException;

public class pagosDaoDerbyImp extends Conexion implements pagos_DAO {
	
	public pagosDaoDerbyImp() {
		
	}
	
	public pagos get(pagos pag) {
		return null;
	}
	
	public pagos create(pagos pag) {
		PreparedStatement stmt;
		int ins;
		String sql;
		pagos insertado;
		
		try{
		
			sql = "INSERT INTO PAGOS(id_card, date_payment, time_payment, time_service, amount, status) VALUES(?,CURRENT_DATE,CURRENT_TIME,?,?,?)";
			stmt = this.conexion.prepareStatement(sql); 
			
			stmt.setString(1, pag.getId_card());
			   stmt.setInt(2, pag.getTime_service());
			stmt.setDouble(3, pag.getAmount());
			stmt.setString(4, pag.getStatus());

            ins = stmt.executeUpdate();

            if(ins == 1) {
            	insertado = new pagos();
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
	
	public pagos update(pagos pag) {
		return null;
	}
	
	public void delete(pagos pag) {
		
	}
	
	public List<pagos> getAll(){
		return null;
	}
}

