package application.copy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeBioImplementation;
import models.Employee;

/**
 * Servlet implementation class showallser
 */
@WebServlet("/showallser")
public class showallser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showallser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeBioImplementation employeeBioImpl = new EmployeeBioImplementation();
        List<Employee> employeeList = employeeBioImpl.getAll();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Employee List</h1>");

        if (!employeeList.isEmpty()) {
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Department</th>");
            out.println("<th>Salary</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Employee employee : employeeList) {
                out.println("<tr>");
                out.println("<td>" + employee.getId() + "</td>");
                out.println("<td>" + employee.getName() + "</td>");
                out.println("<td>" + employee.getEmail() + "</td>");
                out.println("<td>" + employee.getDepartment() + "</td>");
                out.println("<td>" + employee.getSalary() + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
        } else {
            out.println("<p>No employees found.</p>");
        }

        out.println("</body>");
        out.println("</html>");}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
