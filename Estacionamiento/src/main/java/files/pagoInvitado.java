package files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class pagoInvitado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String tiempo, cantidadpagar;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter salida = response.getWriter();
		
		tiempo = request.getParameter("totaltiempo");
		cantidadpagar = request.getParameter("cantidadcobrar");
		
		salida.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<title>Iniciar(operacion)</title>\r\n"
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
				+ "<div class=\"contenedor\">\r\n"
				+ "	\r\n"
				+ "	<form action=\"pagosalida\">\r\n"
				+ "		<div class=\"ext1\"> </div>\r\n"
				+ "		<label1> IMPORTANTE</label1> <br>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "		<label2> Puede realizar su pago por medio de la tecnologia. <b>Contacless</b></label2>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "		<label3> Los pasos a seguir son los siguientes:</label3>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "		<label4> 1) Utilice una tarjeta de credito/debito con soporte contacless <br>"
				                 + "o en su defecto utilice su tarjeta virtual vinculada a una wallet <br>"
				                 + "con un dispositivo que soporte NFC "
				+ "		         2) Pulse aceptar <br>"
				+ "		         3) Acerque su tarjeta/dispositivo al sensor RIFD (NFC) <br>\r\n"
				+ "		</label4>\r\n"
				+ "		<div class=\"ext3\"> </div>\r\n"
				+ "		<input class=\"btnAceptar\" type=\"submit\" value=\"Aceptar\">\r\n"
				+ "     <input type=\"hidden\" name=\"totaltiempo\" value="+tiempo+">"
				+ "     <input type=\"hidden\" name=\"cantidadcobrar\" value="+cantidadpagar+">"
				+ "     <input type=\"hidden\" name=\"opp\" value=\"pagarticket\">"
				+ "	</form>\r\n"
				+ "</div>\r\n"
				+ "<div class=\"divapoyo2\"> </div>\r\n"
				+ "<footer class=\"piepagina\"> © 2023 Todos los derechos reservados <br> Version 1.0.0</footer>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

}
