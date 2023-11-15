<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title> Iniciar</title>
<link rel=\"stylesheet\" href=\"WEB-INF/styless/start.css\">
				<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">
				<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>
				<link href=\"https://fonts.googleapis.com/css2?family=Dosis:wght@500;800&family=Quicksand:wght@700&display=swap\" rel=\"stylesheet\">
				<style>\r\n"
				  @import url('https://fonts.googleapis.com/css2?family=Outfit:wght@500&display=swap');\r\n"
				</style>
				</head>
				<body>
				<header>
				<div class=\"prrf1\">
				<div> <h1> CONTROL DE ESTACIONAMIENTO</h1></div>
				 <div> <h class=\"msj\">(Experimental)</h></div>
				</div>
				</header>
				<div class=\"separador\"> </div>
				<div class=\"contenedor\">
				<form action=\"operation\">
				<div class=\"ext1\"> </div>
				<label1> REGISTRAR INGRESO</label1> <br>
				<div class=\"ext2\"> </div>
				<input type=\"hidden\" name=\"operacion\" value=\"ingreso\">
				<input class=\"btningreso\" type=\"submit\" value=\"Ingreso\">
				</form>
				<form action=\"operation\">
				<div class=\"ext1\"> </div>
				<label1> REGISTRAR SALIDA</label1> <br>
				<div class=\"ext2\"> </div>
				<input type=\"hidden\" name=\"operacion\" value=\"salida\">
								<input class=\"btnsalida\" type=\"submit\" value=\"Salida\">
				</form>
				</div>
				<div class=\"divapoyo2\"> </div>
				<footer class=\"piepagina\"> © 2023 Todos los derechos reservados <br> Version 1.0.0</footer>
				</body>
				</html>