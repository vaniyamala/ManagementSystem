package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;

public class EmployeeBioImplementation implements EmployeeBio{
	static private Connection connection=null;
	private PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res;
	private static String INSERT_QUERY = "INSERT INTO employee1 (id, name, email, departement, salary) VALUES (?, ?, ?, ?, ?)";
	private static String UPDATE_QUERY="UPDATE employee1 SET name=?,email=?,departement=?,salary=? where id=?";
	private static String DELETE_QUERY="DELETE from employee1  where id=?";
	private static String GET_Employee_QUERY="SELECT id,name,email,departement,salary from `employee1` where `id`=?";
	private static String GET_ALL_QUERY="SELECT * from `employee1`";
	 
	
	
	
	
	public EmployeeBioImplementation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycollege", "root", "root123");
			  
		} catch (ClassNotFoundException | SQLException e1) {			 
			e1.printStackTrace();
		}
		 
	}

	
	@Override
	public int save(Employee e) {
		
		try {
			prepareStatement=connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, e.getId());
			prepareStatement.setString(2,e.getName());
			prepareStatement.setString(3,e.getEmail());
			prepareStatement.setString(4,e.getDepartment());
			prepareStatement.setInt(5, e.getSalary());
			int rowsAffected = prepareStatement.executeUpdate();
	        System.out.println("Rows affected: " + rowsAffected); // Add this line

	        return rowsAffected;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}
		 
		
	}

	@Override
	public  int update(Employee e) {
		// TODO Auto-generated method stub
		
		try {
			prepareStatement=connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1,e.getName());
			prepareStatement.setString(2,e.getEmail());
			prepareStatement.setString(3,e.getDepartment());
			prepareStatement.setInt(4, e.getSalary());
			prepareStatement.setInt(5, e.getId());
			return prepareStatement.executeUpdate();
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		
		try {
			prepareStatement=connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1,id);
			return prepareStatement.executeUpdate();
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Employee e) {
		// TODO Auto-generated method stub

		return delete(e.getId());
	}

	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		
		try {
			statement=connection.prepareStatement(GET_Employee_QUERY);
			((PreparedStatement) statement).setInt(1,id);
			res=((PreparedStatement) statement).executeQuery();
			while(res.next()) {
				String name=res.getString("name");
				String email=res.getString("email");
				String departement=res.getString("departement");
				int salary=res.getInt("salary");
				Employee e =new Employee(id,name,email, departement, salary);
				return e;
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			
		
		  
		
		return null;
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		
		ArrayList<Employee> employeeList=new ArrayList<Employee>();
		try {
			statement=connection.createStatement();
			res=statement.executeQuery(GET_ALL_QUERY);
			
			while(res.next()) {
				int id=res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String departement=res.getString("departement");
				int salary=res.getInt("salary");
				Employee e =new Employee(id,name,email, departement, salary);
				employeeList.add(e);
				
			}
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return employeeList;
	}
	
}

