<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%> 
<%@ page import="java.util.Date"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="ISO-8859-1"> 
<title>Actividad2</title> 
<link rel = "stylesheet" href = "./style.css"> 
</head> 
<body> 
    <div class="container">
        <h1>Bienvenido a mi pagina web</h1>

        <div class="section-title">Mensajes desde el Servlet</div>
        <form action="Actividad2" method="GET"> 
            <button type="submit">GET Message</button> 
        </form> 

        <form action="Actividad2" method="POST"> 
            <button type="submit">POST Message</button> 
        </form> 

        <div class="section-title">Generador de numero aleatorio</div>
        <form method="post" action="Actividad2">
            <label for="min">Numero minimo:</label><br>
            <input type="number" name="min" id="min" required><br>

            <label for="max">Numero maximo:</label><br>
            <input type="number" name="max" id="max" required><br>

            <input type="submit" value="Generar numero aleatorio">
        </form>
    </div>
</body> 
</html> 