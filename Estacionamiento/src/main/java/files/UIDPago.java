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
import com.fazecast.jSerialComm.SerialPort;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.app.dto.pagos;
import DaosApp.dao.dao.derby.pagosDaoDerbyImp;

public class UIDPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SerialPort comPort;
	private String dataToValidate, tiempo, cantidad,opp;
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
        
        //
        tiempo = request.getParameter("totaltiempo");
        cantidad = request.getParameter("cantidadcobrar");
        opp = request.getParameter("opp");
        
        int time = Integer.parseInt(tiempo);
        double cantPag = Double.parseDouble(cantidad);
        
        if(opp.equals("pagarticket")) {
        	
        String url = ConexionEstatica.CONEXION_CREDENTIALS;
        	 
        	 try {
        		
        		conexion = DriverManager.getConnection(url);
        		pagosDaoDerbyImp paym = new pagosDaoDerbyImp();
 	            paym.setConexion(conexion);
 	            pagos pag = new pagos();
 	            
 	            pag.setId_card(dataToValidate);
	            pag.setTime_service(time);
	            pag.setAmount(cantPag);
	            pag.setStatus("Completo");
	            
	            pag = paym.create(pag);
	            
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
			                		+ "<p class=\"prf1\"> TRANSACCION REALIZADA CON EXITO. <br> QUE TENGA EXCELENTE DIA...</p>"
			                + "<a class=\"btnAceptar\" href=\"javascript:history.go(-5)\">Aceptar</a>"  
			                + "    </form>\r\n"
							+ "</div>" 
			                + "</body>\r\n"
			                + "</html>");
        		 
        	 }catch(SQLException ex) {
        		 ex.printStackTrace();
        	 }

        }else {
        	
        }
        // END IF
     
        if(dataToValidate==null) {
            
        }else {
       	comPort.closePort();
        }
	}

}
