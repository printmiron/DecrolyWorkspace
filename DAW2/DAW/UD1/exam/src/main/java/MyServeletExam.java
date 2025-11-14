

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServeletExam
 */
@WebServlet("/Exam")
public class MyServeletExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServeletExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException { 
			        
			        response.setContentType("text/html"); 
			        PrintWriter out = response.getWriter(); 
			        out.println("<h1>Message from doPost method from MyServlet</h1>"); 
			        
			     
			        try {
			        	
			        	int litros  = Integer.parseInt(request.getParameter("litros"));
			        	
			        	if(litros <= 0) {
			        		out.println("El numero min tiene que ser mayor que 0.");
			        		
			        	}else{
			        		
			        		double mpg = 235.214 / litros;
			        		
			        		out.println("Convertir de " + litros + " litros a galones es: " + mpg + " galones");
			        	}
			        	
			        }catch(NumberFormatException e){
			        	out.println("Error: asigurar de introducir numeros validos.");
			        }
			    } 

}
