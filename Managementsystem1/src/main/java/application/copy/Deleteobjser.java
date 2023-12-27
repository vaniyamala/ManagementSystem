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

 
@WebServlet("/Deleteobjser")
public class Deleteobjser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the form directly in the doGet method
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Enter employee id to delete</h1>");
        out.println("<form action='Deleteid' method='post'>");
        out.println("<label for='id'>ID:</label>");
        out.println("<input type='text' id='id' name='id' required><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");

        out.close();
    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Handle the deletion logic for POST requests
	        try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            EmployeeBioImplementation employeeBioImpl = new EmployeeBioImplementation();
	            int rowsDeleted = employeeBioImpl.delete(id);

	            // Prepare the response
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();

	            out.println("<html><body>");
	            out.println("<h2>Delete Result</h2>");
	            out.println("<p>" + rowsDeleted + " row(s) deleted.</p>");
	            out.println("</body></html>");

	            out.close();
	        } catch (NumberFormatException e) {
	            // Handle the NumberFormatException (e.g., invalid input for parseInt)
	            e.printStackTrace(); // Log the exception or handle it according to your needs
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
	        }

	}

}
