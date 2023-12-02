package test;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.app.dto.adminusers;
import DaosApp.dao.app.dto.cards;
import DaosApp.dao.app.dto.nfc_movements;
import DaosApp.dao.dao.derby.adminusersDAODerbyImp;
import DaosApp.dao.dao.derby.cardsDaoDerbyImp;
import DaosApp.dao.dao.derby.nfc_movementsDaoDerbyImp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

private static Connection conexion;
		
	public static void main(String [] args){
		    
		  String url = ConexionEstatica.CONEXION_CREDENTIALS;
		
			try {
				
	            conexion = DriverManager.getConnection(url);
                    
                    nfc_movementsDaoDerbyImp derb = new nfc_movementsDaoDerbyImp();
                    derb.setConexion(conexion);
                    
                    nfc_movements nfc = new nfc_movements();
                    
                    List<nfc_movements> nfc2 = new ArrayList<>();
                    
                    
                    
                    nfc.setDate_mov("2023-11-22");
                    nfc.setTipe_movement("salida");
        
                   //nfc2 = derb.get(nfc);
                            
                    System.out.println(derb.get(nfc));
                    
        		
              
				}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			
	}

	}


