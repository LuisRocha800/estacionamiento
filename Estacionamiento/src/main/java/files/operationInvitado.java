package files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class operationInvitado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String opp;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter salida = response.getWriter();

		opp = request.getParameter("operacion");
		
		salida.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">"
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
				+ "	<form action=\"entradainvitado\">\r\n"
				+ "		<div class=\"ext1\"> </div>\r\n"
				+ "		<label1> IMPORTANTE</label1> <br>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "		<label2> <b>El sistema le generara una tarjeta.</b> <br> y activara su sesion temporal como invitado <br>"
				+ "		 Guarde la tarjeta porque le sera requerida posteriormente </label2>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "		<label4>Pulse Aceptar para continuar<br>\r\n"
				+ "		</label4>\r\n"
				+ "		<div class=\"ext3\"> </div>\r\n"
				+ "		<input class=\"btnAceptar\" type=\"submit\" value=\"Aceptar\">\r\n"
				+ "     <input type=\"hidden\" name=\"operacion\" value="+opp+">"
				+ "	</form>\r\n"
				+ "</div>\r\n"
				+ "<div class=\"divapoyo2\"> </div>\r\n"
				+ "<footer class=\"piepagina\"> © 2023 Todos los derechos reservados <br> Version 1.0.0</footer>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

}
