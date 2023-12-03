package DaosApp.dao.dao.derby;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import DaosApp.dao.app.Conexion;
import DaosApp.dao.app.pagos_DAO;
import DaosApp.dao.app.dto.pagos;
import DaosApp.dao.app.error.PersistenciaException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pagosDaoDerbyImp extends Conexion implements pagos_DAO {
	
	public pagosDaoDerbyImp() {
		
	}
	
    public List<pagos> get(pagos pag) {
    Statement stmt;
    String sql;
    ResultSet rs;
    Map<String, Object> atributos;
    pagos consultado;
    List<pagos> pagosCons = new ArrayList<>();

    try {
        stmt = this.conexion.createStatement();
        sql = "SELECT * FROM PAGOS";
        atributos = this.getAtributos(pag);

        if (!atributos.isEmpty()) {
            sql += " WHERE ";
            for (Map.Entry<String, Object> coso : atributos.entrySet()) {
                String key = coso.getKey();
                Object value = coso.getValue();
                String cad = key + "=";

                if (value instanceof String) {
                    cad += "'" + value.toString() + "' and ";
                } else {
                    cad += value.toString() + " and ";
                }
                sql += cad;
            }
            sql = sql.substring(0, sql.length() - 5);
        }

        System.out.println(sql);
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            consultado = new pagos();
            consultado.setDate_payment(rs.getString("date_payment"));
            consultado.setTime_payment(rs.getString("time_payment"));
            consultado.setTime_service(rs.getInt("time_service"));
            consultado.setAmount(rs.getInt("amount"));
            consultado.setStatus(rs.getString("status"));
            pagosCons.add(consultado);
        }

        stmt.close();
        conexion.close();
        return pagosCons;

    } catch (SQLException e) {
        e.printStackTrace();
        throw new PersistenciaException(e);
    }
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
        
        public List<pagos> consultaEspecial(int mes, int año) {
        Statement stmt;
        String sql;
        ResultSet rs;
        pagos consultado;
        List<pagos> pagosCons = new ArrayList<>();

        try {
            stmt = this.conexion.createStatement();
            sql = "SELECT * FROM PAGOS WHERE MONTH(DATE_PAYMENT) = ? AND YEAR(DATE_PAYMENT) = ?";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setInt(1, mes);
            preparedStatement.setInt(2, año);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                consultado = new pagos();
                consultado.setDate_payment(rs.getString("date_payment"));
                consultado.setTime_payment(rs.getString("time_payment"));
                consultado.setTime_service(rs.getInt("time_service"));
                consultado.setAmount(rs.getInt("amount"));
                consultado.setStatus(rs.getString("status"));
                pagosCons.add(consultado);
            }

            stmt.close();
            conexion.close();
            return pagosCons;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
        
    public List<pagos> consultaEspecialMonth(int mes) {
        Statement stmt;
        String sql;
        ResultSet rs;
        pagos consultado;
        List<pagos> pagosCons = new ArrayList<>();

        try {
            stmt = this.conexion.createStatement();
            sql = "SELECT * FROM PAGOS WHERE MONTH(DATE_PAYMENT) = ?";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setInt(1, mes);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                consultado = new pagos();
                consultado.setDate_payment(rs.getString("date_payment"));
                consultado.setTime_payment(rs.getString("time_payment"));
                consultado.setTime_service(rs.getInt("time_service"));
                consultado.setAmount(rs.getInt("amount"));
                consultado.setStatus(rs.getString("status"));
                pagosCons.add(consultado);
            }

            stmt.close();
            conexion.close();
            return pagosCons;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    public List<pagos> consultaEspecialYear(int año) {
        Statement stmt;
        String sql;
        ResultSet rs;
        pagos consultado;
        List<pagos> pagosCons = new ArrayList<>();

        try {
            stmt = this.conexion.createStatement();
            sql = "SELECT * FROM PAGOS WHERE YEAR(DATE_PAYMENT) = ?";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setInt(1, año);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                consultado = new pagos();
                consultado.setDate_payment(rs.getString("date_payment"));
                consultado.setTime_payment(rs.getString("time_payment"));
                consultado.setTime_service(rs.getInt("time_service"));
                consultado.setAmount(rs.getInt("amount"));
                consultado.setStatus(rs.getString("status"));
                pagosCons.add(consultado);
            }

            stmt.close();
            conexion.close();
            return pagosCons;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
        
        private Map<String, Object> getAtributos(pagos pag){
		Map<String, Object> resul = new HashMap<String, Object>();
		String val;
                double var;
		
		val = pag.getId_card();
		if(val != null) {
			resul.put("id_card", val);
		}
		
		val = pag.getDate_payment();
		if(val != null) {
			resul.put("date_payment", val);
		}
		
		val = pag.getTime_payment();
		if(val != null) {
			resul.put("time_payment", val);
		}
		
		var = pag.getTime_service();
		if(var > 0) {
			resul.put("time_service", var);
		}
		
		var = pag.getAmount();
		if(var > 0) {
			resul.put("amount", var);
		}
		
		val = pag.getStatus();
		if(val != null) {
			resul.put("status", val);
		}
			
		return resul;
	}
}

