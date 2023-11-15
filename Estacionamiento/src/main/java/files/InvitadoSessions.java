package files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.app.dto.cards;
import DaosApp.dao.dao.derby.cardsDaoDerbyImp;
import DaosApp.dao.app.dto.nfc_movements;
import DaosApp.dao.dao.derby.nfc_movementsDaoDerbyImp;

public class InvitadoSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String opcnUsr;
	private static Connection conexion;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 opcnUsr = request.getParameter("operacion");
		 
     	PrintWriter salida = response.getWriter();
	       response.setContentType("text/html");
		 
		 String url = ConexionEstatica.CONEXION_CREDENTIALS;
		 
		 try {
			 
			 conexion = DriverManager.getConnection(url);
			 
			 cardsDaoDerbyImp crds = new cardsDaoDerbyImp();
	            crds.setConexion(conexion);
	            
	            cards crd = new cards();
	            crd.setEstatus("DISPONIBLE");
	            
	            crd = crds.get(crd);
	            String tagid = crd.getId_tag();
	            
	            if(tagid == null) {
 					
 			        salida.println("<!DOCTYPE html>\r\n"
 			        		+ "<html>\r\n"
 							+ "<head>\r\n"
 							+ "<title>Registro</title>\r\n"
 							+ "<link rel=\"stylesheet\" href=\"styles/enter.css\">\r\n"
 							+ "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
 							+ "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
 							+ "<link href=\"https://fonts.googleapis.com/css2?family=Dosis:wght@500;800&family=Quicksand:wght@700&display=swap\" rel=\"stylesheet\">\r\n"
 							+ "<style>\r\n"
 							+ "  @import url('https://fonts.googleapis.com/css2?family=Outfit:wght@500&display=swap');\r\n"
 							+ "</style>\r\n"
 							+ "</head>\r\n"
 			                + "<body>\r\n"
 							+ "<header>\r\n"
 							+ "	<div class=\"prrf1\">\r\n"
 							+ "		<div> <h1> CONTROL DE ESTACIONAMIENTO</h1></div>\r\n"
 							+ "		 <div> <h class=\"msj\">(Experimental)</h></div>\r\n"
 							+ "	</div>\r\n"
 							+ "</header>\r\n"
 							+ "<div class=\"separador\"> </div>\r\n"
 							+ "<div class=\"contenedor\"> "
 			                + "    <form id=\"miFormulario\">"
 			                		+ "<p class=\"prf1\"> YA NO HAY DISPONIBILIDAD PARA INVITADOS EN EL ESTACIONAMIENTO...</p>"
 			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-3)\">Aceptar</a>"  
 			                + "    </form>\r\n"
 							+ "</div>"
 							+ "<script>\r\n"
 							+ "    document.getElementById(\"btnRegresar\").addEventListener(\"click\", function() {\r\n"
 							+ "    window.location.href = \"index.html\";"
 							+ "    });\r\n"
 							+ "  </script>" 
 			                + "</body>\r\n"
 			                + "</html>");
	            	
	            } else {
	            	nfc_movementsDaoDerbyImp nfc = new nfc_movementsDaoDerbyImp();      
		            nfc_movements mvmt = new nfc_movements();
		            
		            LocalTime horaActual = LocalTime.now();
		            
		            int hora = horaActual.getHour();
		            int minutos = horaActual.getMinute();
		            int segundos = horaActual.getSecond();
		            
		            mvmt.setId_tag(tagid);
		            mvmt.setStatus("Completo");
		            mvmt.setTipe_movement(opcnUsr);
		            nfc_movements nfcm = nfc.create(mvmt);
		            
		            crd.setEstatus("OCUPADO");
		            crd.setId_tag(tagid);
		            
		            crd = crds.update(crd);
		            
 			        salida.println("<!DOCTYPE html>\r\n"
 			        		+ "<html>\r\n"
 							+ "<head>\r\n"
 							+ "<title>Registro</title>\r\n"
 							+ "<link rel=\"stylesheet\" href=\"styles/enter.css\">\r\n"
 							+ "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
 							+ "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
 							+ "<link href=\"https://fonts.googleapis.com/css2?family=Dosis:wght@500;800&family=Quicksand:wght@700&display=swap\" rel=\"stylesheet\">\r\n"
 							+ "<style>\r\n"
 							+ "  @import url('https://fonts.googleapis.com/css2?family=Outfit:wght@500&display=swap');\r\n"
 							+ "</style>\r\n"
 							+ "</head>\r\n"
 			                + "<body>\r\n"
 							+ "<header>\r\n"
 							+ "	<div class=\"prrf1\">\r\n"
 							+ "		<div> <h1> CONTROL DE ESTACIONAMIENTO</h1></div>\r\n"
 							+ "		 <div> <h class=\"msj\">(Experimental)</h></div>\r\n"
 							+ "	</div>\r\n"
 							+ "</header>\r\n"
 							+ "<div class=\"separador\"> </div>\r\n"
 							+ "<div class=\"contenedor\"> "
 			                + "    <form id=\"miFormulario\">"
 			                		+ "<p class=\"prf1\"> TRANSACCION REALIZADA CON EXITO </p>"
 			                		+ "<label1> Su ID de transaccion es: "+ tagid+" <br></label1>"
 			                		+ "<label2> Su hora de ingreso es: "+hora+" Horas "+minutos+" Minutos "+segundos+" Segundos <br></label2>"
 			                		+ "<label> <br> </label>"
 			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-3)\">Aceptar</a>"  
 			                + "    </form>\r\n"
 							+ "</div>"
 							+ "<script>\r\n"
 							+ "    document.getElementById(\"btnRegresar\").addEventListener(\"click\", function() {\r\n"
 							+ "    window.location.href = \"index.html\";"
 							+ "    });\r\n"
 							+ "  </script>" 
 			                + "</body>\r\n"
 			                + "</html>");
 			        
	            }
			 
			 
		 } catch(SQLException ex) {
			 ex.printStackTrace();
		 }
	}

}
