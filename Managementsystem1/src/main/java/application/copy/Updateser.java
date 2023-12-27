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

 
@WebServlet("/Updateser")
public class Updateser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            String name = request.getParameter("name");
	            String email = request.getParameter("email");
	            String department = request.getParameter("department");
	            int salary = Integer.parseInt(request.getParameter("salary"));

	            // Create an Employee object with the updated information
	            Employee updatedEmployee = new Employee();
	            updatedEmployee.setId(id);
	            updatedEmployee.setName(name);
	            updatedEmployee.setEmail(email);
	            updatedEmployee.setDepartment(department);
	            updatedEmployee.setSalary(salary);

	            EmployeeBioImplementation employeeBioImpl = new EmployeeBioImplementation();
	            int rowsUpdated = employeeBioImpl.update(updatedEmployee);

	            // Prepare the response
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();

	            out.println("<html><body>");
	            out.println("<h2>Update Result</h2>");
	            out.println("<p>" + rowsUpdated + " row(s) updated.</p>");
	            out.println("</body></html>");

	            out.close();
	        } catch (NumberFormatException e) {
	            // Handle the NumberFormatException (e.g., invalid input for parseInt)
	            e.printStackTrace(); // Log the exception or handle it according to your needs
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
	        }
	}

}
