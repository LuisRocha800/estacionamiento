package files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class opPensionado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String opcnUsr;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter salida = response.getWriter();

		opcnUsr = request.getParameter("usuario");
		
		salida.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<title> Iniciar</title>\r\n"
				+ "<link rel=\"stylesheet\" href=\"styles/start.css\">\r\n"
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
				+ "	<form action=\"operationpensionado\">\r\n"
				+ "		<div class=\"ext1\"> </div>\r\n"
				+ "		<label1> REGISTRAR INGRESO</label1> <br>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "<input type=\"hidden\" name=\"operacion\" value=\"ingreso\">"
				+ "		<input class=\"btningreso\" type=\"submit\" value=\"Ingreso\">\r\n"
				+ "	</form>\r\n"
				+ "	<form action=\"operationpensionado\">\r\n"
				+ "		<div class=\"ext1\"> </div>\r\n"
				+ "		<label1> REGISTRAR SALIDA</label1> <br>\r\n"
				+ "		<div class=\"ext2\"> </div>\r\n"
				+ "<input type=\"hidden\" name=\"operacion\" value=\"salida\">"
				+ "		<input class=\"btnsalida\" type=\"submit\" value=\"Salida\">\r\n"
				+ "	</form>\r\n"
				+ "</div>\r\n"
				+ "<div class=\"divapoyo2\"> </div>\r\n"
				+ "<footer class=\"piepagina\"> © 2023 Todos los derechos reservados <br> Version 1.0.0</footer>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
	}

}