package application.copy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeBioImplementation;
import models.Employee;

 
@WebServlet("/Register")
public class Register extends HttpServlet {
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int id=Integer.parseInt(request.getParameter("id"));
		 String name=request.getParameter("name");
		 String email=request.getParameter("email");
		 String department=request.getParameter("department");
		 int salary=Integer.parseInt(request.getParameter("salary"));
		 
		 Employee employee=new Employee(id,name,email,department,salary);
		 
		 EmployeeBioImplementation employeeBioImpl= new EmployeeBioImplementation();
		 
		 int rowsSaved = employeeBioImpl.save(employee);

	        // Set the content type for the response
	        response.setContentType("text/html");

	        // Get the PrintWriter object to write the response
	        PrintWriter out = response.getWriter();
	        if(rowsSaved==0) {
	        	out.println("Choose another id this is already exit");
	        	
	        }
	        else {
	        // Write the response to the browser
	        
	        out.println("<html><body>");
	        out.println("<h2>Save Result</h2>");
	        out.println("<p>" + rowsSaved + " row(s) saved.</p>");
	        out.println("</body></html>");

	        // Close the PrintWriter
	        out.close();
	        }
	}

}
