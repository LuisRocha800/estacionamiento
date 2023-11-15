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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fazecast.jSerialComm.SerialPort;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.dao.derby.nfc_movementsDaoDerbyImp;
import DaosApp.dao.app.dto.Pensionados;
import DaosApp.dao.app.dto.nfc_movements;
import DaosApp.dao.dao.derby.PensionadoDAODerbyImp;

public class leerUIDPensionado extends HttpServlet {
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
        
        opcnUsr = request.getParameter("operacion");
        
        if(opcnUsr.equals("ingreso")) {
        	
        	String url = ConexionEstatica.CONEXION_CREDENTIALS;
        	
        	try {
        	
            conexion = DriverManager.getConnection(url);
        	nfc_movementsDaoDerbyImp nfc = new nfc_movementsDaoDerbyImp();
            PensionadoDAODerbyImp pensionGet = new PensionadoDAODerbyImp();
            pensionGet.setConexion(conexion);
            
            nfc_movements mvmt = new nfc_movements();
			Pensionados pens = new Pensionados();
			pens.setId_tag(dataToValidate);
        	
		    Pensionados cd = pensionGet.get(pens);
			
		    String tagid = cd.getId_tag();
		          
	        if(tagid == null) {
	        	
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
			                		+ "<p class=\"prf1\"> ERROR: LA TARJETA NO SE ENCUENTRA REGISTRADA. <br> POR FAVOR REGISTRELA...</p>"
			               // + "        <button type=\"button\" class=\"btnAceptar\" id=\"btnRegresar\">Aceptar</button>\r\n"
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
	        	
	        }else {
	        	
			    String fecha = cd.getVigencia_tarjeta();
			    LocalDate fechaActual = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate otraFecha = LocalDate.parse(fecha, formatter);
		        
	        	if(fechaActual.isAfter(otraFecha)) {
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
 			                + "    <form id=\"miFormulario\" action=\"recibido\" >\r\n"
 			                + "        <input type=\"hidden\" name=\"uid\" value=\"" + dataToValidate + "\">\r\n"
 			                		+ "<p class=\"prf3\"> ERROR: TU SUSCRIPCION CADUCO. <br> RENUEVALA POR FAVOR</p>"
 			               // + "        <button type=\"button\" class=\"btnAceptar\" id=\"btnRegresar\">Aceptar</button>\r\n"  
 			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-3)\">Aceptar</a>"  
 			                + "    </form>\r\n"
 							+ "</div>"
 			                + "<script>\r\n"
 							+ "    document.getElementById(\"btnRegresar\").addEventListener(\"click\", function() {\r\n"
 							+ "    window.location.href = \"index.html\";"
 							+ "    });\r\n"
 			                + "</script>\r\n" 
 			                + "</body>\r\n"
 			                + "</html>");
	        	} else {
	        		
					mvmt.setId_tag_personal("None");
					mvmt.setId_tag_pensionado(tagid);
					mvmt.setId_tag_invitado("None");
					mvmt.setStatus("Completo");
					mvmt.setTipe_movement("Entrada");
					mvmt = nfc.create(mvmt);
					
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
 			                + "    <form id=\"miFormulario\" action=\"recibido\" >\r\n"
 			                + "        <input type=\"hidden\" name=\"uid\" value=\"" + dataToValidate + "\">\r\n"
 			                		+ "<p class=\"prf3\"> TRANSACCION REALIZADA CON EXITO. <br> QUE TENGAS UN EXCELENTE DIA</p>"
 			               // + "        <button type=\"button\" class=\"btnAceptar\" id=\"btnRegresar\">Aceptar</button>\r\n"  
 			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-3)\">Aceptar</a>"  
 			                + "    </form>\r\n"
 							+ "</div>"
 			                + "<script>\r\n"
 							+ "    document.getElementById(\"btnRegresar\").addEventListener(\"click\", function() {\r\n"
 							+ "    window.location.href = \"index.html\";"
 							+ "    });\r\n"
 			                + "</script>\r\n" 
 			                + "</body>\r\n"
 			                + "</html>");
	        	}    	
	        }	
        	}catch(SQLException ex) {
        		 ex.printStackTrace();
        	}
        }else if(opcnUsr.equals("salida")) {
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
			                + "    <form id=\"miFormulario\" action=\"recibido\" >\r\n"
			                + "        <input type=\"hidden\" name=\"uid\" value=\"" + dataToValidate + "\">\r\n"
			                		+ "<p class=\"prf4\"> TRANSACCION REALIZADA CON EXITO. <br> QUE TENGA EXCELENTE DIA: </p>"
			                //+ "        <button type=\"button\" class=\"btnAceptar\" id=\"btnRegresar\">Aceptar</button>\r\n" 
 			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-3)\">Aceptar</a>"  
			                + "    </form>\r\n"
							+ "</div>"
			                + "<script>\r\n"
 							+ "    document.getElementById(\"btnRegresar\").addEventListener(\"click\", function() {\r\n"
 							+ "    window.location.href = \"index.html\";"
 							+ "    });\r\n"
			                + "</script>\r\n" 
			                + "</body>\r\n"
			                + "</html>");
        }
        
        if(dataToValidate==null) {
            
        }else {
       	comPort.closePort();
        }
	}

}
