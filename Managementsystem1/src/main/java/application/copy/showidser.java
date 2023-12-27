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

/**
 * Servlet implementation class showidser
 */
@WebServlet("/showidser")
public class showidser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                // Get the employee by ID from the database
                EmployeeBioImplementation employeeBioImpl = new EmployeeBioImplementation();
                Employee employee = employeeBioImpl.get(id);

                // Prepare the response
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<html><body>");
                if (employee != null) {
                    // Display employee information
                    out.println("<h1>Employee Details</h1>");
                    out.println("<p>ID: " + employee.getId() + "</p>");
                    out.println("<p>Name: " + employee.getName() + "</p>");
                    out.println("<p>Email: " + employee.getEmail() + "</p>");
                    out.println("<p>Department: " + employee.getDepartment() + "</p>");
                    out.println("<p>Salary: " + employee.getSalary() + "</p>");
                } else {
                    // Display message for non-existing employee
                    out.println("<h1>Employee not found!</h1>");
                }
                out.println("</body></html>");

                out.close();
            } catch (NumberFormatException e) {
                // Handle the NumberFormatException (e.g., invalid input for parseInt)
                e.printStackTrace(); // Log the exception or handle it according to your needs
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
            }
        } else {
            // Display an error message for missing or empty 'id' parameter
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty 'id' parameter");
        }
	}

	 
}
