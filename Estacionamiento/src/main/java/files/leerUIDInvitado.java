package files;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fazecast.jSerialComm.SerialPort;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.app.dto.cards;
import DaosApp.dao.dao.derby.cardsDaoDerbyImp;
import DaosApp.dao.app.dto.nfc_movements;
import DaosApp.dao.dao.derby.nfc_movementsDaoDerbyImp;

public class leerUIDInvitado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SerialPort comPort;
	private String dataToValidate, opcnUsr;
	private static Connection conexion;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		comPort = SerialPort.getCommPort("COM3");
        comPort.openPort();

        StringBuilder serialDataBuffer = new StringBuilder();

        while (true) {
            while (comPort.bytesAvailable() <= 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            byte[] readBuffer = new byte[comPort.bytesAvailable()];
            int numRead = comPort.readBytes(readBuffer, readBuffer.length);
            if (numRead > 0) {
                String serialData = new String(readBuffer, 0, numRead);
                serialDataBuffer.append(serialData);
                if (serialData.contains("\n")) {
                    dataToValidate = serialDataBuffer.toString().trim();
                    serialDataBuffer.setLength(0);
                    break;
                }
            }
        }
	//.---------
        opcnUsr = request.getParameter("operacion");
        
        if(opcnUsr.equals("salida")) {
        	 String url = ConexionEstatica.CONEXION_CREDENTIALS;
             
            
             
             try{
             	
             	conexion = DriverManager.getConnection(url);
             	
             	String iddeltag = dataToValidate;
             	
	            cardsDaoDerbyImp crds = new cardsDaoDerbyImp();
	            crds.setConexion(conexion);
	            cards cd = new cards();
	            cd.setId_tag(iddeltag);

	            cd = crds.get(cd);
	            
	            if(cd.getId_tag() == null) {
	            	PrintWriter salida = response.getWriter();
 			        response.setContentType("text/html");
 					
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
 			                		+ "<p class=\"prf1\"> ERROR: NO SE ENCUENTRA LA TARJETA. <br> INTENTE DE NUEVO...</p>"
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
	            mvmt.setId_tag(iddeltag);
	            
	            mvmt = nfc.get(mvmt);
	            
                 if(cd.getEstatus().equals("DISPONIBLE")) {
                 	PrintWriter salida = response.getWriter();
     		        response.setContentType("text/html");
     				
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
     		                		+ "<p class=\"prf1\"> NO HAY NINGUNA SESION ASOCIADA A ESA TARJETA</p>"

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
                    
                	 String mov = mvmt.getTipe_movement();
                	 
                 	if(mov.equals("ingreso")) {
                 		
                 		LocalTime horaActual = LocalTime.now();
     	            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
     	                String hraActual = horaActual.format(formatter);
     	            	String horaEntrada = mvmt.getTime_mov();
     	            	
     	            	LocalTime hora1 = LocalTime.parse(hraActual, DateTimeFormatter.ofPattern("HH:mm:ss"));
     	                LocalTime hora2 = LocalTime.parse(horaEntrada, DateTimeFormatter.ofPattern("HH:mm:ss"));
     	                LocalTime resultado = hora1.minusHours(hora2.getHour()).minusMinutes(hora2.getMinute());
     	                long minutosTotales = resultado.toSecondOfDay() / 60;

     	                double hrstot = (double) minutosTotales / 60;
     	                
     					
     					PrintWriter salida = response.getWriter();
     			        response.setContentType("text/html");
     					
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
      			                + "    <form id=\"miFormulario\" action=\"acercatransaccionsalida\" >\r\n"
      			                + "        <input type=\"hidden\" name=\"uid\" value=\"" + dataToValidate + "\">\r\n"
      			                		+ "<p class=\"prf3\"> TRANSACCION REALIZADA CON EXITO. <br> "
      			                		+ "Su hora de ingreso fue: "+horaEntrada+" <br>"
      			                		+ " Su hora de salida fue: "+hraActual+" <br>"
      			                	    + " Total de tiempo de uso del servicio: "+ minutosTotales +" <br>"
      			                	    + " Cantidad a cobrar por el uso del servicio($15/hr): "+hrstot * 15+"</p>" 
      			                + "     <input type=\"hidden\" name=\"totaltiempo\" value="+minutosTotales+">"
      			                + "     <input type=\"hidden\" name=\"cantidadcobrar\" value="+hrstot * 15+">"
      			                + "		<input class=\"btnAceptar\" type=\"submit\" value=\"Realizar Pago\">\r\n"  
      			                + "    </form>\r\n"
      							+ "</div>"
      			                + "</body>\r\n"
      			                + "</html>");
      			        
      			      mvmt.setId_tag(iddeltag);
						mvmt.setStatus("Completo");
						mvmt.setTipe_movement("salida");
						mvmt = nfc.create(mvmt);
						
						cd.setEstatus("DISPONIBLE");
						cd.setId_tag(iddeltag);
						
						cd = crds.update(cd);
                 	}
                 	
                 }
	            
	            }
                 
                 }catch(SQLException ex) {
             	ex.printStackTrace();
             }
        }
       
        if(dataToValidate==null) {
            
        }else {
       	comPort.closePort();
        }
	
	}


}
