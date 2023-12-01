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
import java.util.HashMap;
import java.util.Map;


public class Main {

private static Connection conexion;
		
	public static void main(String [] args){
		    
		  String url = ConexionEstatica.CONEXION_CREDENTIALS;
		
			try {
				
	            conexion = DriverManager.getConnection(url);
        		
                String usuario = "noobmaster";
                String passwrd = "24112023";
                
             	
	            adminusersDAODerbyImp admusr = new adminusersDAODerbyImp();
	            admusr.setConexion(conexion);
	            adminusers usradm = new adminusers();
	            usradm.setUsuario(usuario);
	            

	            usradm = admusr.get(usradm);
	            
	            System.out.println("RESULT DE CD GET: "+usradm);
                    
                    if(usradm.getUsuario() == null){
                        System.out.println("NO EXISTE ESE USUARIO, INTENTA DE NUEVO");
                    }else if(usradm.getUsuario().equals(usuario) && usradm.getPassword().equals(passwrd) ){
                       System.out.println("HOLA BIENVENIDO");
                    }else{
                       System.out.println("CREDENCIALES INCORRECTAS, TRY IT"); 
                    }
         
				}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			
	}

	}


