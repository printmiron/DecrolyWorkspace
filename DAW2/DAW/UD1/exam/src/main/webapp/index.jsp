<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
pageEncoding="ISO-8859-1"%> 
<%@ page import="java.util.Date"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="ISO-8859-1"> 
<title>Exam</title> 
<link rel = "stylesheet" href = "./style.css"> 
</head> 
<body> 
    <div class="container">
        <h1>Bienvenido a mi pagina de examen </h1>
        
        
		
        
        <div class="section-title">Form</div>
        
        <form method="post" action="Exam">
        
            <label for="litros">Numero de litros:</label><br>
            <input type="number" name="litros" id="litros" required><br>

            <input type="submit" value="Convertir">
            
        </form>
        
    </div>
</body> 
</html> 