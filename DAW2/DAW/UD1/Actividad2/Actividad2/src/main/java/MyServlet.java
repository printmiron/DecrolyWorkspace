import java.io.IOException; 
import java.io.PrintWriter; 
  
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
  
/** 
 * Servlet implementation class MyServlet 
 */ 
@WebServlet("/Actividad2") 
public class MyServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
         
    /** 
     * @see HttpServlet#HttpServlet() 
     */ 
    public MyServlet() { 
        super(); 
        // TODO Auto-generated constructor stub 
    } 
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
response) 
     */ 
    protected void doGet(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException { 
  
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 
        out.println("<h1>Message from doGet method from MyServlet</h1>"); 
          
    } 
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, 
HttpServletResponse response) 
     */ 
    protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException { 
        
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 
        out.println("<h1>Message from doPost method from MyServlet</h1>"); 
        
     
        try {
        	//obtener los parametros del html
        	int min = Integer.parseInt(request.getParameter("min"));
        	int max = Integer.parseInt(request.getParameter("max"));
        	//validar que el minimo tiene que ser menor que el max
        	if(min >= max) {
        		out.println("El numero min tiene que ser menor que el maximo.");
        		//si todo es correcto hacemos la generacion del numero entre min y max
        	}else{
        		int randomNumero = (int)(Math.random() * (max - min + 1)) + min;
        		//monstramos el numero generado
        		out.println("Numero aleatorio generado entre " + min + " y " + max + " es: " + randomNumero);
        	}
        	
        }catch(NumberFormatException e){
        	out.println("Error: asigurar de introducir numeros validos.");
        }
    } 
  
} 